package edu.arizona.sirls.etc.markupSimilarity.algorithm.description;

import edu.arizona.sirls.etc.markupSimilarity.algorithm.ISimilarity;
import edu.arizona.sirls.etc.markupSimilarity.io.Score;
import edu.arizona.sirls.etc.markupSimilarity.model.Description;

public class CosineSimilarity implements ISimilarity<Description> {

	@Override
	public Score getSimilarity(Description a, Description b) {
		edu.arizona.sirls.etc.markupSimilarity.algorithm.text.CosineSimilarity calculator = new 
				edu.arizona.sirls.etc.markupSimilarity.algorithm.text.CosineSimilarity();
		return calculator.getSimilarity(a.getText(), b.getText());
	}

}
