package edu.arizona.sirls.etc.markupComparison.algorithm.similarity.text;

import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.apache.commons.math3.linear.ArrayRealVector;
import org.apache.commons.math3.linear.RealVector;
import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.core.SimpleAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.document.FieldType;
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.index.Terms;
import org.apache.lucene.index.TermsEnum;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.RAMDirectory;
import org.apache.lucene.util.BytesRef;
import org.apache.lucene.util.Version;

import edu.arizona.sirls.etc.markupComparison.algorithm.similarity.ISimilarity;
import edu.arizona.sirls.etc.markupComparison.algorithm.similarity.Result;

public class CosineSimilarity implements ISimilarity<String> {

	@Override
	public Result getSimilarity(String a, String b) {
		try {
	        Directory directory = createIndex(a, b);
	        IndexReader reader = DirectoryReader.open(directory);
	
	        Set<String> terms = new HashSet<>();
	        Map<String, Integer> f1 = getTermFrequencies(reader, 0, terms);
	        Map<String, Integer> f2 = getTermFrequencies(reader, 1, terms);
	        reader.close();
	        RealVector v1 = toRealVector(f1, terms);
	        RealVector v2 = toRealVector(f2, terms);
	        return new Result((v1.dotProduct(v2)) / (v1.getNorm() * v2.getNorm()));
		} catch(Exception e) {
			e.printStackTrace();
		}
		return new Result(-1.0);
	}
    
    private Directory createIndex(String a, String b) throws IOException {
        Directory directory = new RAMDirectory();
        Analyzer analyzer = new SimpleAnalyzer(Version.LUCENE_45);
        IndexWriterConfig indexWriterConfig = new IndexWriterConfig(Version.LUCENE_45, analyzer);
        IndexWriter indexWriter = new IndexWriter(directory, indexWriterConfig);
        addDocument(indexWriter, a);
        addDocument(indexWriter, b);
        indexWriter.close();
        return directory;
    }
    
    private void addDocument(IndexWriter writer, String content) throws IOException {
        Document doc = new Document();
        FieldType typeStored = new FieldType();
        typeStored.setIndexed(true);
        typeStored.setTokenized(true);
        typeStored.setStored(true);
        typeStored.setStoreTermVectors(true);
        typeStored.setStoreTermVectorPositions(true);
        typeStored.freeze();
        Field field = new Field("content", content, typeStored);
        doc.add(field);
        writer.addDocument(doc);
    }

    private Map<String, Integer> getTermFrequencies(IndexReader reader, int docId, Set<String> terms)
            throws IOException {
        Terms vector = reader.getTermVector(docId, "content");
        TermsEnum termsEnum = null;
        termsEnum = vector.iterator(termsEnum);
        Map<String, Integer> frequencies = new HashMap<>();
        BytesRef text = null;
        while ((text = termsEnum.next()) != null) {
            String term = text.utf8ToString();
            int freq = (int) termsEnum.totalTermFreq();
            frequencies.put(term, freq);
            terms.add(term);
        }
        return frequencies;
    }

    public RealVector toRealVector(Map<String, Integer> map, Set<String> terms) {
        RealVector vector = new ArrayRealVector(terms.size());
        int i = 0;
        for (String term : terms) {
            int value = map.containsKey(term) ? map.get(term) : 0;
            vector.setEntry(i++, value);
        }
        return (RealVector) vector.mapDivide(vector.getL1Norm());
    }
}