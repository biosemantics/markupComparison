package edu.arizona.sirls.etc.markupSimilarity.algorithm.character;

import edu.arizona.sirls.etc.markupSimilarity.algorithm.ISimilarity;
import edu.arizona.sirls.etc.markupSimilarity.algorithm.Score;


public class SomeSimilarity implements ISimilarity<edu.arizona.sirls.etc.markupSimilarity.model.Character> {

	@Override
	public Score getSimilarity(edu.arizona.sirls.etc.markupSimilarity.model.Character a, 
			edu.arizona.sirls.etc.markupSimilarity.model.Character b) {
		
		return new Score(0, this.getClass());
	}
	
}
