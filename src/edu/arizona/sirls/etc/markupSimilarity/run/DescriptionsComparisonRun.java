package edu.arizona.sirls.etc.markupSimilarity.run;

import java.io.File;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import com.google.inject.Inject;
import com.google.inject.name.Named;

import edu.arizona.sirls.etc.markupSimilarity.algorithm.treatment.ISimilarityCalculator;
import edu.arizona.sirls.etc.markupSimilarity.io.IResultPresenter;
import edu.arizona.sirls.etc.markupSimilarity.io.ITreatmentReader;
import edu.arizona.sirls.etc.markupSimilarity.io.Score;
import edu.arizona.sirls.etc.markupSimilarity.io.SimilarityResult;
import edu.arizona.sirls.etc.markupSimilarity.model.DescriptionsFile;
import edu.arizona.sirls.etc.markupSimilarity.model.Description;
import edu.arizona.sirls.etc.markupSimilarity.model.DescriptionsFileList;
import edu.arizona.sirls.etc.markupSimilarity.model.Statement;
import edu.arizona.sirls.etc.markupSimilarity.model.Structure;
import edu.arizona.sirls.etc.markupSimilarity.model.TreatmentRoot;

public class DescriptionsComparisonRun implements IRun {

	private ITreatmentReader reader;
	private File input;
	private ISimilarityCalculator similarityCalculator;
	private IResultPresenter resultPresenter;

	@Inject
	public DescriptionsComparisonRun(ITreatmentReader reader, 
			@Named("input")File input, ISimilarityCalculator similarityCalculator, 
			IResultPresenter resultPresenter) {
		this.reader = reader;
		this.input = input;
		this.similarityCalculator = similarityCalculator;
		this.resultPresenter = resultPresenter;
	}
	
	@Override
	public void run() {
		try {
			List<SimilarityResult> results = new LinkedList<SimilarityResult>();
			
			DescriptionsFileList descriptionsFileList = reader.read(input);	
			
			Map<TreatmentRoot, String> treatmentRootsMap = new LinkedHashMap<TreatmentRoot, String>();
			for(DescriptionsFile descriptionsFile : descriptionsFileList.getDescriptionsFiles()) {
				for(int i=0; i<descriptionsFile.getTreatmentRoots().size(); i++) {
					TreatmentRoot treatmentRoot = descriptionsFile.getTreatmentRoots().get(i);
					treatmentRootsMap.put(treatmentRoot, descriptionsFile.getName() + " - " + i);
				}
			}
			
			List<TreatmentRoot> iterationList = new LinkedList<TreatmentRoot>();
			iterationList.addAll(treatmentRootsMap.keySet());
			for(int i=0; i<iterationList.size(); i++) {
				TreatmentRoot a = iterationList.get(i);
				for(int j=i+1; j<descriptionsFileList.getDescriptionsFiles().size(); j++) { 
					TreatmentRoot b = iterationList.get(j);					
					Score score = similarityCalculator.getSimilarity(a, b);
					String aLabel = treatmentRootsMap.get(a);
					String bLabel = treatmentRootsMap.get(b);
					results.add(new SimilarityResult(a, aLabel, b, bLabel, score));
				}
			}
			resultPresenter.present(results);
		}catch(Exception e) {
			e.printStackTrace();
		}
	}

}
