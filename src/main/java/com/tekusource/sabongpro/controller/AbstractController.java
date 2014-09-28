package com.tekusource.sabongpro.controller;

import javax.servlet.http.HttpSession;

import org.springframework.ui.ModelMap;
import org.springframework.web.servlet.ModelAndView;

import com.tekusource.sabongpro.constants.SabongProConstants;
import com.tekusource.sabongpro.model.User;


public abstract class AbstractController {
	protected String viewName;
	
	public User userSession;
	
	public boolean isUserSessionValid(HttpSession httpSession) {
		userSession = (User) httpSession.getAttribute(SabongProConstants.USER_SESSION);
		
		if(userSession != null) {
			return true;
		} else {
			userSession = new User();
			return false;
		}
	}
	
	public User getUserSession() {
		return userSession;
	}
	
	public void setUserSession(User userSession) {
		this.userSession = userSession;
	}
	
	public abstract ModelAndView pageInitializer(HttpSession httpSession, ModelMap model);
}
