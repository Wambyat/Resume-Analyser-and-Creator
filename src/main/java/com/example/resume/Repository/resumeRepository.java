package com.example.resume.Repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.resume.model.resume;

@Repository
public interface resumeRepository extends JpaRepository<resume,Long> {
	
}
