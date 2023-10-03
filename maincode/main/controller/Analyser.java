package main.controller;
import main.model.Resume;
import java.io.IOException;
import java.util.*;

//Move the analysis logic from engine nlp to here.
public class Analyser {
	TokenRes topTen;
	String comment;
	float score;

	// This trains the NLP model. This is done by taking all words in the resumes of a profession and taking the most common ten words.
	Analyser(String prof) throws IOException {
		
		//This is the database part. Fix this. masterAll should have all words in the selected profession.
		String masterAll = "";
		NLPEngine en = new NLPEngine();

		ArrayList<String> allWords = new ArrayList<String>();
		ArrayList<String> tok = en.getTags(masterAll);

		for (String a : tok) {
			allWords.add(a);
		}

		this.topTen = en.topTenGiver(allWords);
	}

	public String generateComments(Resume r) throws IOException {
		
		NLPEngine en = new NLPEngine();
		
		String resumeAsString = r.toString();
		
		ArrayList<String> token_given = en.getTags(resumeAsString);
		ArrayList<String> allWords_given = new ArrayList<String>();
		for (String d : token_given) {
			allWords_given.add(d);
		}

		TokenRes top10List_given = en.topTenGiver(allWords_given);
		
		
		Map<String, Float> topFreq = this.topTen.getFreq();
		ArrayList<String> topTenn = this.topTen.getTen();
		Map<String, Float> topFreq_new = top10List_given.getFreq();
		//ArrayList<String> topTen_new = top10List_given.getTen();

		// ITERATE TO SEE RES
		
		ArrayList<String> badUse = new ArrayList<String>();
		ArrayList<String> noUse = new ArrayList<String>();
		ArrayList<String> highUse = new ArrayList<String>();
		ArrayList<String> goodUse = new ArrayList<String>();

		for (String a : topTenn) {
			
			if (topFreq_new.get(a) == null) {
				noUse.add(a);
			} else if (topFreq_new.get(a) / topFreq.get(a) > 1.1) {
				System.out.println(topFreq_new.get(a) / topFreq.get(a));
				highUse.add(a);
			} else if (topFreq_new.get(a) / topFreq.get(a) > 0.9) {
				System.out.println(topFreq_new.get(a) / topFreq.get(a));
				goodUse.add(a);
			} else {
				System.out.println(topFreq_new.get(a) / topFreq.get(a));
				badUse.add(a);
			}
		}
		
		String comment = "";
		
		comment = "You've used the words " + goodUse.toString().replaceAll("[\\[\\]]","")+" a good amount.\n";
		comment += "You should use " + badUse.toString().replaceAll("[\\[\\]]","")+" more.\n";
		comment += "Meanwhile try using " + highUse.toString().replaceAll("[\\[\\]]","")+" less.\n";
		comment += "To improve the resume even more try using " + noUse.toString().replaceAll("[\\[\\]]","")+".\n";	
		
		return comment;
		
	}

	public float getScore(Resume r) throws IOException {
		
		NLPEngine en = new NLPEngine();
		
		String newAll = r.toString();
		
		ArrayList<String> tok_given = en.getTags(newAll);
		ArrayList<String> allWords_given = new ArrayList<String>();
		for (String d : tok_given) {
			allWords_given.add(d);
		}

		TokenRes top10List_given = en.topTenGiver(allWords_given);
		
		Map<String, Float> topFreq = this.topTen.getFreq();
		ArrayList<String> topTenn = this.topTen.getTen();
		Map<String, Float> topFreq_new = top10List_given.getFreq();
		//ArrayList<String> topTen_new = top10List_given.getTen();

		// ITERATE TO SEE RES
		
		float score = 0.0f;

		for (String a : topTenn) {
			
			if (topFreq_new.get(a) == null) {
				score += 0.0f;
			} else if (topFreq_new.get(a) / topFreq.get(a) > 1.1) {
				score += 0.4f;
			} else if (topFreq_new.get(a) / topFreq.get(a) > 0.9) {
				score += 1.0f;
			} else {
				score += 0.3f;
			}
		}
		
		
		return score;
	}
}

