package com.example.demo.model;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.demo.repository.ResumeRepository;
import com.example.demo.model.resume;

import java.util.*;


@Service
public class ResumeService {
	
	@Autowired
	private ResumeRepository repo;
	
	
	public List<resume> listAll() {
        return repo.findAll();
    }

	
    public resume get(Integer id) {
        return repo.findById(id).get();
    }
    //Search 
	public List<resume> search(String term) {
		List<resume> ans = new ArrayList<resume>();
		List<resume> yo = repo.findAll();
		for(int i=0;i<yo.size();i++) {
			for(int j=0;j<3;j++) {
				if((yo.get(i)).skills.get(0).equalsIgnoreCase(term)) {
					ans.add(yo.get(i));
				}
			}
		}
		return ans;
	}
	
	
}