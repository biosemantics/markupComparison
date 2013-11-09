package edu.arizona.sirls.etc.markupComparison.algorithm;


public abstract class AbstractCalculation<T> implements Comparable<AbstractCalculation<T>> {

	protected T a;
	protected T b;
	protected String aLabel;
	protected String bLabel;
	
	public AbstractCalculation(T a, String aLabel, T b, String bLabel) {
		this.a = a;
		this.b = b;
		this.aLabel = aLabel;
		this.bLabel = bLabel;
	}

	@Override
	public int compareTo(AbstractCalculation<T> o) {
		if(o instanceof AbstractCalculation) {
			AbstractCalculation calculation = (AbstractCalculation)o;
			if(this.aLabel.compareTo(calculation.aLabel) == 0)
				return this.bLabel.compareTo(calculation.bLabel);
			return this.aLabel.compareTo(calculation.aLabel);
		}
		return -1;
	}

	public T getA() {
		return a;
	}

	public void setA(T a) {
		this.a = a;
	}

	public T getB() {
		return b;
	}

	public void setB(T b) {
		this.b = b;
	}

	public String getaLabel() {
		return aLabel;
	}

	public void setaLabel(String aLabel) {
		this.aLabel = aLabel;
	}

	public String getbLabel() {
		return bLabel;
	}

	public void setbLabel(String bLabel) {
		this.bLabel = bLabel;
	}
	
	

	
}
