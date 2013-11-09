package edu.arizona.sirls.etc.markupComparison.run;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import com.google.common.io.Files;

public class Avg090Above {
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new FileReader("output2.txt"));
		String line;

		int i=0;
		int number = 0;
		double sumValue = 0;
		while((line=br.readLine()) != null) {
			//System.out.println(line.split("=")[1].trim());
			double value = Double.valueOf(line.split("=")[1].trim());
			
			//if(value > 0.9) {
				sumValue += value;
				String firstPart = line.split("=")[0];
				firstPart = firstPart.replace("similarity_CosineSimilarity(", "");
				firstPart = firstPart.replace(")", "");
				String[] treatments = firstPart.split(",");
				
				treatments[0] = treatments[0].replace(" - 0", "");
				treatments[1] = treatments[1].trim().replace(" - 0", "");
				
				/*i++;
				Files.copy(new File("input" + File.separator + treatments[0]), 
						new File("input" + File.separator + "filtered" + File.separator + 
								i + "." + treatments[0]));
				Files.copy(new File("input" + File.separator + treatments[1]), 
						new File("input" + File.separator + "filtered" + File.separator + 
								i + "." + treatments[1]));*/
				
				System.out.println("cosine(" + treatments[0] + ", " + treatments[1] + ") = " + value);
				number++;
			//}
			//System.out.println(value);
		}
		System.out.println("number found " + number);
		System.out.println("avg: " + sumValue / number);
	}

}
