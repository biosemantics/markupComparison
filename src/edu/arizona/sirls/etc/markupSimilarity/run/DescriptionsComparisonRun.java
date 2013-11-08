package edu.arizona.sirls.etc.markupSimilarity.run;

import java.io.File;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import com.google.common.util.concurrent.ListenableFuture;
import com.google.common.util.concurrent.ListeningExecutorService;
import com.google.common.util.concurrent.MoreExecutors;
import com.google.inject.Inject;
import com.google.inject.name.Named;

import edu.arizona.sirls.etc.markupSimilarity.algorithm.ISimilarity;
import edu.arizona.sirls.etc.markupSimilarity.algorithm.SimilarityCalculation;
import edu.arizona.sirls.etc.markupSimilarity.io.IResultPresenter;
import edu.arizona.sirls.etc.markupSimilarity.io.ITreatmentReader;
import edu.arizona.sirls.etc.markupSimilarity.io.Score;
import edu.arizona.sirls.etc.markupSimilarity.io.SimilarityResult;
import edu.arizona.sirls.etc.markupSimilarity.model.DescriptionsFile;
import edu.arizona.sirls.etc.markupSimilarity.model.DescriptionsFileList;
import edu.arizona.sirls.etc.markupSimilarity.model.TreatmentRoot;

public class DescriptionsComparisonRun implements IRun {

	private ITreatmentReader reader;
	private File input;
	private ISimilarity<TreatmentRoot> similarity;
	private IResultPresenter resultPresenter;
	
	@Inject
	public DescriptionsComparisonRun(ITreatmentReader reader, 
			@Named("input")File input, ISimilarity<TreatmentRoot> similarity, 
			IResultPresenter resultPresenter) {
		this.reader = reader;
		this.input = input;
		this.similarity = similarity;
		this.resultPresenter = resultPresenter;
	}
	
	@Override
	public void run() {
		try {
			ListeningExecutorService executorService = MoreExecutors.listeningDecorator(Executors.newFixedThreadPool(10));//.newCachedThreadPool());
			
			//read input
			DescriptionsFileList descriptionsFileList = reader.read(input);	
			
			//get meaningful labels
			Map<TreatmentRoot, String> treatmentRootsMap = new LinkedHashMap<TreatmentRoot, String>();
			for(DescriptionsFile descriptionsFile : descriptionsFileList.getDescriptionsFiles()) {
				for(int i=0; i<descriptionsFile.getTreatmentRoots().size(); i++) {
					TreatmentRoot treatmentRoot = descriptionsFile.getTreatmentRoots().get(i);
					treatmentRootsMap.put(treatmentRoot, descriptionsFile.getName() + " - " + i);
				}
			}
			
			//convert to list for iteration
			List<TreatmentRoot> iterationList = new LinkedList<TreatmentRoot>();
			iterationList.addAll(treatmentRootsMap.keySet());
			
			// calculate number of calculations
			int size = iterationList.size();
			int numberOfCalculations = (size-1) * size / 2;
			System.out.println("Need " + numberOfCalculations + " calculations");
			final CountDownLatch calculationsLatch = new CountDownLatch(numberOfCalculations);
			
			// calculate
			int x=0;
			Map<Future<Score>, SimilarityResult> futureScoreResults = new HashMap<Future<Score>, SimilarityResult>();
			for(int i=0; i<iterationList.size(); i++) {
				TreatmentRoot a = iterationList.get(i);
				for(int j=i+1; j<descriptionsFileList.getDescriptionsFiles().size(); j++) { 
					TreatmentRoot b = iterationList.get(j);	
					SimilarityCalculation<TreatmentRoot> calculation = new SimilarityCalculation<TreatmentRoot>(similarity, a, b);
					ListenableFuture<Score> futureScore = executorService.submit(calculation);
					futureScoreResults.put(futureScore, new SimilarityResult(a, treatmentRootsMap.get(a), b, treatmentRootsMap.get(b), null));
					futureScore.addListener(new Runnable() {
						@Override
						public void run() {
							calculationsLatch.countDown();
							System.out.println(calculationsLatch.getCount());
						} 
					}, executorService);
				}
			}
			System.out.println("all calculations have been submitted - await for completion...");
				
			//present results when calculations have finished
			calculationsLatch.await();
			executorService.shutdown();
			List<SimilarityResult> results = new LinkedList<SimilarityResult>();
			for(Future<Score> futureScore : futureScoreResults.keySet()) {
				SimilarityResult result = futureScoreResults.get(futureScore);
				result.setScore(futureScore.get());
				results.add(result);
			}
			Collections.sort(results);
			resultPresenter.present(results);
		}catch(Exception e) {
			e.printStackTrace();
		}
	}

}
