package edu.arizona.sirls.etc.markupComparison;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.util.LinkedList;
import java.util.List;

import com.google.inject.AbstractModule;
import com.google.inject.TypeLiteral;
import com.google.inject.name.Names;

import edu.arizona.sirls.etc.markupComparison.algorithm.IComparable;
import edu.arizona.sirls.etc.markupComparison.algorithm.IResult;
import edu.arizona.sirls.etc.markupComparison.algorithm.IResultAlgorithm;
import edu.arizona.sirls.etc.markupComparison.algorithm.setRelation.DeterministicResult;
import edu.arizona.sirls.etc.markupComparison.algorithm.setRelation.IDeterministicSetRelationDeterminer;
import edu.arizona.sirls.etc.markupComparison.algorithm.setRelation.treatment.SomeDeterminer;
import edu.arizona.sirls.etc.markupComparison.algorithm.similarity.ISimilarity;
import edu.arizona.sirls.etc.markupComparison.algorithm.similarity.treatment.CosineSimilarity;
import edu.arizona.sirls.etc.markupComparison.io.ICalculationPresenter;
import edu.arizona.sirls.etc.markupComparison.io.ITreatmentReader;
import edu.arizona.sirls.etc.markupComparison.io.MOXyTreatmentReader;
import edu.arizona.sirls.etc.markupComparison.io.PrintStreamResultPresenter;
import edu.arizona.sirls.etc.markupComparison.model.Structure;
import edu.arizona.sirls.etc.markupComparison.model.TreatmentRoot;
import edu.arizona.sirls.etc.markupComparison.run.DescriptionsComparisonRun;
import edu.arizona.sirls.etc.markupComparison.run.IRun;
import edu.arizona.sirls.etc.markupComparison.run.SelectedSimilaritiesRun;

public class Config extends AbstractModule {

	private List<String> bindings = createBindings();

	@Override
	protected void configure() {
		bind(IRun.class).to(DescriptionsComparisonRun.class);
		bind(ITreatmentReader.class).to(MOXyTreatmentReader.class);
		bind(File.class).annotatedWith(Names.named("input")).toInstance(new File("input"));
		bind(new TypeLiteral<ISimilarity<TreatmentRoot>>() {
		}).to(CosineSimilarity.class);
		//bind(new TypeLiteral<IResultAlgorithm<DeterministicResult, TreatmentRoot>>() { 
		//}).to(SomeDeterminer.class);
		bind(new TypeLiteral<IResultAlgorithm<? extends IResult, TreatmentRoot>>() { 
		}).to(edu.arizona.sirls.etc.markupComparison.algorithm.setRelation.treatment.SomeDeterminer.class);
		bind(new TypeLiteral<IDeterministicSetRelationDeterminer<Structure>>() { 
		}).to(edu.arizona.sirls.etc.markupComparison.algorithm.setRelation.structure.SomeDeterminer.class);
		bind(ICalculationPresenter.class).to(PrintStreamResultPresenter.class);
		bind(PrintStream.class).toInstance(createPrintStream());
		bind(new TypeLiteral<List<String>>() {
		}).annotatedWith(Names.named("BindingFiles")).toInstance(bindings);
	}

	private PrintStream createPrintStream() {
		File file = new File("output2.txt");
		FileOutputStream fos;
		try {
			fos = new FileOutputStream(file, true);
			return new PrintStream(fos);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		return System.out;
	}

	private List<String> createBindings() {
		List<String> result = new LinkedList<String>();
		result.add("resources" + File.separator + "baseBindings.xml");
		result.add("resources" + File.separator + "singleTreatmentDescriptionBindings.xml");
		return result;
	}

}
