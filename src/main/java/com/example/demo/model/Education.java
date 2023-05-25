package com.example.demo.model;

import java.util.Date;

public class Education {
	private String Degree;
	private String School;
	private Date startDate;
	private Date endDate;
	private String description;
	
	public Education() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Education(String Degree, String School,Date startDate,Date endDate,String description) {
		super();
		this.Degree = Degree;
		this.School = School;
		this.startDate= startDate;
		this.endDate = endDate ;
		this.description =description;
	}
	public void setDegree(String deg) {
		this.Degree=deg;
	}
	public String getDegree() {
		return Degree;
	}
	public void getSchool(String School) {
		this.School = School;
	}
	public String getSchool() {
		return School;
	}
	public void setstartDate(Date startDate) {
		this.startDate = startDate;
	}
	public Date getstartDate() {
		return startDate;
	}
	public void setendDate(Date endDate) {
		this.endDate = endDate;
	}
	public Date getendDate() {
		return endDate;
	}
	public void getdescription(String desc) {
		this.description = desc;
	}
	public String description() {
		return description;
	}
	
	
	
	
}