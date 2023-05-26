package com.example.demo.model;

import java.util.Date;

public class Education {
	private String Degree;
	private String Subject;
	private String School;
	private Date startDate;
	private Date endDate;
	private String description;
	
	public Education()
	{
		this.Degree = "btech";
		this.School = "carmel";
		this.startDate= new Date();
		this.endDate = new Date() ;
		this.description = "Did a btech";
	}
	public Education(String Degree, String School,Date startDate,Date endDate,String description) 
	{
		this.Degree = Degree;
		this.School = School;
		this.startDate= startDate;
		this.endDate = endDate ;
		this.description =description;
	}
	
	public String getDegree() 
	{
		return Degree;
	}
	public void setDegree(String deg) 
	{
		this.Degree = deg;
	}
	
	public String getSubject() {
		return Subject;
	}
	public void setSubject(String subject) {
		Subject = subject;
	}
	
	public String getSchool() 
	{
		return School;
	}
	public void setSchool(String School) 
	{
		this.School = School;
	}
	
	public Date getStart() 
	{
		return startDate;
	}
	public void setStart(Date startDate) 
	{
		this.startDate = startDate;
	}
	
	public Date getEnd() 
	{
		return endDate;
	}
	public void setEnd(Date endDate) 
	{
		this.endDate = endDate;
	}
	
	
	public String getDescription() 
	{
		return description;
	}
	public void setDescription(String desc) 
	{
		this.description = desc;
		
	}
	
	public String toString()
	{
		return "Studied at "+School+" and was awarded a "+Degree+" in "+Subject+" ("+startDate+" - "+endDate+"). "+description;
	}
}