package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.model.resume;

@Repository
public interface ResumeRepository extends JpaRepository<resume, Integer> {

}
