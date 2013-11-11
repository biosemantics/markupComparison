package edu.arizona.sirls.etc.markupComparison.algorithm.similarity.treatment;

import edu.arizona.sirls.etc.markupComparison.algorithm.similarity.ISimilarity;
import edu.arizona.sirls.etc.markupComparison.algorithm.similarity.Result;
import edu.arizona.sirls.etc.markupComparison.model.Description;
import edu.arizona.sirls.etc.markupComparison.model.Statement;
import edu.arizona.sirls.etc.markupComparison.model.StringComparable;
import edu.arizona.sirls.etc.markupComparison.model.TreatmentRoot;

public class CosineSimilarity implements ISimilarity<TreatmentRoot> {

	@Override
	public Result getResult(TreatmentRoot a, TreatmentRoot b) {
		String aContent = getContent(a);
		String bContent = getContent(b);		
		edu.arizona.sirls.etc.markupComparison.algorithm.similarity.text.CosineSimilarity calculator = new 
				edu.arizona.sirls.etc.markupComparison.algorithm.similarity.text.CosineSimilarity();
		Result result = calculator.getResult(new StringComparable(aContent.toString()), 
				new StringComparable(bContent.toString()));
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
