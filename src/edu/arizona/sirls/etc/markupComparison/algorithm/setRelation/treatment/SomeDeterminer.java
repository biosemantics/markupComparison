package edu.arizona.sirls.etc.markupComparison.algorithm.setRelation.treatment;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import org.tartarus.snowball.ext.PorterStemmer;

import com.google.inject.Inject;

import edu.arizona.sirls.etc.markupComparison.algorithm.setRelation.DeterministicResult;
import edu.arizona.sirls.etc.markupComparison.algorithm.setRelation.IDeterministicSetRelationDeterminer;
import edu.arizona.sirls.etc.markupComparison.model.Description;
import edu.arizona.sirls.etc.markupComparison.model.Relation;
import edu.arizona.sirls.etc.markupComparison.model.Statement;
import edu.arizona.sirls.etc.markupComparison.model.Structure;
import edu.arizona.sirls.etc.markupComparison.model.TreatmentRoot;

public class SomeDeterminer implements IDeterministicSetRelationDeterminer<TreatmentRoot> {

	private IDeterministicSetRelationDeterminer<Structure> structureDeterminer;
	
	@Inject
	public SomeDeterminer(IDeterministicSetRelationDeterminer<Structure> structureDeterminer) {
		this.structureDeterminer = structureDeterminer;
	}
	
	@Override
	public DeterministicResult getResult(TreatmentRoot a, TreatmentRoot b) {		
		Set<Structure> aStructures = getNormalizedStructures(a);
		Set<Structure> bStructures = getNormalizedStructures(b);
		Set<Relation> aRelations = getNormalizedRelations(a);
		Set<Relation> bRelations = getNormalizedRelations(b);
		
		Set<DeterministicResult> childResults = getStructureResults(aStructures, bStructures);
		childResults.add(getRelationsResult(aRelations, bRelations));
	
		boolean congruent = true;
		boolean subset = true;
		boolean superset = true;
		boolean distinct = true;
		
		if(aStructures.size() > bStructures.size() || aRelations.size() > bRelations.size()) {
			subset = false;
			congruent = false;
		}
		if(aStructures.size() < bStructures.size() || aRelations.size() < bRelations.size()) {
			superset = false;
			congruent = false;
		}
		
		for(DeterministicResult structureResult : childResults) {
			if(!structureResult.equals(DeterministicResult.CONGRUENT))
				congruent = false;
			if(!structureResult.equals(DeterministicResult.DISTINCT))
				distinct = false;
			if(!structureResult.equals(DeterministicResult.SUBSET) &&
					!structureResult.equals(DeterministicResult.CONGRUENT))
				subset = false;
			if(!structureResult.equals(DeterministicResult.SUPERSET) &&
					!structureResult.equals(DeterministicResult.CONGRUENT))
				superset = false;
		}

		boolean overlap = !congruent && !distinct && !subset && !superset;
		if(congruent)
			return DeterministicResult.CONGRUENT;
		if(distinct)
			return DeterministicResult.DISTINCT;
		if(subset)
			return DeterministicResult.SUBSET;
		if(superset)
			return DeterministicResult.SUPERSET;
		return DeterministicResult.OVERLAP;
	}
	
	private DeterministicResult getRelationsResult(Set<Relation> aRelations,
			Set<Relation> bRelations) {
		Set<String> aRelationStrings = getRelationStrings(aRelations);
		Set<String> bRelationStrings = getRelationStrings(bRelations);
		Set<String> aRelationStringsCopy = new HashSet<String>(aRelationStrings);
		int numberARelations = aRelationStrings.size();
		aRelationStrings.removeAll(bRelationStrings);
		int aRelationsNotBRelations = aRelationStrings.size();
		int aRelationsBRelations = numberARelations - aRelationsNotBRelations;
		
		Set<String> bRelationStringsCopy = new HashSet<String>(bRelationStrings);
		int numberBRelations = bRelationStrings.size();
		bRelationStrings.removeAll(aRelationStringsCopy);
		int bRelationsNotARelations = bRelationStrings.size();
		int bRelationsARelations = numberBRelations - bRelationsNotARelations;
		
		if(aRelationsBRelations > 0)
			if(numberARelations == aRelationsBRelations)
				if(numberARelations == numberBRelations) 
					return DeterministicResult.CONGRUENT;
				else
					return DeterministicResult.SUPERSET;
			else
				if(numberBRelations > aRelationsBRelations) 
					return DeterministicResult.OVERLAP;
				else 
					return DeterministicResult.SUBSET;
		else
			return DeterministicResult.DISTINCT;
	}

	private Set<String> getRelationStrings(Set<Relation> relations) {
		Set<String> relationStrings = new HashSet<String>();
		for(Relation relation : relations) {
			String relationFromStructure = "null";
			String relationToStructure = "null";
			if(relation.getFromStructure() != null)
				relationFromStructure = relation.getFromStructure().getName();
			if(relation.getToStructure() != null)
				relationToStructure = relation.getToStructure().getName();
			String relationString = relation.getNegation() + "_" + normalize(relation.getName()) + "_" + 
					normalize(relationFromStructure) + "_" + 
					normalize(relationToStructure);
			relationStrings.add(relationString);
		}
		return relationStrings;
	}

