package edu.arizona.sirls.etc.markupSimilarity.algorithm.structure;

import edu.arizona.sirls.etc.markupSimilarity.io.Score;
import edu.arizona.sirls.etc.markupSimilarity.model.Structure;

public interface ISimilarityCalculator {

	public Score getSimilarity(Structure a, Structure b);
	
}
