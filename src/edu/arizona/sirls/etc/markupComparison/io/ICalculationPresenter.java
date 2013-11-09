package edu.arizona.sirls.etc.markupComparison.io;

import java.util.List;

public interface ICalculationPresenter {

	void present(List<? extends ICalculation<? extends IResult>> calculations);

}
