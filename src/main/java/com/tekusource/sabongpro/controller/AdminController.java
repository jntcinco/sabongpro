package com.tekusource.sabongpro.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.tekusource.sabongpro.model.RoleType;
import com.tekusource.sabongpro.model.StreamingConfig;
import com.tekusource.sabongpro.model.User;
import com.tekusource.sabongpro.service.UserService;
import com.tekusource.sabongpro.util.CommonUtil;
import com.tekusource.sabongpro.validator.StreamingConfigValidator;

@Controller
@RequestMapping(value="/admin")
public class AdminController extends AbstractController {

	@Autowired
	private UserService userService;

	private boolean isValidUser(User user){
		if(user != null){
			if(!CommonUtil.isBlankOrNull(user.getUserName())){
				if(user.getUserRole().getRole().equals(RoleType.ADMIN.getDescription())){
					return true;
				}
			}
		}
		return false;
	}
	
	@Override
	@RequestMapping(value="/management", method = RequestMethod.GET)
	public ModelAndView pageInitializer(HttpSession httpSession, ModelMap model) {
		viewName = "signin";
		User user = (User) httpSession.getAttribute("userSession");
		if(isValidUser(user)){
			viewName = "adminManagement";
			model.put("user", user);
		}
		return new ModelAndView(viewName, model);
	}
	
	@RequestMapping(value="/search", method=RequestMethod.POST)
	public ModelAndView searchUser(HttpServletRequest request, ModelMap model){
		try{
			String searchKey = request.getParameter("search");
			List<User> users = userService.getUsersBy(searchKey, userService.EMAIL);
			if(users == null)
				users = new ArrayList<User>();
			model.put("users", users);
		}catch(Exception e){
			System.err.println(e);
		}
		
		return new ModelAndView("userManagement", model);
	}

	@RequestMapping(value="/user/management", method = RequestMethod.GET)
	public ModelAndView userManagement(HttpSession httpSession, ModelMap model) {
		viewName = "signin";
		try {
			User user = (User) httpSession.getAttribute("userSession");
			if(isValidUser(user)){
				List<User> users = userService.getAllUser();
				model.put("users", users);
				viewName = "userManagement";
			}
		} catch(Exception e){
			System.err.println(e);
		}
		
		return new ModelAndView(viewName, model);
	}
	
	@RequestMapping(value="/streamingConfig", method = RequestMethod.GET)
	public ModelAndView streamingConfig(HttpSession httpSession, ModelMap model) {
		model.addAttribute("config", new StreamingConfig());
		return new ModelAndView("streamingConfig", model);
	}
	
	@RequestMapping(value="/addStreamingConfig", method = RequestMethod.POST)
	public ModelAndView addStreamingConfig(HttpSession httpSession, @ModelAttribute("streamingConfig") StreamingConfig config, 
										   BindingResult results, ModelMap model) {
		StreamingConfigValidator validator = new StreamingConfigValidator();
		validator.validate(config, results);
		
		if(!results.hasErrors()) {
			
		}
		return new ModelAndView("streamingConfig", model);
	}
}
