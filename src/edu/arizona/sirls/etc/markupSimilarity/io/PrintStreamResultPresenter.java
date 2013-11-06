package edu.arizona.sirls.etc.markupSimilarity.io;

import java.io.PrintStream;
import java.util.List;

public class PrintStreamResultPresenter implements IResultPresenter {

	private PrintStream printStream;

	public PrintStreamResultPresenter(PrintStream printStream) {
		this.printStream = printStream;
	}
	
	@Override
	public void present(List<SimilarityResult> results) {
		for(SimilarityResult result : results) {
			Score score = result.getScore();
			printStream.println("similarity_" + score.getAlgorithm() + "(" +
					result.getA().toString() + ", " + result.getB().toString() + ")" +
					" = " + score.getSimilarity());
		}
	}

}
