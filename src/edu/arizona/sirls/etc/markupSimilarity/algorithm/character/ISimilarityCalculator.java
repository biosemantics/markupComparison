package edu.arizona.sirls.etc.markupSimilarity.algorithm.character;

import edu.arizona.sirls.etc.markupSimilarity.io.Score;

public interface ISimilarityCalculator {

	public Score getSimilarity(edu.arizona.sirls.etc.markupSimilarity.model.Character a, 
			edu.arizona.sirls.etc.markupSimilarity.model.Character b);
	
}
