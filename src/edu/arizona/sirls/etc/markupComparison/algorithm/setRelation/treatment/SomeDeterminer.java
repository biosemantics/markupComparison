package edu.arizona.sirls.etc.markupComparison.algorithm.setRelation.treatment;

import edu.arizona.sirls.etc.markupComparison.algorithm.setRelation.ISetRelationDeterminer;
import edu.arizona.sirls.etc.markupComparison.algorithm.setRelation.Result;
import edu.arizona.sirls.etc.markupComparison.model.Description;
import edu.arizona.sirls.etc.markupComparison.model.Relation;
import edu.arizona.sirls.etc.markupComparison.model.Statement;
import edu.arizona.sirls.etc.markupComparison.model.Structure;
import edu.arizona.sirls.etc.markupComparison.model.TreatmentRoot;

public class SomeDeterminer implements ISetRelationDeterminer<TreatmentRoot> {

	private ISetRelationDeterminer<Relation> relationSimilarity;
	private ISetRelationDeterminer<Structure> structureSimilarity;
	
	public SomeDeterminer(ISetRelationDeterminer<Relation> relationSimilarity, ISetRelationDeterminer<Structure> 
		structureSimilarity) {
		this.relationSimilarity = relationSimilarity;
		this.structureSimilarity = structureSimilarity;
	}
	
	@Override
	public Result getSetRelation(TreatmentRoot a, TreatmentRoot b) {
		Description aDescription = a.getDescriptions().get(0);
		Description bDescription = b.getDescriptions().get(0);
		for(Statement statement : aDescription.getStatements()) {
			for(Structure structure : statement.getStructures()) {
				for(edu.arizona.sirls.etc.markupComparison.model.Character character : structure.getCharacters()){
					
				}
			}
			for(Relation relation : statement.getRelations()) {
				
			}
		}
		return null;
	}

}
