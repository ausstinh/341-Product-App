package com.gcu.controller;


import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.gcu.business.UserBusinessInterface;
import com.gcu.data.DatabaseException;
import com.gcu.models.Credentials;
import com.gcu.models.User;

@Controller
@RequestMapping("/user")
public class CredentialsController {
	
	UserBusinessInterface userBusinessService;
	/**
	 * Displays the registration form to the user with an empty user object
	 * @return ModelAndView - sends the user to the registration page with a blank user
	 */
	@RequestMapping(path="/registration", method=RequestMethod.GET)
	public ModelAndView displayRegistrationForm()
	{
		return new ModelAndView("Register", "user", new User());
	}
	
	/**
	 * Displays the user the login form and send a empty user to the view in order to fill with information inputed by the user
	 * @return ModelAndView - send the user to the login page with an empty user credentials objects
	 */
	@RequestMapping(path="/login", method=RequestMethod.GET)
	public ModelAndView displayLoginForm()
	{
		return new ModelAndView("Login", "credentials", new Credentials());
	}
	
	/**
	 * method to be able to login the user
	 * @param credentials
	 * @return ModelAndView - attempt to authenticate user with inputed credentials
	 * @throws DatabaseException 
	 */
	@RequestMapping(path="/loginuser", method=RequestMethod.POST)
	public ModelAndView loginUser(@Valid @ModelAttribute("credentials")Credentials credentials, BindingResult results, HttpSession session) throws DatabaseException 
	{	
		if(results.hasErrors())
		{
			return new ModelAndView("Login", "credentials", credentials);
		}
		User user = userBusinessService.authenticateUser(credentials);
		if(user != null) 
		{
			session.setAttribute("user" , user);	
			System.out.println(session.getAttribute("user"));
			return new ModelAndView("index", "user", user);	
		}
		else 
		{
			return new ModelAndView("ErrorPage");
		}
	}
	
	/**
	 * add a new person by going through the business logic in the business service.
	 * @param credentials
	 * @return ModelAndView - attempt to register user with new user fields
	 * @throws DatabaseException 
	 */
	@RequestMapping(path="/registeruser", method=RequestMethod.POST)
	public ModelAndView registerUser(@Valid @ModelAttribute("user")User user, BindingResult results) throws DatabaseException
	{		
		
		if(results.hasErrors())
		{
			return new ModelAndView("Register", "user", user);
		}
		// check credentials to see if user already exists.
		if(userBusinessService.authenticateRegistration(user) == true)
		{
			System.out.print("here");
			return new ModelAndView("Login", "credentials", new Credentials());			
		}
		else 
		{
			return new ModelAndView("ErrorPage");
		}			
	}
	/**
	 * logout active user
	 */
	@RequestMapping(path="/logout", method=RequestMethod.GET)
	public ModelAndView logoutUser(HttpSession session) throws DatabaseException 
	{	
		session.invalidate();
		return new ModelAndView("Login", "credentials", new Credentials());	
	}
	
	@Autowired
	public void setUserBusinessService(UserBusinessInterface userBusinessService) {
		this.userBusinessService = userBusinessService;
	}
}
