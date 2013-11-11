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
import edu.arizona.sirls.etc.markupComparison.model.StringComparable;

public class TextSimilarityRun implements IRun {

	private ISimilarity<StringComparable> similarity;
	private ICalculationPresenter resultPresenter;

	@Inject
	public TextSimilarityRun(ISimilarity<StringComparable> similarity, 
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
			Calculation<StringComparable> abCalc = 
					new Calculation<StringComparable>(similarity, new StringComparable(a), "a", 
							new StringComparable(b), "b");
			Calculation<StringComparable> acCalc = new Calculation<StringComparable>(similarity, 
					new StringComparable(a), "a", new StringComparable(c), "c");
			abCalc.call();
			acCalc.call();
			List<Calculation<StringComparable>> results = new LinkedList<Calculation<StringComparable>>();
			results.add(abCalc);
			results.add(acCalc);
			resultPresenter.present(results);
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
}
