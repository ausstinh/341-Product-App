package com.gcu.models;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 * User Session object is the website's way of keeping track of the users information throughout there time on the website
 * with a session scoped class
 *
 */
@Component
@Scope("session")
public class UserSession 
{
	/**
	 * Defining the properties of the class
	 */
	private int userID;
	private int productID;
	private String username;
	private Product product;
	
	/**
	 * Default constructor to initialize the variables
	 */
	public UserSession()
	{
		userID = -1;
		productID = -1;
	}
	
	
	/**
	 * Getters and setters for the properties of the class
	 * @return object - return the object of the property
	 */
	public int getUserID() {
		return userID;
	}
	public void setUserID(int userID) {
		this.userID = userID;
	}
	public int getProductID() {
		return productID;
	}
	public void setProductID(int productID) {
		this.productID = productID;
	}

	public String getUserName() {
		return username;
	}

	public void setUserName(String username) {
		this.username = username;
	}


	public String getUsername() {
		return username;
	}


	public void setUsername(String username) {
		this.username = username;
	}


	public Product getProduct() {
		return product;
	}


	public void setProduct(Product recipe) {
		this.product = recipe;
	}
	
}
