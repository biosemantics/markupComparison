package edu.arizona.sirls.etc.markupComparison.model;

import java.util.List;

import edu.arizona.sirls.etc.markupComparison.algorithm.IComparable;

public class TreatmentRoot implements IComparable {

	private List<Description> descriptions;

	public List<Description> getDescriptions() {
		return descriptions;
	}

	public void setDescriptions(List<Description> descriptions) {
		this.descriptions = descriptions;
	}

}
