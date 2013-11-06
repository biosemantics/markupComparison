package edu.arizona.sirls.etc.markupSimilarity.algorithm.treatment;

import edu.arizona.sirls.etc.markupSimilarity.model.Treatment;

public interface ISimilarityCalculator {

	public double getSimilarity(Treatment a, Treatment b);
	
}
