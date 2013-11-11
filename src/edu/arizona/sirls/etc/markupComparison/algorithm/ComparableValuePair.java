package edu.arizona.sirls.etc.markupComparison.algorithm;


public abstract class ComparableValuePair<V extends IComparable> 
	implements Comparable<ComparableValuePair<V>> {

	protected V a;
	protected V b;
	protected String aLabel;
	protected String bLabel;
	
	public ComparableValuePair(V a, String aLabel, V b, String bLabel) {
		this.a = a;
		this.b = b;
		this.aLabel = aLabel;
		this.bLabel = bLabel;
	}

	@Override
	public int compareTo(ComparableValuePair<V> o) {
		if(o instanceof ComparableValuePair) {
			ComparableValuePair calculation = (ComparableValuePair)o;
			if(this.aLabel.compareTo(calculation.aLabel) == 0)
				return this.bLabel.compareTo(calculation.bLabel);
			return this.aLabel.compareTo(calculation.aLabel);
		}
		return -1;
	}

	public V getA() {
		return a;
	}

	public void setA(V a) {
		this.a = a;
	}

	public V getB() {
		return b;
	}

	public void setB(V b) {
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
