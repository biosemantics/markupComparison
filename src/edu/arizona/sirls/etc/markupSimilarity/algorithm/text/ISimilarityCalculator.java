package edu.arizona.sirls.etc.markupSimilarity.algorithm.text;

import edu.arizona.sirls.etc.markupSimilarity.io.Score;

public interface ISimilarityCalculator {

	public Score getSimilarity(String a, String b) throws Exception;
	
}
