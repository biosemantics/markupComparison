package edu.arizona.sirls.etc.markupSimilarity.io;

public class SimilarityResult implements Comparable<SimilarityResult> {

	private Object a;
	private Object b;
	private Score score;
	private String aLabel;
	private String bLabel;

	public SimilarityResult(Object a, String aLabel, Object b, String bLabel, Score score) {
		this.a = a;
		this.aLabel = aLabel;
		this.b = b;
		this.bLabel = bLabel;
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

	
	
	public String getaLabel() {
		return aLabel;
	}

	public void setaLabel(String aLabel) {
		this.aLabel = aLabel;
	}

	public String getbLabel() {
		return bLabel;
	}

	public void setbLabel(String bLabel) {
		this.bLabel = bLabel;
	}

	@Override
	public String toString() {
		if(aLabel != null && bLabel != null && !aLabel.isEmpty() && !bLabel.isEmpty())
			return "similarity_" + score.getAlgorithm().getSimpleName() + "(" +
			aLabel + ", " + bLabel + ")" +
			" = " + score.getSimilarity();
		else
			return "similarity_" + score.getAlgorithm().getSimpleName() + "(" +
					a.toString() + ", " + b.toString() + ")" +
					" = " + score.getSimilarity();
	}

	@Override
	public int compareTo(SimilarityResult o) {
		if(this.getaLabel().compareTo(o.getbLabel()) == 0)
			return this.getbLabel().compareTo(o.getbLabel());
		return this.getaLabel().compareTo(o.getbLabel());
	}
	

}
