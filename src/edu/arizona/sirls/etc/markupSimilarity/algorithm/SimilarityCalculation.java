package edu.arizona.sirls.etc.markupSimilarity.algorithm;

import java.util.concurrent.Callable;

import edu.arizona.sirls.etc.markupSimilarity.io.Score;

public class SimilarityCalculation<T> implements Callable<Score> {

	private T a;
	private T b;
	private ISimilarity<T> similarity;

	public SimilarityCalculation(ISimilarity<T> similarity, T a, T b) {
		this.a = a;
		this.b = b;
		this.similarity = similarity;
	}
	
	@Override
	public Score call() throws Exception {
		return similarity.getSimilarity(a, b);
	}

}
