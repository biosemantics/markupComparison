package edu.arizona.sirls.etc.markupComparison.algorithm;

import java.util.concurrent.Callable;

public interface ICalculation<T extends IResult> extends Callable<T> {

	@Override
	public String toString();
	
}
