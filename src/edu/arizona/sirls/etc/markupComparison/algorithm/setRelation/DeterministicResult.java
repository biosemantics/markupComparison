package edu.arizona.sirls.etc.markupComparison.algorithm.setRelation;

import edu.arizona.sirls.etc.markupComparison.algorithm.IResult;

public enum DeterministicResult implements IResult {
	CONGRUENT("Congruent"), 
	SUPERSET("Superset"), 
	SUBSET("Subset"), 
	OVERLAP("Overlap"), 
	DISTINCT("Distinct");

	private final String value;

	DeterministicResult(String value) {
		this.value = value;
	}

	@Override
	public String getResult() {
		return value;
	}

}
