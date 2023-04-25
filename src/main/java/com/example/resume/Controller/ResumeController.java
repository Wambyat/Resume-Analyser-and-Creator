package com.example.resume.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.example.resume.Service.ResumeService;


@Controller
public class ResumeController {
	@Autowired
	
	private ResumeService Service;
	

}
