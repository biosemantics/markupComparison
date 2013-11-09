package edu.arizona.sirls.etc.markupComparison.algorithm.similarity.character;

import edu.arizona.sirls.etc.markupComparison.algorithm.similarity.ISimilarity;
import edu.arizona.sirls.etc.markupComparison.algorithm.similarity.Result;


public class SomeSimilarity implements ISimilarity<edu.arizona.sirls.etc.markupComparison.model.Character> {

	@Override
	public Result getSimilarity(edu.arizona.sirls.etc.markupComparison.model.Character a, 
			edu.arizona.sirls.etc.markupComparison.model.Character b) {
		
		return new Result(0);
	}
	
}
