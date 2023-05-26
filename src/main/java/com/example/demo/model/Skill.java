package com.example.demo.model;

public class Skill {

	private String name;
	private int duration;
	private String description;
	
	public Skill()
	{
		this.name = "default skill";
		this.duration = 0;
		this.description = "regular skills ig";
	}
	
	public Skill(String name,int dur,String desc)
	{
		this.name = name;
		this.duration = dur;
		this.description = desc;
	}
	
	public String getDescription() 
	{
		return description;
	}
	public void setDescription(String description) 
	{
		this.description = description;
	}
	
	public int getDuration() 
	{
		return duration;
	}
	public void setDuration(int duration) 
	{
		this.duration = duration;
	}
	
	public String getName() 
	{
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	public String toString()
	{
		return name+" for "+duration+" years.\n"+description;
	}
}
