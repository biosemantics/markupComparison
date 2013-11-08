package edu.arizona.sirls.etc.markupSimilarity.algorithm;

import edu.arizona.sirls.etc.markupSimilarity.model.Description;

public interface ISimilarity<T> {

	public Score getSimilarity(T a, T b);
	
}
