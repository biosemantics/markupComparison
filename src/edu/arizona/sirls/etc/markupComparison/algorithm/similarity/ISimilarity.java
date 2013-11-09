package edu.arizona.sirls.etc.markupComparison.algorithm.similarity;

import edu.arizona.sirls.etc.markupComparison.model.Description;

public interface ISimilarity<T> {

	public Result getSimilarity(T a, T b);
	
}
