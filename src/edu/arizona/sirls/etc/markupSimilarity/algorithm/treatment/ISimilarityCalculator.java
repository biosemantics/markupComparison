package edu.arizona.sirls.etc.markupSimilarity.algorithm.treatment;

import edu.arizona.sirls.etc.markupSimilarity.io.Score;
import edu.arizona.sirls.etc.markupSimilarity.model.TreatmentRoot;

public interface ISimilarityCalculator {

	public Score getSimilarity(TreatmentRoot a, TreatmentRoot b);
	
}
