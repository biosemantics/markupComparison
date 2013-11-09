package edu.arizona.sirls.etc.markupComparison.run;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class Cluster {
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new FileReader("output.txt"));
		String line;
	
		int number = 0;
		while((line=br.readLine()) != null) {
			//System.out.println(line.split("=")[1].trim());
			double value = Double.valueOf(line.split("=")[1].trim());
			
			if(value > 0.9) {
				String firstPart = line.split("=")[0];
				firstPart = firstPart.replace("similarity_CosineSimilarity(", "");
				firstPart = firstPart.replace(")", "");
				String[] treatments = firstPart.split(",");
				
				treatments[0] = treatments[0].replace(" - 0", "");
				treatments[1] = treatments[1].trim().replace(" - 0", "");
				System.out.println("cosine(" + treatments[0] + ", " + treatments[1] + ") = " + value);
				number++;
			}
			//System.out.println(value);
		}
		System.out.println("number found " + number);
		
	}

}
