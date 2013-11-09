package edu.arizona.sirls.etc.markupComparison.io;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.xml.bind.Binder;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.eclipse.persistence.jaxb.JAXBContextFactory;
import org.eclipse.persistence.jaxb.JAXBContextProperties;
import org.w3c.dom.Document;
import org.w3c.dom.Node;

import com.google.inject.Inject;
import com.google.inject.name.Named;

import edu.arizona.sirls.etc.markupComparison.model.DescriptionsFile;
import edu.arizona.sirls.etc.markupComparison.model.DescriptionsFileList;

public class MOXyTreatmentReader implements ITreatmentReader {

	private JAXBContext jaxbContext;
	private DocumentBuilder documentBuilder;
	
	@Inject
	public MOXyTreatmentReader(@Named("BindingFiles")List<String> bindingsFiles)
			throws JAXBException, ParserConfigurationException {
		Map<String, Object> jaxbContextProperties = new HashMap<String, Object>(1);
		jaxbContextProperties.put(JAXBContextProperties.OXM_METADATA_SOURCE , bindingsFiles);
		this.jaxbContext = JAXBContextFactory.createContext(new Class[] {DescriptionsFile.class}, jaxbContextProperties);
		DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
		documentBuilderFactory.setNamespaceAware(true);
		this.documentBuilder = documentBuilderFactory.newDocumentBuilder();
	}

	@Override
	public DescriptionsFileList read(File input) throws IOException {
		List<DescriptionsFile> descriptionsFiles = new LinkedList<DescriptionsFile>();
		if(input.exists() && input.isDirectory()) {			
			for(File inputFile : input.listFiles()) {
				try {
			        Document document = documentBuilder.parse(inputFile);
			        Binder<Node> binder = jaxbContext.createBinder();
			        DescriptionsFile descriptionsFile = (DescriptionsFile) binder.unmarshal(document);
			        descriptionsFile.setFile(inputFile);
					descriptionsFiles.add(descriptionsFile);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		} else {
			throw new IOException("Input directory does not exist or there is a name conflict with a file: " + 
					input.getAbsolutePath());
		}
		return new DescriptionsFileList(descriptionsFiles);
	}

}
