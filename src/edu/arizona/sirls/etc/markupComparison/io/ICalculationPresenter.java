package edu.arizona.sirls.etc.markupComparison.io;

import java.util.List;

import edu.arizona.sirls.etc.markupComparison.algorithm.Calculation;
import edu.arizona.sirls.etc.markupComparison.algorithm.IComparable;
import edu.arizona.sirls.etc.markupComparison.algorithm.IResult;
import edu.arizona.sirls.etc.markupComparison.algorithm.IResultAlgorithm;

public interface ICalculationPresenter {

	void present(List<? extends Calculation<? extends IResultAlgorithm<? extends IResult, ?
			extends IComparable>,  
			? extends IResult, ? extends IComparable>> calculations);

}
