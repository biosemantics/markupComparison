package edu.arizona.sirls.etc.markupComparison.algorithm;

import java.util.concurrent.Callable;

import edu.arizona.sirls.etc.markupComparison.model.TreatmentRoot;

public class Calculation<A extends IResultAlgorithm<R, V>, R extends IResult, V extends IComparable> 
		extends ComparableValuePair<V> implements Callable<R> {

	private A algorithm;
	private R result;

	public Calculation(A algorithm, V a, String aLabel, V b, String bLabel) {
		super(a, aLabel, b, bLabel);
		this.algorithm = algorithm;
	}

	@Override
	public R call() throws Exception {
		result = algorithm.getResult(a, b);
		return result;
	}

	@Override
	public String toString() {
		if(aLabel != null && bLabel != null && !aLabel.isEmpty() && !bLabel.isEmpty())
			return "algo_" + algorithm.getClass().getSimpleName() + "(" +
			aLabel + ", " + bLabel + ")" +
			" = " + result.getResult();
		else
			return "algo_" + algorithm.getClass().getSimpleName() + "(" +
					a.toString() + ", " + b.toString() + ")" +
					" = " + result.getResult();
	}

}
