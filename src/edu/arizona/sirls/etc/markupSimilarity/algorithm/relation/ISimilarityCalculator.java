package edu.arizona.sirls.etc.markupSimilarity.algorithm.relation;

import edu.arizona.sirls.etc.markupSimilarity.io.Score;
import edu.arizona.sirls.etc.markupSimilarity.model.Relation;

public interface ISimilarityCalculator {

	public Score getSimilarity(Relation a, Relation b);
	
}
