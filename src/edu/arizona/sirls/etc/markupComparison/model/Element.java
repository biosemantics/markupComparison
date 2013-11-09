package edu.arizona.sirls.etc.markupComparison.model;

import java.io.StringWriter;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;

import org.eclipse.persistence.jaxb.JAXBContextFactory;

public abstract class Element {
	
	private static Marshaller marshaller = null; 
	
	static {
		try {
			JAXBContext jaxbContext = JAXBContextFactory.createContext(
					new Class[] {Element.class, Statement.class, Structure.class, Character.class, Relation.class }, 
					null);
			marshaller = jaxbContext.createMarshaller();
			marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public boolean isRelation() {
		return this instanceof Relation;
	}
	
	public boolean isCharacter() {
		return this instanceof Character;
	}
	
	public boolean isStructure() {
		return this instanceof Structure;
	}
	
	public boolean isDescription() {
		return this instanceof Description;
	}

	public boolean isStatement() {
		return this instanceof Statement;
	}
	
	public boolean isMeta() {
		return this instanceof Meta;
	}
	
	public boolean isOfType(Class<? extends Element> elementType) {
		return this.getClass().equals(elementType);
	}

	public boolean isNamedElement() {
		return this instanceof NamedElement;
	}

	public abstract void removeElementRecursively(Element element);
	
	@Override
	public String toString() {
		try {
			StringWriter stringWriter = new StringWriter();
			marshaller.marshal(this, stringWriter);
			return stringWriter.toString();
		} catch(Exception e) {
			e.printStackTrace();
		}
		return super.toString();
	}

}
