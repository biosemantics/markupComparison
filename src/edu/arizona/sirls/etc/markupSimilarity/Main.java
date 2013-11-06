package edu.arizona.sirls.etc.markupSimilarity;

import com.google.inject.Guice;
import com.google.inject.Injector;

import edu.arizona.sirls.etc.markupSimilarity.run.IRun;

public class Main {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Config config = new Config();
		Injector injector = Guice.createInjector(config);
        IRun run = injector.getInstance(IRun.class);
        run.run();
	}

}
