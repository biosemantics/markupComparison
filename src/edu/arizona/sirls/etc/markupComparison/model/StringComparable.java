package edu.arizona.sirls.etc.markupComparison.model;

import edu.arizona.sirls.etc.markupComparison.algorithm.IComparable;

public class StringComparable implements IComparable {

	private String string;

	
	
	public StringComparable(String string) {
		super();
		this.string = string;
	}

	public String getString() {
		return string;
	}

	public void setString(String string) {
		this.string = string;
	}
	
	
	
}
