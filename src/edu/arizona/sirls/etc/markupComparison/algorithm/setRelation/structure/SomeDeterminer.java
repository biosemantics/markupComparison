package edu.arizona.sirls.etc.markupComparison.algorithm.setRelation.structure;

import java.util.HashSet;
import java.util.Set;

import org.tartarus.snowball.ext.PorterStemmer;

import edu.arizona.sirls.etc.markupComparison.algorithm.setRelation.ISetRelationDeterminer;
import edu.arizona.sirls.etc.markupComparison.algorithm.setRelation.Result;
import edu.arizona.sirls.etc.markupComparison.model.Structure;

public class SomeDeterminer implements ISetRelationDeterminer<Structure> {

	@Override
	public Result getSetRelation(Structure a, Structure b) {
		Set<String> aCharacters = new HashSet<String>();
		for(edu.arizona.sirls.etc.markupComparison.model.Character character : a.getCharacters()) {
			if(!character.getCharType().equals("range_value")) {
				aCharacters.add(normalize(character.getValue() + normalize(character.getName())));
			}
		}
		
		Set<String> bCharacters = new HashSet<String>();
		for(edu.arizona.sirls.etc.markupComparison.model.Character character : b.getCharacters()) {
			if(!character.getCharType().equals("range_value")) {
				bCharacters.add(normalize(character.getValue() + normalize(character.getName())));
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
		
		return null;
	}
	
	private String normalize(String term) {
		term = term.trim().toLowerCase();
	    PorterStemmer stemmer = new PorterStemmer();
	    stemmer.setCurrent(term);
	    stemmer.stem();
	    return stemmer.getCurrent();
	}

}
