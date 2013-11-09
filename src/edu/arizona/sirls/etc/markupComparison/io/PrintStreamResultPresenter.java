package edu.arizona.sirls.etc.markupComparison.io;

import java.io.PrintStream;
import java.util.List;

import com.google.inject.Inject;

public class PrintStreamResultPresenter implements ICalculationPresenter {

	private PrintStream printStream;

	@Inject
	public PrintStreamResultPresenter(PrintStream printStream) {
		this.printStream = printStream;
	}
	
	@Override
	public void present(
			List<? extends ICalculation<? extends IResult>> calculations) {
		for(ICalculation<? extends IResult> calculation : calculations) {
			printStream.println(calculation.toString());
		}
	}

}
