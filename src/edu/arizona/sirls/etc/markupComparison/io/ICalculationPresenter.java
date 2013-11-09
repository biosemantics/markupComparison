package edu.arizona.sirls.etc.markupComparison.io;

import java.util.List;

import edu.arizona.sirls.etc.markupComparison.algorithm.ICalculation;
import edu.arizona.sirls.etc.markupComparison.algorithm.IResult;

public interface ICalculationPresenter {

	void present(List<? extends ICalculation<? extends IResult>> calculations);

}
