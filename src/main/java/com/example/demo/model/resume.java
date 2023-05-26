package com.example.demo.model;
import java.util.*;


public class resume {
	private int id;
	private String Name ;
	private String Phone ;
	private String Summary ;
	private List<Skill> Skills;
	private List<Experience> Exp;
	private List<Education> Edu;
	
	public resume()
	{
		this.id = 0;
		this.Name = "Default";
		this.Phone = "0";
		this.Summary = "Regular preson";
		this.Edu = null;
		this.Exp = null;
		this.Skills = null;
	}
	
	public resume(int id, String name, String Phone, String Summary,List<Experience> experience,List<Education> education,List<Skill> skill) 
	{
		this.id = id;
		this.Name = name;
		this.Phone = Phone;
		this.Summary = Summary ;
		this.Edu = education;
		this.Exp = experience;
		this.Skills = skill;
	}
	
	public int getId() 
	{
		return id;
	}
	public void setId(int id) 
	{
		this.id = id;
	}
	
	public String getName() 
	{
		return Name;
	}
	public void setName(String name) 
	{
		Name = name;
	}
	
	public String getPhone() 
	{
		return Phone;
	}
	public void setPhone(String Phone) 
	{
		this.Phone = Phone;
	}
	
	public String getSummary() 
	{
		return Summary;
	}
	public void setSummary(String Summary) {
		this.Summary = Summary;
	}
	
	public List<Education> getEducation() 
	{
		return Edu;
	}
	public void setEducation(List<Education> edu) 
	{
		this.Edu = edu;
	}
	
	public void addEducation(Education edu) 
	{
		Edu.add(edu);
    }
	public List<Experience> getExperience() 
	{
		return Exp;
	}
	
	public void setExperience(List<Experience> exp) 
	{
		this.Exp = exp;
	}
	public void addExperience(Experience exp) 
	{
		Exp.add(exp);
    }
	
	public void setSkill(Skill skill) 
	{
		this.Skills.add(skill);
	}
	public List<Skill> getSkills() {
		return Skills;
	}

	public String toString()
	{
		return "I am "+Name+"\n Ph No: "+Phone+"\n"+Edu+"\n"+Exp+"\n"+Skills+"\n"+Summary;
	}
}