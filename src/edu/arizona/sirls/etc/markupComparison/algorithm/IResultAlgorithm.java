package edu.arizona.sirls.etc.markupComparison.algorithm;

public interface IResultAlgorithm<R extends IResult, V extends IComparable> {

	public R getResult(V a, V b);
	
}
