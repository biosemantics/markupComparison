package edu.arizona.sirls.etc.markupSimilarity.run;

import java.io.File;
import java.util.LinkedList;
import java.util.List;

import edu.arizona.sirls.etc.markupSimilarity.algorithm.character.ISimilarityCalculator;
import edu.arizona.sirls.etc.markupSimilarity.io.IResultPresenter;
import edu.arizona.sirls.etc.markupSimilarity.io.ITreatmentReader;
import edu.arizona.sirls.etc.markupSimilarity.io.Score;
import edu.arizona.sirls.etc.markupSimilarity.io.SimilarityResult;
import edu.arizona.sirls.etc.markupSimilarity.model.Treatment;

public class CharacterSummaryRun implements IRun {

	private ITreatmentReader reader;
	private File input;
	private ISimilarityCalculator similarityCalculator;
	private IResultPresenter resultPresenter;

	public CharacterSummaryRun(ITreatmentReader reader, 
			File input, ISimilarityCalculator similarityCalculator, 
			IResultPresenter resultPresenter) {
		this.reader = reader;
		this.input = input;
		this.similarityCalculator = similarityCalculator;
		this.resultPresenter = resultPresenter;
	}
	
	@Override
	public void run() {
		List<Treatment> treatments = reader.read(input);
		List<Character> characters = new LinkedList<Character>();
		for(Treatment treatment : treatments) {
			characters.addAll(treatment.getCharacters());
		}
		
		List<SimilarityResult> results = new LinkedList<SimilarityResult>();
		for(int i=0; i<characters.size(); i++) {
			Character a = characters.get(i);
			for(int j=i+1; j<characters.size(); j++) { 
				Character b = characters.get(j);
				Score score = similarityCalculator.getSimilarity(a, b);
				results.add(new SimilarityResult(a, b, score));
			}
		}
		
		resultPresenter.present(results);
	}
}
