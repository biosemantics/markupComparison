package edu.arizona.sirls.etc.markupComparison.algorithm.similarity;

import edu.arizona.sirls.etc.markupComparison.algorithm.IComparable;
import edu.arizona.sirls.etc.markupComparison.algorithm.IResultAlgorithm;
import edu.arizona.sirls.etc.markupComparison.model.Description;

public interface ISimilarity<V extends IComparable> extends IResultAlgorithm<Result, V> {

	public Result getResult(V a, V b);
	
}
