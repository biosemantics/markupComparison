package edu.arizona.sirls.etc.markupSimilarity.io;

public class SimilarityResult {

	private Object a;
	private Object b;
	private Score score;

	public SimilarityResult(Object a, Object b, Score score) {
		this.a = a;
		this.b = b;
		this.score = score;
	}

	public Object getA() {
		return a;
	}

	public void setA(Object a) {
		this.a = a;
	}

	public Object getB() {
		return b;
	}

	public void setB(Object b) {
		this.b = b;
	}

	public Score getScore() {
		return score;
	}

	public void setScore(Score score) {
		this.score = score;
	}

	

}
