package edu.arizona.sirls.etc.markupComparison.algorithm.similarity;

import edu.arizona.sirls.etc.markupComparison.algorithm.IComparable;

public class Calculation<V extends IComparable>
	extends edu.arizona.sirls.etc.markupComparison.algorithm.Calculation<
	ISimilarity<V>, Result, V> {

	public Calculation(ISimilarity<V> similarity, V a, String aLabel, V b, String bLabel) {
		super(similarity, a, aLabel, b, bLabel);
	}
}
