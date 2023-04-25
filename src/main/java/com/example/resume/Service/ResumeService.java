package com.example.resume.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.resume.Repository.resumeRepository;
import com.example.resume.model.resume;

import java.util.*;


@Service
public class ResumeService {
	
	@Autowired
	private resumeRepository repo;
	
	
	public List<resume> listAll() {
        return repo.findAll();
    }

	
    public resume get(long id) {
        return repo.findById(id).get();
    }
    
    //Save Resume
    public void saveResume(resume resume) {
        repo.save(resume);
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
