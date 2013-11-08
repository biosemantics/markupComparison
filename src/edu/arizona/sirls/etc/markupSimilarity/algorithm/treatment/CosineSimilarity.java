package edu.arizona.sirls.etc.markupSimilarity.algorithm.treatment;

import edu.arizona.sirls.etc.markupSimilarity.algorithm.ISimilarity;
import edu.arizona.sirls.etc.markupSimilarity.io.Score;
import edu.arizona.sirls.etc.markupSimilarity.model.Description;
import edu.arizona.sirls.etc.markupSimilarity.model.Statement;
import edu.arizona.sirls.etc.markupSimilarity.model.TreatmentRoot;

public class CosineSimilarity implements ISimilarity<TreatmentRoot> {

	@Override
	public Score getSimilarity(TreatmentRoot a, TreatmentRoot b) {
		String aContent = getContent(a);
		String bContent = getContent(b);		
		edu.arizona.sirls.etc.markupSimilarity.algorithm.text.CosineSimilarity calculator = new 
				edu.arizona.sirls.etc.markupSimilarity.algorithm.text.CosineSimilarity();
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
