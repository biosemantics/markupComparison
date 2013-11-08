package edu.arizona.sirls.etc.markupSimilarity.run;

import java.util.LinkedList;
import java.util.List;

import com.google.inject.Inject;

import edu.arizona.sirls.etc.markupSimilarity.algorithm.text.ISimilarityCalculator;
import edu.arizona.sirls.etc.markupSimilarity.io.IResultPresenter;
import edu.arizona.sirls.etc.markupSimilarity.io.Score;
import edu.arizona.sirls.etc.markupSimilarity.io.SimilarityResult;

public class TextSimilarityRun implements IRun {

	private ISimilarityCalculator similarityCalculator;
	private IResultPresenter resultPresenter;

	@Inject
	public TextSimilarityRun(ISimilarityCalculator similarityCalculator, 
			IResultPresenter resultPresenter) {
		this.similarityCalculator = similarityCalculator;
		this.resultPresenter = resultPresenter;
	}
	
	@Override
	public void run() {
		String a = "this is some example text";
		String b = "this example is some text";
		String c = "well i dont think so, example";
		
		try {
			Score ab = similarityCalculator.getSimilarity(a, b);
			Score ac = similarityCalculator.getSimilarity(a, c);
			SimilarityResult abResult = new SimilarityResult(a, "a", b, "b", ab);
			SimilarityResult acResult = new SimilarityResult(a, "a", c, "c", ac);
			List<SimilarityResult> results = new LinkedList<SimilarityResult>();
			results.add(abResult);
			results.add(acResult);
			resultPresenter.present(results);
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
}
