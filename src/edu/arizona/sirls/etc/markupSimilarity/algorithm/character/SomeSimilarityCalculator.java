package edu.arizona.sirls.etc.markupSimilarity.algorithm.character;

import edu.arizona.sirls.etc.markupSimilarity.io.Score;


public class SomeSimilarityCalculator implements ISimilarityCalculator {

	@Override
	public Score getSimilarity(Character a, Character b) {
		
		return new Score(0, this.getClass().toString());
	}
	
}
