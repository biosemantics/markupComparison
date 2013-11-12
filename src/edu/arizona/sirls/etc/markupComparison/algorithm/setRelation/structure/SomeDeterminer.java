package edu.arizona.sirls.etc.markupComparison.algorithm.setRelation.structure;

import java.util.HashSet;
import java.util.Set;

import org.tartarus.snowball.ext.PorterStemmer;

import edu.arizona.sirls.etc.markupComparison.algorithm.setRelation.DeterministicResult;
import edu.arizona.sirls.etc.markupComparison.algorithm.setRelation.IDeterministicSetRelationDeterminer;
import edu.arizona.sirls.etc.markupComparison.algorithm.setRelation.IProbabilisticSetRelationDeterminer;
import edu.arizona.sirls.etc.markupComparison.algorithm.setRelation.ProbabilisticResult;
import edu.arizona.sirls.etc.markupComparison.model.Structure;

public class SomeDeterminer implements IDeterministicSetRelationDeterminer<Structure> {

	@Override
	public DeterministicResult getResult(Structure a, Structure b) {
		if(this.normalize(a.getName()).equals(this.normalize(b.getName()))) {
			Set<String> aCharacters = new HashSet<String>();
			for(edu.arizona.sirls.etc.markupComparison.model.Character character : a.getCharacters()) {
				if((character.getCharType()==null || !character.getCharType().equals("range_value")) &&
						!character.getName().equals("atypical_size")) {
					aCharacters.add(normalize(character.getValue()) + normalize(character.getName()));
				}
			}
			
			Set<String> bCharacters = new HashSet<String>();
			for(edu.arizona.sirls.etc.markupComparison.model.Character character : b.getCharacters()) {
				if((character.getCharType()==null || !character.getCharType().equals("range_value")) &&
						!character.getName().equals("atypical_size")) {
					bCharacters.add(normalize(character.getValue()) + normalize(character.getName()));
				}
			}
			
			Set<String> aCharactersCopy = new HashSet<String>(aCharacters);
			int numberACharacters = aCharacters.size();
			aCharacters.removeAll(bCharacters);
			int aCharactersNotBCharacters = aCharacters.size();
			int aCharactersBCharacters = numberACharacters - aCharactersNotBCharacters;
			
			Set<String> bCharactersCopy = new HashSet<String>(bCharacters);
			int numberBCharacters = bCharacters.size();
			bCharacters.removeAll(aCharactersCopy);
			int bCharactersNotACharacters = bCharacters.size();
			int bCharactersACharacters = numberBCharacters - bCharactersNotACharacters;
			
			if(numberACharacters == 0 && numberBCharacters == 0)
				return DeterministicResult.CONGRUENT;
			
			if(aCharactersBCharacters > 0)
				if(numberACharacters == aCharactersBCharacters)
					if(numberACharacters == numberBCharacters) 
						return DeterministicResult.CONGRUENT;
					else
						return DeterministicResult.SUPERSET;
				else
					if(numberBCharacters > aCharactersBCharacters) 
						return DeterministicResult.OVERLAP;
					else 
						return DeterministicResult.SUBSET;
			else
				return DeterministicResult.DISTINCT;
		} else {
			return DeterministicResult.DISTINCT;
		}
	}
	
	private String normalize(String term) {
		term = term.trim().toLowerCase();
	    PorterStemmer stemmer = new PorterStemmer();
	    stemmer.setCurrent(term);
	    stemmer.stem();
	    return stemmer.getCurrent();
	}

}
