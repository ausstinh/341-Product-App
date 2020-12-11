package com.gcu.models;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class Credentials 
{
	//Data Validation Annotations
	@NotNull(message="UserName cannot be null.")
	@Size(min=2, max=30, message="UserName must be between 2 and 30 characters.")
	private String username;
	
	//Data Validation Annotations
	@NotNull(message="Password cannot be null.")
	@Size(min=2, max=30, message="Password must be between 2 and 30 characters.")
	private String password;
	
	public Credentials(String userName, String password) 
	{
		
		this.username = userName;
		this.password = password;
	}
	
	public Credentials()
	{
		this.username = "";
		this.password = "";
	}

	public String getUsername() 
	{
		return username;
	}

	public void setUsername(String username) 
	{
		this.username = username;
	}

	public String getPassword() 
	{
		return password;
	}

	public void setPassword(String password) 
	{
		this.password = password;
	}
	@Override
	public String toString() {
		return "User [username=" + username + ", password=" + password +"]";
	}
}
