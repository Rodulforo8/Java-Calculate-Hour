package com.example.models;


import org.springframework.lang.NonNull;

public class TimeZoneModel {
	@NonNull
  private String time;
	@NonNull
  private Integer hours;

  
 public TimeZoneModel() {
	 
 }
 
 

public TimeZoneModel(String time, Integer hours) {
	super();
	this.time = time;
	this.hours = hours;
}


public String getTime() {
	return time;
}

public void setTime(String time) {
	this.time = time;
}

public Integer getHours() {
	return hours;
}

public void setHours(Integer hours) {
	this.hours = hours;
}



  
}
