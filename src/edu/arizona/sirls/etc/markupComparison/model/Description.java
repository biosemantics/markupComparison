package edu.arizona.sirls.etc.markupComparison.model;


import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;


public class Description extends Element {

	private String text;
	//private DescriptionsFile descriptionsFile;
	
	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}
	
	/*
	public DescriptionsFile getDescriptionsFile() {
		return descriptionsFile;
	}

	public void setDescriptionsFile(DescriptionsFile descriptionsFile) {
		this.descriptionsFile = descriptionsFile;
	}*/



	// inplace modification, later marshalling out of DescriptionFile again
	private List<Statement> statements = new LinkedList<Statement>();

	public List<Statement> getStatements() {
		return statements;
	}

	public void setStatements(List<Statement> statements) {
		this.statements = statements;
		
		Map<String, Structure> structuresMap = new HashMap<String, Structure>();
		for(Statement statement : statements) {
			for(Structure structure : statement.getStructures()) {
				structuresMap.put(structure.getId(), structure);
			}
		}
		
		Map<String, LinkedHashSet<Relation>> fromRelationMap = new HashMap<String, LinkedHashSet<Relation>>();
		Map<String, LinkedHashSet<Relation>> toRelationMap = new HashMap<String, LinkedHashSet<Relation>>();
		for(Statement statement : statements) {
			for(Relation relation : statement.getRelations()) {
				relation.setFromStructure(structuresMap.get(relation.getFrom()));
				if(!fromRelationMap.containsKey(relation.getFrom()))
					fromRelationMap.put(relation.getFrom(), new LinkedHashSet<Relation>());
				fromRelationMap.get(relation.getFrom()).add(relation);
				relation.setToStructure(structuresMap.get(relation.getTo()));
				if(!toRelationMap.containsKey(relation.getTo()))
					toRelationMap.put(relation.getTo(), new LinkedHashSet<Relation>());
				toRelationMap.get(relation.getTo()).add(relation);
			}
		}
		
		for(Statement statement : statements) {
			for(Structure structure : statement.getStructures()) {
				if(fromRelationMap.containsKey(structure.getId())) {
					structure.setFromRelations(fromRelationMap.get(structure.getId()));
				}
				if(toRelationMap.containsKey(structure.getId())) {
					structure.setToRelations(toRelationMap.get(structure.getId()));
				}
			}
		}
	}

	public void addStatement(Statement statement) {
		this.statements.add(statement);
	}

	@Override
	public void removeElementRecursively(Element element) {
		Iterator<Statement> statementsIterator = statements.iterator();
		while(statementsIterator.hasNext()) {
			Statement statement = statementsIterator.next();
			if(statement.equals(element))
				statementsIterator.remove();
			else
				statement.removeElementRecursively(element);
		}
	}
}
