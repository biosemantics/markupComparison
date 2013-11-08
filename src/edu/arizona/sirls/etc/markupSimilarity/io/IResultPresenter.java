package edu.arizona.sirls.etc.markupSimilarity.io;

import java.util.List;

import edu.arizona.sirls.etc.markupSimilarity.algorithm.SimilarityResult;

public interface IResultPresenter {

	void present(List<SimilarityResult> results);

}
