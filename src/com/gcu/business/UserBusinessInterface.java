package com.gcu.business;

import com.gcu.data.DatabaseException;
import com.gcu.models.Credentials;
import com.gcu.models.User;

public interface UserBusinessInterface {
	/**
	 * This method will utilize the userDataService and return a user object from the
	 * users in the database.
	 * 
	 * @return User object - User Class
	 */
	public User authenticateUser(Credentials login)throws DatabaseException;
	/**
	 * This method will utilize the userDataService and return a boolean when checking if existing credentials
	 * are in the database
	 * 
	 * @return boolean
	 */
	public boolean authenticateRegistration(User register)throws DatabaseException;
}
