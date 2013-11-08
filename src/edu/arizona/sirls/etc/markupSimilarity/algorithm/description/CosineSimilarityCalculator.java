package edu.arizona.sirls.etc.markupSimilarity.algorithm.description;

import edu.arizona.sirls.etc.markupSimilarity.io.Score;
import edu.arizona.sirls.etc.markupSimilarity.model.Description;

public class CosineSimilarityCalculator implements ISimilarityCalculator {

	@Override
	public Score getSimilarity(Description a, Description b) {
		edu.arizona.sirls.etc.markupSimilarity.algorithm.text.CosineSimilarityCalculator calculator = new 
				edu.arizona.sirls.etc.markupSimilarity.algorithm.text.CosineSimilarityCalculator();
		return calculator.getSimilarity(a.getText(), b.getText());
	}

}
