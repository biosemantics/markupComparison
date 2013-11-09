package edu.arizona.sirls.etc.markupComparison.algorithm.setRelation;

import edu.arizona.sirls.etc.markupComparison.algorithm.AbstractCalculation;
import edu.arizona.sirls.etc.markupComparison.io.ICalculation;
import edu.arizona.sirls.etc.markupComparison.io.IResult;

public class Calculation<T> extends AbstractCalculation<T> implements ICalculation<Result> {
	

	private ISetRelationDeterminer<T> determiner;

	public Calculation(ISetRelationDeterminer<T> determiner, T a, String aLabel, T b, String bLabel) {
		super(a, aLabel, b, bLabel);
		this.determiner = determiner;
	}

	@Override
	public Result call() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}
	

}
