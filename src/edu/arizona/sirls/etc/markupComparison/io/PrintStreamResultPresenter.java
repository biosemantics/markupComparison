package edu.arizona.sirls.etc.markupComparison.io;

import java.io.PrintStream;
import java.util.List;

import com.google.inject.Inject;

import edu.arizona.sirls.etc.markupComparison.algorithm.Calculation;
import edu.arizona.sirls.etc.markupComparison.algorithm.IComparable;
import edu.arizona.sirls.etc.markupComparison.algorithm.IResult;
import edu.arizona.sirls.etc.markupComparison.algorithm.IResultAlgorithm;

public class PrintStreamResultPresenter implements ICalculationPresenter {

	private PrintStream printStream;

	@Inject
	public PrintStreamResultPresenter(PrintStream printStream) {
		this.printStream = printStream;
	}

	@Override
	public void present(
			List<? extends Calculation<? extends IResultAlgorithm<? extends IResult, ? extends IComparable>, ? extends IResult, ? extends IComparable>> calculations) {
		for(Calculation calculation : calculations) {
			printStream.println(calculation.toString());
		}
	}
	
}
