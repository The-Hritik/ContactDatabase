package com.contactdetails.ContactDetails.models;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Positive;


public class CustomerDto {
	
	@NotEmpty(message = "The name is required")
	private String name;
	
	@NotEmpty(message = "The email is required")
	private String email;
	
	@Positive(message = "The phone number is required")
	private long phoneno;
	
	private String dob;
	
	private String city;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public long getPhoneno() {
		return phoneno;
	}

	public void setPhoneno(long phoneno) {
		this.phoneno = phoneno;
	}

	
	public String getDob() {
		return dob;
	}

	public void setDob(String dob) {
		this.dob = dob;
	}
	
	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

}
