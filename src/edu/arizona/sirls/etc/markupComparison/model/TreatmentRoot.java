package edu.arizona.sirls.etc.markupComparison.model;

import java.util.List;

import edu.arizona.sirls.etc.markupComparison.algorithm.IComparable;

public class TreatmentRoot implements IComparable {

	private List<Description> descriptions;
	private TaxonIdentification taxonIdentification;
	private String taxonHierarchy;
	
	public List<Description> getDescriptions() {
		return descriptions;
	}

	public void setDescriptions(List<Description> descriptions) {
		this.descriptions = descriptions;
	}

	public String getTaxonHierarchy() {
		return taxonHierarchy;
	}

	public void setTaxonHierarchy(String taxonHierarchy) {
		this.taxonHierarchy = taxonHierarchy;
	}

	public TaxonIdentification getTaxonIdentification() {
		return taxonIdentification;
	}

	public void setTaxonIdentification(TaxonIdentification taxonIdentification) {
		this.taxonIdentification = taxonIdentification;
	}
	

	
	

}
