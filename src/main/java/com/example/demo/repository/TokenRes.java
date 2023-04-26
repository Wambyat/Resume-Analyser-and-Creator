package com.example.demo.repository;

import java.util.ArrayList;
import java.util.Map;

public class TokenRes {
	Map<String, Float> topFreq;
	ArrayList<String> topTen;

	public TokenRes(Map<String, Float> topFreq, ArrayList<String> topTen) {
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
