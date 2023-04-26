package com.example.demo.repository;

import com.example.demo.Analyser;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import com.example.demo.repository.TokenRes;
import com.example.demo.model.TagMaker;
import com.example.demo.model.resume;

//this is a facade for the analyser to tag maker. 
public class EngineNLP {

	public ArrayList<String> getTags(String choice) throws IOException {
		TagMaker tm = new TagMaker();
		return tm.getTags(choice);
	}
	
	
	// This gives top 10 words used in the given thingy
	// It also return freq

	public TokenRes topTenGiver(ArrayList<String> allWords) {
		int lenOfTrained = allWords.size();

		Map<String, Integer> freqMap = new HashMap<String, Integer>();
		for (String element : allWords) {
			freqMap.put(element, freqMap.getOrDefault(element, 0) + 1);
		}

		ArrayList<Map.Entry<String, Integer>> sortedList = new ArrayList<>(freqMap.entrySet());

		Collections.sort(sortedList, (a, b) -> b.getValue().compareTo(a.getValue()));

		ArrayList<String> top10List = new ArrayList<String>();
		for (int i = 0; i < 10 && i < sortedList.size(); i++) {
			top10List.add(sortedList.get(i).getKey());
		}

		Map<String, Float> thang = new HashMap<String, Float>();
		for (String element1 : top10List) {
			thang.put(element1, (float) (freqMap.get(element1)) / lenOfTrained);
		}


		return new TokenRes(thang, top10List);
	}
	
public String getComments(resume r) throws IOException {
		
		EngineNLP en = new EngineNLP();
		String newAll = "";
		newAll += r.getSummary();
		newAll += " ";
		newAll += r.getSkill1();
		newAll += " ";
		newAll += r.getSkill2();
		newAll += " ";
		newAll += r.getSkill3();
		
		
		
		ArrayList<String> tok_given = en.getTags(newAll);
		ArrayList<String> allWords_given = new ArrayList<String>();
		for (String d : tok_given) {
			allWords_given.add(d);
		}

		TokenRes top10List_given = en.topTenGiver(allWords_given);

		Map<String, Float> topFreq = topTen.getFreq();
		ArrayList<String> topTenn = topTen.getTen();
		Map<String, Float> topFreq_new = top10List_given.getFreq();
		ArrayList<String> topTen_new = top10List_given.getTen();

		// ITERATE TO SEE RES
		
		float score = 0.0f;
		ArrayList<String> badUse = new ArrayList<String>();
		ArrayList<String> noUse = new ArrayList<String>();
		ArrayList<String> highUse = new ArrayList<String>();
		ArrayList<String> goodUse = new ArrayList<String>();

		for (String a : topTenn) {
			
			if (topFreq_new.get(a) == null) {
				noUse.add(a);
			} else if (topFreq_new.get(a) / topFreq.get(a) > 1.1) {
				System.out.println(topFreq_new.get(a) / topFreq.get(a));
				score += 0.4f;
				highUse.add(a);
			} else if (topFreq_new.get(a) / topFreq.get(a) > 0.9) {
				System.out.println(topFreq_new.get(a) / topFreq.get(a));
				score += 1.0f;
				goodUse.add(a);
			} else {
				System.out.println(topFreq_new.get(a) / topFreq.get(a));
				score += 0.3f;
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
		String newAll = "";
		newAll += r.getSummary();
		newAll += " ";
		newAll += r.getSkill1();
		newAll += " ";
		newAll += r.getSkill2();
		newAll += " ";
		newAll += r.getSkill3();
		
		ArrayList<String> tok_given = en.getTags(newAll);
		ArrayList<String> allWords_given = new ArrayList<String>();
		for (String d : tok_given) {
			allWords_given.add(d);
		}

		TokenRes top10List_given = en.topTenGiver(allWords_given);

		Map<String, Float> topFreq = topTen.getFreq();
		ArrayList<String> topTenn = topTen.getTen();
		Map<String, Float> topFreq_new = top10List_given.getFreq();
		ArrayList<String> topTen_new = top10List_given.getTen();

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
