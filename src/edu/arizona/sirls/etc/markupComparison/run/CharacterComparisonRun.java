package edu.arizona.sirls.etc.markupComparison.run;

import java.io.File;
import java.util.LinkedList;
import java.util.List;

import com.google.inject.Inject;

import edu.arizona.sirls.etc.markupComparison.algorithm.ICalculation;
import edu.arizona.sirls.etc.markupComparison.algorithm.similarity.ISimilarity;
import edu.arizona.sirls.etc.markupComparison.algorithm.similarity.Calculation;
import edu.arizona.sirls.etc.markupComparison.algorithm.similarity.Result;
import edu.arizona.sirls.etc.markupComparison.algorithm.similarity.Result;
import edu.arizona.sirls.etc.markupComparison.io.ICalculationPresenter;
import edu.arizona.sirls.etc.markupComparison.io.ITreatmentReader;
import edu.arizona.sirls.etc.markupComparison.model.Description;
import edu.arizona.sirls.etc.markupComparison.model.DescriptionsFile;
import edu.arizona.sirls.etc.markupComparison.model.DescriptionsFileList;
import edu.arizona.sirls.etc.markupComparison.model.Statement;
import edu.arizona.sirls.etc.markupComparison.model.Structure;

public class CharacterComparisonRun implements IRun {

	private ITreatmentReader reader;
	private File input;
	private ISimilarity<edu.arizona.sirls.etc.markupComparison.model.Character> similarity;
	private ICalculationPresenter resultPresenter;

	@Inject
	public CharacterComparisonRun(ITreatmentReader reader, 
			File input, ISimilarity<edu.arizona.sirls.etc.markupComparison.model.Character> similarity, 
			ICalculationPresenter resultPresenter) {
		this.reader = reader;
		this.input = input;
		this.similarity = similarity;
		this.resultPresenter = resultPresenter;
	}
	
	@Override
	public void run() {
		try {
			List<edu.arizona.sirls.etc.markupComparison.model.Character> characters = 
					new LinkedList<edu.arizona.sirls.etc.markupComparison.model.Character>();
			DescriptionsFileList descriptionsFileList = reader.read(input);
			for(DescriptionsFile descriptionsFile : descriptionsFileList.getDescriptionsFiles()) {
				for(Description description : descriptionsFile.getDescriptions()) {
					for(Statement statement : description.getStatements()) {
						for(Structure structure : statement.getStructures()) {
							characters.addAll(structure.getCharacters());
						}
					}
				}
			}
			
			List<Calculation<edu.arizona.sirls.etc.markupComparison.model.Character>> calculations = 
					new LinkedList<Calculation<edu.arizona.sirls.etc.markupComparison.model.Character>>();
			for(int i=0; i<characters.size(); i++) {
				edu.arizona.sirls.etc.markupComparison.model.Character a = characters.get(i);
				for(int j=i+1; j<characters.size(); j++) { 
					edu.arizona.sirls.etc.markupComparison.model.Character b = characters.get(j);
					Calculation<edu.arizona.sirls.etc.markupComparison.model.Character> calculation = 
							new Calculation<edu.arizona.sirls.etc.markupComparison.model.Character>(similarity, a, null, b, null);
					calculation.call();
					calculations.add(calculation);
				}
			}
			
			resultPresenter.present(calculations);
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
}
