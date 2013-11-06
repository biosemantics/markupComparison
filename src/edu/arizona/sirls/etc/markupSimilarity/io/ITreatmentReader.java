package edu.arizona.sirls.etc.markupSimilarity.io;

import java.io.File;
import java.util.List;

import edu.arizona.sirls.etc.markupSimilarity.model.Treatment;

public interface ITreatmentReader {

	public List<Treatment> read(File input);
	
}
