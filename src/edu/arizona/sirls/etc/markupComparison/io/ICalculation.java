package edu.arizona.sirls.etc.markupComparison.io;

import java.util.concurrent.Callable;

public interface ICalculation<T extends IResult> extends Callable<T> {

	@Override
	public String toString();
	
}
