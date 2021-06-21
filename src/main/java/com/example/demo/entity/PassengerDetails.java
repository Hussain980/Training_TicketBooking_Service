package com.example.demo.entity;

import javax.persistence.Embeddable;
import javax.validation.constraints.NotEmpty;

@Embeddable
public class PassengerDetails {

	@NotEmpty(message = "userName is required")
	private String userName;
	
	@NotEmpty(message = "aadharId is required")
	private String aadharId;
	
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getAadharId() {
		return aadharId;
	}
	public void setAadharId(String aadharId) {
		this.aadharId = aadharId;
	}
	
	
	
}
