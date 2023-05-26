package com.example.demo.model;

import java.util.Date;

public class Experience {
	private String Title;
	private String Company;
	private Date startDate;
	private Date endDate;
	private String description;
	
	public Experience() 
	{
		this.setTitle("Intern");
		this.setCompany("Company money");
		this.setStart(new Date());
		this.setEnd(new Date());
		this.setDescription("Donkey work");
	}
	
	public Experience(String Title, String Company,Date startDate,Date endDate,String description) 
	{
		this.setTitle(Title);
		this.setCompany(Company);
		this.setStart(startDate);
		this.setEnd(endDate) ;
		this.setDescription(description);
	}

	public String getTitle() 
	{
		return Title;
	}
	public void setTitle(String title) 
	{
		this.Title = title;
	}

	public String getCompany() 
	{
		return Company;
	}
	public void setCompany(String company) 
	{
		this.Company = company;
	}

	public Date getStart() 
	{
		return startDate;
	}
	public void setStart(Date startDate) 
	{
		this.startDate = startDate;
	}

	public Date getEnd() {
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
	public void setDescription(String description) 
	{
		this.description = description;
	}
	
	public String toString()
	{
		return "Worked at "+Company+" as a "+Title+" from "+startDate+" to "+endDate+". "+description;
	}
}