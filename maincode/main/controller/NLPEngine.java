package main.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;


public class NLPEngine {

    public ArrayList<String> getTags(String choice) throws IOException {
        TagMaker tm = new TagMaker();
        return tm.getTags(choice);
    }

    // This gives top 10 words used in the given Array and the frequency.
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

}
