package com.example.demo.model;

import java.util.Date;

public class Experience {
	private String Title;
	private String Company;
	private Date startDate;
	private Date endDate;
	private String description;
	
	public Experience() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Experience(String Title, String Company,Date startDate,Date endDate,String description) {
//		super();
		this.Title = Title;
		this.Company = Company;
		this.startDate= startDate;
		this.endDate = endDate ;
		this.description =description;
	}
	public void setTitle(String Title) {
		this.Title=Title;
	}
	public String getTitle() {
		return Title;
	}
	public void getCompany(String Company) {
		this.Company = Company;
	}
	public String getCompany() {
		return Company;
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