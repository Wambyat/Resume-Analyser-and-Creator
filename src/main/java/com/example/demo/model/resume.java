package com.example.demo.model;
import java.util.*;


public class resume {
	private Long id;
	private String Name ;
	private String Phone ;
	private String Summary ;
	
//	@OneToMany(mappedBy = "resume" ,cascade = CascadeType.ALL ,orphanRemoval=true)
//	private List<Experience> experience = new ArrayList<>();
	
//	@OneToMany(mappedBy = "resume" ,cascade = CascadeType.ALL ,orphanRemoval=true)
//	private List<Education> education = new ArrayList<>();
	public List<String> skills;
	
	public resume() {
		super();
		// TODO Auto-generated constructor stub
	}
	public resume(Long id, String name, String Phone, String Summary,List<Experience> experience,List<Education> education,String skill1,String skill2,String skill3) {
		super();
		this.id = id;
		this.Name = name;
		this.Phone = Phone;
		this.Summary = Summary ;
//		this.education = education;
//		this.experience = experience;
		this.skills.add(skill1);
		this.skills.add(skill2);
		this.skills.add(skill3);
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return Name;
	}
	public void setName(String name) {
		Name = name;
	}
	public String getPhone() {
		return Phone;
	}
	public void setPhone(String Phone) {
		this.Phone = Phone;
	}
	public String getSummary() {
		return Summary;
	}
	public void setSummary(String Summary) {
		this.Summary = Summary;
	}
//	public List<Education> getEducation() {
//		return education;
//	}
//	public void setEducation(List<Education> edu) {
//		this.education = edu;
//	}
//	
//	//logic to God
//	public void addEducation(Education edu) {
//		education.add(edu);
//        edu.setResume(this);
//    }
//	public List<Experience> getExperience() {
//		return experience;
//	}
//	public void setExperience(List<Experience> exp) {
//		this.experience = exp;
//	}
	
	//logic to God
//	public void addExperience(Experience exp) {
//		experience.add(exp);
//        exp.setResume(this);
//    }
	public void setskill1(String skill) {
		this.skills.add(skill);
	}
	public String getSkill1() {
		return skills.get(0);
	}
	public void setskill2(String skill) {
		this.skills.add(skill);
	}
	public String getSkill2() {
		return skills.get(1);
	}
	public void setskill3(String skill) {
		this.skills.add(skill);
	}
	public String getSkill3() {
		return skills.get(2);
	}

}