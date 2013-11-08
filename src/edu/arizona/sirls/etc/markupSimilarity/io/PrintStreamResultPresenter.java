package edu.arizona.sirls.etc.markupSimilarity.io;

import java.io.PrintStream;
import java.util.List;

import com.google.inject.Inject;

public class PrintStreamResultPresenter implements IResultPresenter {

	private PrintStream printStream;

	@Inject
	public PrintStreamResultPresenter(PrintStream printStream) {
		this.printStream = printStream;
	}
	
	@Override
	public void present(List<SimilarityResult> results) {
		for(SimilarityResult result : results) {
			printStream.println(result.toString());
		}
	}

}
