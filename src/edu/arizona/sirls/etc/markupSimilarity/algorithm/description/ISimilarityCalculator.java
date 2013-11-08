package edu.arizona.sirls.etc.markupSimilarity.algorithm.description;

import edu.arizona.sirls.etc.markupSimilarity.io.Score;
import edu.arizona.sirls.etc.markupSimilarity.model.Description;

public interface ISimilarityCalculator {

	public Score getSimilarity(Description a, Description b);
	
}
