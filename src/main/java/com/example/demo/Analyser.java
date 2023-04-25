package com.example.demo;
//package nlpthing;


import java.io.IOException;
import java.util.*;
import com.example.demo.model.*;

public class Analyser {
	TokenRes topTen;
	String comment;
	float score;

	// Make a constructor that does "training"
	Analyser(String prof) throws IOException {
		ResumeService rs = new ResumeService();
		List<resume> res = rs.search(prof);
		String masterAll = "";
		List<Experience> ex = new ArrayList<Experience>();
		List<Education> ed = new ArrayList<Education>();
		for (resume r : res) {
			masterAll += r.getSummary();
			masterAll += " ";
			ed = r.getedu();
			ex = r.getexp();
			for (Experience e : ex) {
				// LINSON FIX THIS IN THE MAIN CLASSS
				masterAll += e.description();
				masterAll += " ";
			}
			for (Education e1 : ed) {
				// LINSON FIX THIS IN THE MAIN CLASSS
				masterAll += e1.description();
				masterAll += " ";
			}
		}

		TagMaker tm = new TagMaker();

		ArrayList<String> allWords = new ArrayList<String>();
		ArrayList<String> tok = tm.getTags(masterAll);

		for (String a : tok) {
			allWords.add(a);
		}

		this.topTen = topTenGiver(allWords);

	}

	// This gives top 10 words used in the given thingy
	// It also return freq
	public static TokenRes topTenGiver(ArrayList<String> allWords) {
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

		/*
		 * for (String element : top10List) { System.out.println(element + " occurs " +
		 * freqMap.get(element) + " times"); }
		 */

		Map<String, Float> thang = new HashMap<String, Float>();
		for (String element1 : top10List) {
			thang.put(element1, (float) (freqMap.get(element1)) / lenOfTrained);
		}

		/*
		 * for (String element : top10List) { System.out.println(element + " occurs " +
		 * thang.get(element) + " Hz"); }
		 * 
		 * System.out.println("one return for top10");
		 */

		return new TokenRes(thang, top10List);
	}

	public String getComments(resume r) throws IOException {
		
		TagMaker tm = new TagMaker();
		String newAll = "";
		newAll += r.getSummary();
		newAll += " ";

		List<Experience> ex = new ArrayList<Experience>();
		List<Education> ed = new ArrayList<Education>();
		ed = r.getedu();
		ex = r.getexp();
		for (Experience e : ex) {
			// LINSON FIX THIS IN THE MAIN CLASSS
			newAll += e.description();
			newAll += " ";
		}
		for (Education e1 : ed) {
			// LINSON FIX THIS IN THE MAIN CLASSS
			newAll += e1.description();
			newAll += " ";
		}
		
		ArrayList<String> tok_given = tm.getTags(newAll);
		ArrayList<String> allWords_given = new ArrayList<String>();
		for (String d : tok_given) {
			allWords_given.add(d);
		}

		TokenRes top10List_given = topTenGiver(allWords_given);

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
		
		comment = "You've used the words " + goodUse.toString().replaceAll("[\\[\\]]","")+" a good amount.\n";
		comment += "You should use " + badUse.toString().replaceAll("[\\[\\]]","")+" more.\n";
		comment += "Meanwhile try using " + highUse.toString().replaceAll("[\\[\\]]","")+" less.\n";
		comment += "To improve the resume even more try using " + noUse.toString().replaceAll("[\\[\\]]","")+".\n";	
		
		return comment;
	}

	public float getScore(resume r) throws IOException {
		
		TagMaker tm = new TagMaker();
		String newAll = "";
		newAll += r.getSummary();
		newAll += " ";

		List<Experience> ex = new ArrayList<Experience>();
		List<Education> ed = new ArrayList<Education>();
		ed = r.getedu();
		ex = r.getexp();
		for (Experience e : ex) {
			// LINSON FIX THIS IN THE MAIN CLASSS
			newAll += e.description();
			newAll += " ";
		}
		for (Education e1 : ed) {
			// LINSON FIX THIS IN THE MAIN CLASSS
			newAll += e1.description();
			newAll += " ";
		}
		
		ArrayList<String> tok_given = tm.getTags(newAll);
		ArrayList<String> allWords_given = new ArrayList<String>();
		for (String d : tok_given) {
			allWords_given.add(d);
		}

		TokenRes top10List_given = topTenGiver(allWords_given);

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

class TokenRes {
	Map<String, Float> topFreq;
	ArrayList<String> topTen;

	TokenRes(Map<String, Float> topFreq, ArrayList<String> topTen) {
		this.topFreq = topFreq;
		this.topTen = topTen;
	}

	public Map<String, Float> getFreq() {
		return topFreq;
	}

	public ArrayList<String> getTen() {
		return topTen;
	}
}