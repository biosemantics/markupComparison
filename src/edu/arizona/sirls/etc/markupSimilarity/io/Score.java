package edu.arizona.sirls.etc.markupSimilarity.io;

public class Score {

	private double similarity;
	private Class algorithm;
	public Score(double similarity, Class algorithm) {
		super();
		this.similarity = similarity;
		this.algorithm = algorithm;
	}
	public double getSimilarity() {
		return similarity;
	}
	public void setSimilarity(double similarity) {
		this.similarity = similarity;
	}
	public Class getAlgorithm() {
		return algorithm;
	}
	public void setAlgorithm(Class algorithm) {
		this.algorithm = algorithm;
	}
	
	
	
}
