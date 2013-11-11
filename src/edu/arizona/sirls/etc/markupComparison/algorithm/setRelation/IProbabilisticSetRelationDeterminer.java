package edu.arizona.sirls.etc.markupComparison.algorithm.setRelation;

import edu.arizona.sirls.etc.markupComparison.algorithm.IComparable;
import edu.arizona.sirls.etc.markupComparison.algorithm.IResultAlgorithm;

public interface IProbabilisticSetRelationDeterminer<V extends IComparable> extends 
		IResultAlgorithm<ProbabilisticResult, V> {

	public ProbabilisticResult getResult(V a, V b);
	
}
