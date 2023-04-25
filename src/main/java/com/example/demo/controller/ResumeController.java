package com.example.demo.controller;



import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import com.example.demo.model.resume;
import com.example.demo.repository.ResumeRepository;

@Controller
public class ResumeController {

    @Autowired
    private ResumeRepository resumeRepository;

    // GET /resumes
    @GetMapping("/resumes")
    public String getAllResumes(Model model) {
        List<resume> resumes = resumeRepository.findAll();
        model.addAttribute("resumes", resumes);
        return "resumes";
    }

    // GET /resumes/{id}
    @GetMapping("/resumes/{id}")
    public ModelAndView getResumeById(@PathVariable(value = "id") int id) {
        ModelAndView mav = new ModelAndView("resume");
        resume resume = resumeRepository.findById(id).get();
        mav.addObject("resume", resume);
        return mav;
    }

    // GET /resumes/new
    @GetMapping("/resumes/new")
    public String displayCreateResumeForm(Model model) {
        resume resume = new resume();
        model.addAttribute("resume", resume);
        return "create-resume";
    }

    // POST /resumes
    @PostMapping("/resumes")
    public String createResume(@ModelAttribute("resume") resume resume, BindingResult result) {
        if (result.hasErrors()) {
            return "create-resume";
        }

        resumeRepository.save(resume);
        return "redirect:/resumes";
    }

    // GET /resumes/{id}/edit
    @GetMapping("/resumes/{id}/edit")
    public ModelAndView displayEditResumeForm(@PathVariable(value = "id") int id) {
        ModelAndView mav = new ModelAndView("edit-resume");
        resume resume = resumeRepository.findById(id).get();
        mav.addObject("resume", resume);
        return mav;
    }

    // POST /resumes/{id}
    @PostMapping("/resumes/{id}")
    public String updateResume(@PathVariable(value = "id") int id, @ModelAttribute("resume") resume resume,
            BindingResult result) {
        if (result.hasErrors()) {
            return "edit-resume";
        }

        resume.setId( (long) id);
        resumeRepository.save(resume);
        return "redirect:/resumes";
    }

    // POST /resumes/{id}/delete
    @PostMapping("/resumes/{id}/delete")
    public String deleteResume(@PathVariable(value = "id") int id) {
        resumeRepository.deleteById(id);
        return "redirect:/resumes";
    }
}
