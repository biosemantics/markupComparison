package edu.arizona.sirls.etc.markupComparison.run;

import java.io.File;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Executors;

import com.google.common.util.concurrent.ListenableFuture;
import com.google.common.util.concurrent.ListeningExecutorService;
import com.google.common.util.concurrent.MoreExecutors;
import com.google.inject.Inject;
import com.google.inject.name.Named;

import edu.arizona.sirls.etc.markupComparison.algorithm.Calculation;
import edu.arizona.sirls.etc.markupComparison.algorithm.IResult;
import edu.arizona.sirls.etc.markupComparison.algorithm.IResultAlgorithm;
import edu.arizona.sirls.etc.markupComparison.io.ICalculationPresenter;
import edu.arizona.sirls.etc.markupComparison.io.ITreatmentReader;
import edu.arizona.sirls.etc.markupComparison.model.DescriptionsFile;
import edu.arizona.sirls.etc.markupComparison.model.DescriptionsFileList;
import edu.arizona.sirls.etc.markupComparison.model.TreatmentRoot;

public class DescriptionsComparisonRun implements IRun {

	private ITreatmentReader reader;
	private File input;
	private IResultAlgorithm<? extends IResult, TreatmentRoot> algorithm;
	private ICalculationPresenter resultPresenter;
	
	@Inject
	public DescriptionsComparisonRun(ITreatmentReader reader, 
			@Named("input")File input, 
			IResultAlgorithm<? extends IResult, TreatmentRoot> algorithm, 
			ICalculationPresenter resultPresenter) {
		this.reader = reader;
		this.input = input;
		this.algorithm = algorithm;
		this.resultPresenter = resultPresenter;
	}
	
	@Override
	public void run() {
		try {
			ListeningExecutorService executorService = MoreExecutors.listeningDecorator(Executors.newFixedThreadPool(2));//.newCachedThreadPool());
			
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
			List<Calculation<IResultAlgorithm<IResult, TreatmentRoot>, IResult, TreatmentRoot>> results = 
					new LinkedList<Calculation<IResultAlgorithm<IResult, TreatmentRoot>, IResult, TreatmentRoot>>();
			for(int i=0; i<iterationList.size(); i++) {
				TreatmentRoot a = iterationList.get(i);
				for(int j=i+1; j<descriptionsFileList.getDescriptionsFiles().size(); j++) { 
					TreatmentRoot b = iterationList.get(j);	
					
					String aLabel = treatmentRootsMap.get(a);
					String bLabel = treatmentRootsMap.get(b);
					if(a.getTaxonIdentification() != null)
						aLabel = a.getTaxonIdentification().toString();
					if(b.getTaxonIdentification() != null)
						bLabel = b.getTaxonIdentification().toString();
					
					//Calculation calculation = null;
					Calculation<IResultAlgorithm<IResult, TreatmentRoot>, IResult, TreatmentRoot> calculation = 
							new Calculation<IResultAlgorithm<IResult, TreatmentRoot>, IResult, TreatmentRoot>(
							(IResultAlgorithm<IResult, TreatmentRoot>)algorithm, a, aLabel, b, bLabel); 
					ListenableFuture<IResult> futureScore = executorService.submit(calculation);
					futureScore.addListener(new Runnable() {
						@Override
						public void run() {
							calculationsLatch.countDown();
							System.out.println("remaining calculations: " + calculationsLatch.getCount());
						} 
					}, executorService);
					results.add(calculation);
				}
			}
			System.out.println("all calculations have been submitted - await for completion...");
				
			//present results when calculations have finished
			calculationsLatch.await();
			executorService.shutdown();
			Collections.sort(results);
			resultPresenter.present(results);
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
}
