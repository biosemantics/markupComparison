package edu.arizona.sirls.etc.markupComparison.model;

public class TaxonIdentification extends Element {

	private String genusName;
	private String speciesName;
	

	public String getGenusName() {
		return genusName;
	}


	public void setGenusName(String genusName) {
		this.genusName = genusName;
	}


	public String getSpeciesName() {
		return speciesName;
	}


	public void setSpeciesName(String speciesName) {
		this.speciesName = speciesName;
	}


	@Override
	public void removeElementRecursively(Element element) {	}
	
	@Override
	public String toString() {
		if(this.speciesName == null || this.speciesName.isEmpty())
			return this.genusName;
		else 
			return this.speciesName;
	}
	
}
