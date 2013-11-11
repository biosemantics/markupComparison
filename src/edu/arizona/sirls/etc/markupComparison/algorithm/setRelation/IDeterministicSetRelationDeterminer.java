package edu.arizona.sirls.etc.markupComparison.algorithm.setRelation;

import edu.arizona.sirls.etc.markupComparison.algorithm.IComparable;
import edu.arizona.sirls.etc.markupComparison.algorithm.IResultAlgorithm;

public interface IDeterministicSetRelationDeterminer<V extends IComparable> 
		extends IResultAlgorithm<DeterministicResult, V> {

	public DeterministicResult getResult(V a, V b);
	
}
