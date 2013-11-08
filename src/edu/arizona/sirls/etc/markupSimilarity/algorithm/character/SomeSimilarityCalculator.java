package edu.arizona.sirls.etc.markupSimilarity.algorithm.character;

import edu.arizona.sirls.etc.markupSimilarity.io.Score;


public class SomeSimilarityCalculator implements ISimilarityCalculator {

	@Override
	public Score getSimilarity(edu.arizona.sirls.etc.markupSimilarity.model.Character a, 
			edu.arizona.sirls.etc.markupSimilarity.model.Character b) {
		
		return new Score(0, this.getClass());
	}
	
}
