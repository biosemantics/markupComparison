package edu.arizona.sirls.etc.markupComparison.algorithm.setRelation;

import edu.arizona.sirls.etc.markupComparison.algorithm.IComparable;

public class Calculation<V extends IComparable>
	extends edu.arizona.sirls.etc.markupComparison.algorithm.Calculation<
	IDeterministicSetRelationDeterminer<V>, DeterministicResult, V> {

	public Calculation(IDeterministicSetRelationDeterminer<V> determiner, V a, String aLabel, V b, String bLabel) {
		super(determiner, a, aLabel, b, bLabel);
	}
}
