package edu.arizona.sirls.etc.markupSimilarity.algorithm.structure;

import edu.arizona.sirls.etc.markupSimilarity.model.Structure;

public interface ISimilarityCalculator {

	public double getSimilarity(Structure a, Structure b);
	
}
