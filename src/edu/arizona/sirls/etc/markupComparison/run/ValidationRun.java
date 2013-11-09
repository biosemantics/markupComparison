package edu.arizona.sirls.etc.markupComparison.run;

import java.io.File;

import edu.arizona.sirls.etc.markupComparison.io.XMLValidator;

public class ValidationRun implements IRun {
	
	@Override
	public void run() {
		XMLValidator validator = new XMLValidator();
		File schemaFile = new File("inputSchema.xsd");
		File file = new File("input");
		
		for(File childFile : file.listFiles()) {
			boolean result = validator.validateXMLFileWithSchema(childFile, schemaFile);
			if(!result)
				System.out.println("not valid for " + childFile.getAbsolutePath());
		}
		
	}

}