	private Set<DeterministicResult> getRelationResults(
			Set<Relation> aRelations, Set<Relation> bRelations) {
		Set<DeterministicResult> results = new HashSet<DeterministicResult>();
		
		for(Relation aRelation : aRelations) {
			for(Relation bRelation : bRelations) {
				//can only be equal to one bRelation since bRelations have been normalized
				if(isEqualRelations(aRelation, bRelation)) {
					//DeterministicResult result = structureDeterminer.getSetRelation(aRelation, bRelation);
					//results.add(result);
				}
			}
		}
		/*a.getFromStructure()
		a.getToStructure()
		a.getName()
		a.getNegation()*/
		return results;
	}

	private Set<DeterministicResult> getStructureResults(
			Set<Structure> aStructures, Set<Structure> bStructures) {
		Set<DeterministicResult> results = new HashSet<DeterministicResult>();
		
		for(Structure aStructure : aStructures) {	
			for(Structure bStructure : bStructures) {				
				//can only be equal to one bStructure since bStructure have been normalized
				if(isEqualStructures(aStructure, bStructure)) {
					DeterministicResult result = structureDeterminer.getResult(aStructure, bStructure);
					results.add(result);
					break;
				}
			}
		}
		return results;		
	}
	

	private Set<Relation> getNormalizedRelations(TreatmentRoot treatment) {
		Set<Relation> relations = new HashSet<Relation>();
		for(Description description : treatment.getDescriptions()) {
			for(Statement statement : description.getStatements()) {
				relations.addAll(statement.getRelations());
			}
		}
		
		List<Relation> relationsList = new LinkedList<Relation>(relations);
		Set<Relation> toRemoveRelations = new HashSet<Relation>();
		for(int i=0; i<relationsList.size(); i++) {
			Relation baseRelation = relationsList.get(i);
			
			if(!toRemoveRelations.contains(baseRelation)) {
				for(int j=i+1; j<relationsList.size(); j++) {
					Relation compareRelation = relationsList.get(j);
					
					if(!toRemoveRelations.contains(compareRelation)) {
						if(isEqualRelations(baseRelation, compareRelation)) {
							combineRelations(baseRelation, compareRelation);
							toRemoveRelations.add(compareRelation);
						}
					}
				}
			}
		}
	
		relations.removeAll(toRemoveRelations);
		return relations;
	}

	private void combineRelations(Relation a, Relation b) {
		//
	}

	private boolean isEqualRelations(Relation a, Relation b) {
		if(normalize(a.getName()).equals(normalize(b.getName())) && 
				a.getNegation().equals(b.getNegation()) && 
				isEqualStructures(a.getFromStructure(), b.getFromStructure()) &&
				isEqualStructures(a.getToStructure(), b.getToStructure())) {
			return true;
		}
		return false;
	}

	private Set<Structure> getNormalizedStructures(TreatmentRoot treatment) {
		Set<Structure> structures = new HashSet<Structure>();
		for(Description description : treatment.getDescriptions()) {
			for(Statement statement : description.getStatements()) {
				structures.addAll(statement.getStructures());
			}
		}
		
		List<Structure> structuresList = new LinkedList<Structure>(structures);
		Set<Structure> toRemoveStructures = new HashSet<Structure>();
		for(int i=0; i<structuresList.size(); i++) {
			Structure baseStructure = structuresList.get(i);
			
			if(!toRemoveStructures.contains(baseStructure)) {
				for(int j=i+1; j<structuresList.size(); j++) {
					Structure compareStructure = structuresList.get(j);
					
					if(!toRemoveStructures.contains(compareStructure)) {
						if(isEqualStructures(baseStructure, compareStructure)) {
							combineStructures(baseStructure, compareStructure);
							toRemoveStructures.add(compareStructure);
						}
					}
				}
			}
		}
	
		structures.removeAll(toRemoveStructures);
		return structures;
	}

	private void combineStructures(Structure a,
			Structure b) {
		a.getCharacters().addAll(b.getCharacters());
		for(Relation relation : b.getToRelations()) {
			relation.setToStructure(a);
			relation.setTo(a.getId());
			a.getToRelations().add(relation);
		}
		for(Relation relation : b.getFromRelations()) {
			relation.setFromStructure(a);
			relation.setFrom(a.getId());
			a.getFromRelations().add(relation);
		}
	}

	private boolean isEqualStructures(Structure a, Structure b) {
		if(a==null || b==null)
			return false;
		//stemming on name? care about constraints? or attached relations?
		return normalize(a.getName()).equals(normalize(b.getName()));
	}
	
	private String normalize(String term) {
		term = term.trim().toLowerCase();
	    PorterStemmer stemmer = new PorterStemmer();
	    stemmer.setCurrent(term);
	    stemmer.stem();
	    return stemmer.getCurrent();
	}

}
