package edu.arizona.sirls.etc.markupComparison.algorithm.similarity;

import edu.arizona.sirls.etc.markupComparison.algorithm.AbstractCalculation;
import edu.arizona.sirls.etc.markupComparison.algorithm.ICalculation;
import edu.arizona.sirls.etc.markupComparison.algorithm.IResult;


public class Calculation<T> extends AbstractCalculation<T> implements ICalculation<Result> {

	private ISimilarity<T> similarity;
	private Result result;

	public Calculation(ISimilarity<T> similarity, T a, String aLabel, T b, String bLabel) {
		super(a, aLabel, b, bLabel);
		this.similarity = similarity;
	}
	
	@Override
	public Result call() throws Exception {
		result = similarity.getSimilarity(a, b);
		return result;
	}

	@Override
	public String toString() {
		if(aLabel != null && bLabel != null && !aLabel.isEmpty() && !bLabel.isEmpty())
			return "similarity_" + similarity.getClass().getSimpleName() + "(" +
			aLabel + ", " + bLabel + ")" +
			" = " + result.getSimilarity();
		else
			return "similarity_" + similarity.getClass().getSimpleName() + "(" +
					a.toString() + ", " + b.toString() + ")" +
					" = " + result.getSimilarity();
	}
}
