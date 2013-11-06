package edu.arizona.sirls.etc.markupSimilarity.algorithm.relation;

import edu.arizona.sirls.etc.markupSimilarity.model.Relation;

public interface ISimilarityCalculator {

	public double getSimilarity(Relation a, Relation b);
	
}
