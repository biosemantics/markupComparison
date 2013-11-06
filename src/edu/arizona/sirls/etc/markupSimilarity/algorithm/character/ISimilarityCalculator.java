package edu.arizona.sirls.etc.markupSimilarity.algorithm.character;

import edu.arizona.sirls.etc.markupSimilarity.io.Score;

public interface ISimilarityCalculator {

	public Score getSimilarity(Character a, Character b);
	
}
