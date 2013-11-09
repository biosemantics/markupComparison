package edu.arizona.sirls.etc.markupComparison.algorithm.similarity.description;

import edu.arizona.sirls.etc.markupComparison.algorithm.similarity.ISimilarity;
import edu.arizona.sirls.etc.markupComparison.algorithm.similarity.Result;
import edu.arizona.sirls.etc.markupComparison.model.Description;

public class CosineSimilarity implements ISimilarity<Description> {

	@Override
	public Result getSimilarity(Description a, Description b) {
		edu.arizona.sirls.etc.markupComparison.algorithm.similarity.text.CosineSimilarity calculator = new 
				edu.arizona.sirls.etc.markupComparison.algorithm.similarity.text.CosineSimilarity();
		return calculator.getSimilarity(a.getText(), b.getText());
	}

}
