package edu.arizona.sirls.etc.markupComparison.algorithm.similarity;

import edu.arizona.sirls.etc.markupComparison.algorithm.IResult;

public class Result implements IResult {

	private double similarity;
	public Result(double similarity) {
		super();
		this.similarity = similarity;
	}
	public double getSimilarity() {
		return similarity;
	}
	public void setSimilarity(double similarity) {
		this.similarity = similarity;
	}
	
	public String getResult() {
		return String.valueOf(similarity);
	}
	
}
