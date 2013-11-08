package edu.arizona.sirls.etc.markupSimilarity.algorithm.treatment;

import edu.arizona.sirls.etc.markupSimilarity.io.Score;
import edu.arizona.sirls.etc.markupSimilarity.model.Description;
import edu.arizona.sirls.etc.markupSimilarity.model.Statement;
import edu.arizona.sirls.etc.markupSimilarity.model.TreatmentRoot;

public class CosineSimilarityCalculator implements ISimilarityCalculator {

	@Override
	public Score getSimilarity(TreatmentRoot a, TreatmentRoot b) {
		String aContent = getContent(a);
		String bContent = getContent(b);		
		edu.arizona.sirls.etc.markupSimilarity.algorithm.text.CosineSimilarityCalculator calculator = new 
				edu.arizona.sirls.etc.markupSimilarity.algorithm.text.CosineSimilarityCalculator();
		Score result = calculator.getSimilarity(aContent.toString(), bContent.toString());
		result.setAlgorithm(this.getClass());
		return result;
	}

	private String getContent(TreatmentRoot treatmentRoot) {
		StringBuilder contentBuilder = new StringBuilder();
		for(Description description : treatmentRoot.getDescriptions()) {
			for(Statement statement : description.getStatements())
				contentBuilder.append(statement.getText() + " ");
		}
		return contentBuilder.toString();
	}

}
