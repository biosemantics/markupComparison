package edu.arizona.sirls.etc.markupComparison.algorithm.setRelation;

import edu.arizona.sirls.etc.markupComparison.algorithm.IResult;

public class ProbabilisticResult implements IResult {

	private double congruent;
	private double subset; 
	private double superset;
	private double overlap;
	private double distinct;
	
	public ProbabilisticResult(double congruent, double subset, double superset, 
			double overlap, double distinct) {
		this.congruent = congruent;
		this.subset = subset;
		this.superset = superset;
		this.overlap = overlap;
		this.distinct = distinct;
	}
	
	@Override
	public String getResult() {
		return "Congruent: " + congruent + " Subset: " + subset + 
				" Superset: " + superset + 
				" Overlap: " + overlap + " Distinct: " + distinct;
	}

	public double getCongruent() {
		return congruent;
	}

	public void setCongruent(double congruent) {
		this.congruent = congruent;
	}

	public double getSubset() {
		return subset;
	}

	public void setSubset(double subset) {
		this.subset = subset;
	}

	public double getOverlap() {
		return overlap;
	}

	public void setOverlap(double overlap) {
		this.overlap = overlap;
	}

	public double getDistinct() {
		return distinct;
	}

	public void setDistinct(double distinct) {
		this.distinct = distinct;
	}

	public double getSuperset() {
		return superset;
	}

	public void setSuperset(double superset) {
		this.superset = superset;
	}
	
	
}

