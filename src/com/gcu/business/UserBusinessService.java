package com.gcu.business;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.gcu.data.DatabaseException;
import com.gcu.data.UserDataService;
import com.gcu.models.Credentials;
import com.gcu.models.User;

public class UserBusinessService implements UserBusinessInterface {
      /**
     * Default constructor. 
     */
    public UserBusinessService() {
    }
 // Inject the dataSerice
 	@SuppressWarnings("rawtypes")
 	@Autowired
    UserDataService userDataService;
    
    /**
     * @throws DatabaseException 
     * @see UserBusinessInterface#authenticateUser(User)
     */
 	@Override
	@SuppressWarnings("unchecked")
    public User authenticateUser(Credentials login) throws DatabaseException {
        User loginUser = null;
        // loop to be able to run through the existing users 
        if(userDataService.findBy(login) != null) 
        {
            loginUser = userDataService.findBy(login);
        }
        return loginUser;
            
    }

    /**
     * going to make sure that two users can't be created with the same username
     * @see UserBusinessInterface#authenticateRegistration(User)
     */
 	@Override
	@SuppressWarnings("unchecked")
    public boolean authenticateRegistration(User register) {
        
        // loop to run through existing users
        List<User> allUsers = userDataService.findAll();
        // Check to see if there is a list in the first place
        if(allUsers != null) {
            for (User users : allUsers) 
            {
                // if there is a user with the same username that someone is trying to create, registration will fail
                if(register.getUsername().equals(users.getUsername()))
                {
                    return false;
                }
            }
        }
        
        // add credentials that did not match with existing user to the list
        int result = userDataService.create(register);
        if(result == 2) {
        return true;
        }
        else {
        	return false;
        }
    }
 	@Autowired
	public void setUserDataService(UserDataService userDataService) {
		this.userDataService = userDataService;
	}
}