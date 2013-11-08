package edu.arizona.sirls.etc.markupSimilarity.run;

import java.io.File;
import java.util.LinkedList;
import java.util.List;

import com.google.inject.Inject;

import edu.arizona.sirls.etc.markupSimilarity.algorithm.character.ISimilarityCalculator;
import edu.arizona.sirls.etc.markupSimilarity.io.IResultPresenter;
import edu.arizona.sirls.etc.markupSimilarity.io.ITreatmentReader;
import edu.arizona.sirls.etc.markupSimilarity.io.Score;
import edu.arizona.sirls.etc.markupSimilarity.io.SimilarityResult;
import edu.arizona.sirls.etc.markupSimilarity.model.DescriptionsFile;
import edu.arizona.sirls.etc.markupSimilarity.model.Description;
import edu.arizona.sirls.etc.markupSimilarity.model.DescriptionsFileList;
import edu.arizona.sirls.etc.markupSimilarity.model.Statement;
import edu.arizona.sirls.etc.markupSimilarity.model.Structure;

public class CharacterComparisonRun implements IRun {

	private ITreatmentReader reader;
	private File input;
	private ISimilarityCalculator similarityCalculator;
	private IResultPresenter resultPresenter;

	@Inject
	public CharacterComparisonRun(ITreatmentReader reader, 
			File input, ISimilarityCalculator similarityCalculator, 
			IResultPresenter resultPresenter) {
		this.reader = reader;
		this.input = input;
		this.similarityCalculator = similarityCalculator;
		this.resultPresenter = resultPresenter;
	}
	
	@Override
	public void run() {
		try {
			List<edu.arizona.sirls.etc.markupSimilarity.model.Character> characters = 
					new LinkedList<edu.arizona.sirls.etc.markupSimilarity.model.Character>();
			DescriptionsFileList descriptionsFileList = reader.read(input);
			for(DescriptionsFile descriptionsFile : descriptionsFileList.getDescriptionsFiles()) {
				for(Description description : descriptionsFile.getDescriptions()) {
					for(Statement statement : description.getStatements()) {
						for(Structure structure : statement.getStructures()) {
							characters.addAll(structure.getCharacters());
						}
					}
				}
			}
			
			List<SimilarityResult> results = new LinkedList<SimilarityResult>();
			for(int i=0; i<characters.size(); i++) {
				edu.arizona.sirls.etc.markupSimilarity.model.Character a = characters.get(i);
				for(int j=i+1; j<characters.size(); j++) { 
					edu.arizona.sirls.etc.markupSimilarity.model.Character b = characters.get(j);
					Score score = similarityCalculator.getSimilarity(a, b);
					results.add(new SimilarityResult(a, null, b, null, score));
				}
			}
			
			resultPresenter.present(results);
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
}
