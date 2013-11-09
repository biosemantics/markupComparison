package edu.arizona.sirls.etc.markupComparison.run;

import java.util.LinkedList;
import java.util.List;

import com.google.inject.Inject;

import edu.arizona.sirls.etc.markupComparison.algorithm.IResult;
import edu.arizona.sirls.etc.markupComparison.algorithm.similarity.ISimilarity;
import edu.arizona.sirls.etc.markupComparison.algorithm.similarity.Calculation;
import edu.arizona.sirls.etc.markupComparison.algorithm.similarity.Result;
import edu.arizona.sirls.etc.markupComparison.algorithm.similarity.Result;
import edu.arizona.sirls.etc.markupComparison.io.ICalculationPresenter;

public class TextSimilarityRun implements IRun {

	private ISimilarity<String> similarity;
	private ICalculationPresenter resultPresenter;

	@Inject
	public TextSimilarityRun(ISimilarity<String> similarity, 
			ICalculationPresenter resultPresenter) {
		this.similarity = similarity;
		this.resultPresenter = resultPresenter;
	}
	
	@Override
	public void run() {
		String a = "this is some example text";
		String b = "this example is some text";
		String c = "well i dont think so, example";
		
		try {
			Calculation<String> abCalc = new Calculation<String>(similarity, a, "a", b, "b");
			Calculation<String> acCalc = new Calculation<String>(similarity, a, "a", c, "c");
			abCalc.call();
			acCalc.call();
			List<Calculation<String>> results = new LinkedList<Calculation<String>>();
			results.add(abCalc);
			results.add(acCalc);
			resultPresenter.present(results);
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
}
