package edu.arizona.sirls.etc.markupSimilarity;

import java.io.File;
import java.io.PrintStream;
import java.util.LinkedList;
import java.util.List;

import com.google.inject.AbstractModule;
import com.google.inject.TypeLiteral;
import com.google.inject.name.Names;

import edu.arizona.sirls.etc.markupSimilarity.algorithm.ISimilarity;
import edu.arizona.sirls.etc.markupSimilarity.algorithm.treatment.CosineSimilarity;
import edu.arizona.sirls.etc.markupSimilarity.io.IResultPresenter;
import edu.arizona.sirls.etc.markupSimilarity.io.ITreatmentReader;
import edu.arizona.sirls.etc.markupSimilarity.io.MOXyTreatmentReader;
import edu.arizona.sirls.etc.markupSimilarity.io.PrintStreamResultPresenter;
import edu.arizona.sirls.etc.markupSimilarity.model.TreatmentRoot;
import edu.arizona.sirls.etc.markupSimilarity.run.DescriptionsComparisonRun;
import edu.arizona.sirls.etc.markupSimilarity.run.IRun;

public class Config extends AbstractModule{

	private List<String> bindings = createBindings();
	
	@Override
	protected void configure() {
		bind(IRun.class).to(DescriptionsComparisonRun.class);
		bind(ITreatmentReader.class).to(MOXyTreatmentReader.class);
		bind(File.class).annotatedWith(Names.named("input")).toInstance(new File("input"));
		bind(new TypeLiteral<ISimilarity<TreatmentRoot>>() {}).to(CosineSimilarity.class);
		bind(IResultPresenter.class).to(PrintStreamResultPresenter.class);
		bind(PrintStream.class).toInstance(System.out);
		bind(new TypeLiteral<List<String>>() {}).annotatedWith(Names.named("BindingFiles"))
			.toInstance(bindings);
	}

	private List<String> createBindings() {
        List<String> result = new LinkedList<String>();
        result.add("resources" + File.separator + "baseBindings.xml");
        result.add("resources" + File.separator + "singleTreatmentDescriptionBindings.xml");
        return result;
	}

}
