package edu.arizona.sirls.etc.markupComparison.algorithm.similarity.character;

import edu.arizona.sirls.etc.markupComparison.algorithm.similarity.ISimilarity;
import edu.arizona.sirls.etc.markupComparison.algorithm.similarity.Result;
import edu.arizona.sirls.etc.markupComparison.model.Character;


public class SomeSimilarity implements ISimilarity<edu.arizona.sirls.etc.markupComparison.model.Character> {

	@Override
	public Result getResult(Character a, Character b) {
		return new Result(0);
	}
	
}
