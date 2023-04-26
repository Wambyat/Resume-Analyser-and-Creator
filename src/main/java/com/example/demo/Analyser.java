package com.example.demo;
import com.example.demo.repository.TokenRes;
import com.example.demo.repository.EngineNLP;

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
		String skills = "";
		for (resume r : res) {
			masterAll += r.getSummary();
			masterAll += " ";
			masterAll += r.getSkill1();
			masterAll += " ";
			masterAll += r.getSkill2();
			masterAll += " ";
			masterAll += r.getSkill3();
		}
		
		
		EngineNLP en = new EngineNLP();

		ArrayList<String> allWords = new ArrayList<String>();
		ArrayList<String> tok = en.getTags(masterAll);

		for (String a : tok) {
			allWords.add(a);
		}

		this.topTen = en.topTenGiver(allWords);

	}


	public String getComments(resume r) throws IOException {
		
		EngineNLP en = new EngineNLP();
		
		return en.getComments(r);
	}

	public float getScore(resume r) throws IOException {
		
		EngineNLP en = new EngineNLP();
		return en.getScore(r);
	}
}

