package edu.arizona.sirls.etc.markupSimilarity;

import java.io.File;
import java.io.PrintStream;

import com.google.inject.AbstractModule;
import com.google.inject.name.Names;

import edu.arizona.sirls.etc.markupSimilarity.algorithm.character.SomeSimilarityCalculator;
import edu.arizona.sirls.etc.markupSimilarity.algorithm.text.CosineSimilarityCalculator;
import edu.arizona.sirls.etc.markupSimilarity.io.IResultPresenter;
import edu.arizona.sirls.etc.markupSimilarity.io.ITreatmentReader;
import edu.arizona.sirls.etc.markupSimilarity.io.MOXyTreatmentReader;
import edu.arizona.sirls.etc.markupSimilarity.io.PrintStreamResultPresenter;
import edu.arizona.sirls.etc.markupSimilarity.run.IRun;
import edu.arizona.sirls.etc.markupSimilarity.run.TextSimilarityRun;

public class Config extends AbstractModule{

	@Override
	protected void configure() {
		bind(IRun.class).to(TextSimilarityRun.class);
		bind(ITreatmentReader.class).to(MOXyTreatmentReader.class);
		bind(File.class).annotatedWith(Names.named("Input")).toInstance(new File("input"));
		bind(edu.arizona.sirls.etc.markupSimilarity.algorithm.character.ISimilarityCalculator.class)
			.to(SomeSimilarityCalculator.class);
		bind(edu.arizona.sirls.etc.markupSimilarity.algorithm.text.ISimilarityCalculator.class)
			.to(CosineSimilarityCalculator.class);
		bind(IResultPresenter.class).to(PrintStreamResultPresenter.class);
		bind(PrintStream.class).toInstance(System.out);
	}

}
