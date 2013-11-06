package edu.arizona.sirls.etc.markupSimilarity.io;

public class Score {

	private double similarity;
	private String algorithm;
	public Score(double similarity, String algorithm) {
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
	public String getAlgorithm() {
		return algorithm;
	}
	public void setAlgorithm(String algorithm) {
		this.algorithm = algorithm;
	}
	
	
	
}
