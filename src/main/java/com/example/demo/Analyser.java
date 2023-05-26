package com.example.demo;
import com.example.demo.repository.TokenRes;
import com.example.demo.repository.EngineNLP;
import com.example.demo.model.resume;
import java.io.IOException;
import java.util.*;


//Move the analysis logic from engine nlp to here.
public class Analyser {
	static TokenRes topTen;
	String comment;
	float score;

	// This trains the NLP model.
	Analyser(String prof) throws IOException {
		
		//This is the database part. Fix this.
		/*
		ResumeService rs = new ResumeService();
		List<resume> res = rs.search(prof);*/
		String masterAll = "";
		/*
		for (resume r : res) {
			masterAll += resumeToString(r);
		}*/
		
		
		EngineNLP en = new EngineNLP();

		ArrayList<String> allWords = new ArrayList<String>();
		ArrayList<String> tok = en.getTags(masterAll);

		for (String a : tok) {
			allWords.add(a);
		}

		topTen = en.topTenGiver(allWords);

	}
	
	public static String resumeToString(resume r)
	{
		String newAll = "";
		newAll += r.getSummary();
		newAll += " ";
		newAll += r.getSkills();
		
		return newAll;
	}

	public String getComments(resume r) throws IOException {
		
		EngineNLP en = new EngineNLP();
		
		String newAll = resumeToString(r);
		
		ArrayList<String> tok_given = en.getTags(newAll);
		ArrayList<String> allWords_given = new ArrayList<String>();
		for (String d : tok_given) {
			allWords_given.add(d);
		}

		TokenRes top10List_given = en.topTenGiver(allWords_given);
		
		
		Map<String, Float> topFreq = topTen.getFreq();
		ArrayList<String> topTenn = topTen.getTen();
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

	public float getScore(resume r) throws IOException {
		
		EngineNLP en = new EngineNLP();
		
		String newAll = resumeToString(r);
		
		ArrayList<String> tok_given = en.getTags(newAll);
		ArrayList<String> allWords_given = new ArrayList<String>();
		for (String d : tok_given) {
			allWords_given.add(d);
		}

		TokenRes top10List_given = en.topTenGiver(allWords_given);
		
		Map<String, Float> topFreq = topTen.getFreq();
		ArrayList<String> topTenn = topTen.getTen();
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

