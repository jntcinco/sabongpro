package com.tekusource.sabongpro.controller;

import java.util.ArrayList;
import java.util.Calendar;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.tekusource.sabongpro.constants.ServiceConstants;
import com.tekusource.sabongpro.model.RoleType;
import com.tekusource.sabongpro.model.StreamingConfig;
import com.tekusource.sabongpro.model.StreamingStatusType;
import com.tekusource.sabongpro.model.User;
import com.tekusource.sabongpro.model.UserProfile;
import com.tekusource.sabongpro.service.StreamingConfigService;
import com.tekusource.sabongpro.service.UserProfileService;
import com.tekusource.sabongpro.service.UserService;
import com.tekusource.sabongpro.util.CommonUtil;
import com.tekusource.sabongpro.validator.StreamingConfigValidator;

@Controller
@RequestMapping(value="/admin")
public class AdminController extends AbstractController {

	@Autowired
	private UserService userService;
	
	@Autowired
	private UserProfileService userProfileService;
	
	@Autowired
	private StreamingConfigService streamingConfigService;

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
		viewName = "login";
		User user = (User) httpSession.getAttribute("userSession");
		if(isValidUser(user)){
			viewName = "adminManagement";
			model.put("user", user);
		} else {
			model.addAttribute("userSession", new User());
		}
		return new ModelAndView(viewName, model);
	}
	
	@RequestMapping(value="/search", method=RequestMethod.POST)
	public ModelAndView searchUser(HttpServletRequest request, ModelMap model){
		try{
			String searchKey = request.getParameter("search");
			List<User> users = userService.getUsersBy(searchKey, ServiceConstants.EMAIL);
			if(users == null)
				users = new ArrayList<User>();
			model.put("users", users);
		}catch(Exception e){
			System.err.println(e);
		}
		
		return new ModelAndView("userManagement", model);
	}
	
	@RequestMapping(value="/user/allow/access", method=RequestMethod.POST)
	public ModelAndView userAllowAccess(@RequestParam("userId") String id, ModelMap model){
		try{
			User user = (User) userService.getUserBy(Long.parseLong(id));
			if(user != null) {
				
				UserProfile profile = (UserProfile) userProfileService.getUserProfileByUserId(Long.parseLong(id));
				profile.setStreamAllowed(true);;
				userProfileService.update(profile);
			}
		}catch(Exception e){
			System.err.println(e);
		}
		List<User> users = userService.getAllUser();
		model.put("users", users);
		return new ModelAndView("userManagement", model);
	}

	@RequestMapping(value="/user/management", method = RequestMethod.GET)
	public ModelAndView userManagement(HttpSession httpSession, ModelMap model) {
		viewName = "login";
		try {
			User user = (User) httpSession.getAttribute("userSession");
			if(isValidUser(user)){
				List<User> users = userService.getAllUser();
				model.put("users", users);
				viewName = "userManagement";
			} else {
				model.addAttribute("userSession", new User());
			}
		} catch(Exception e){
			System.err.println(e);
		}
		
		return new ModelAndView(viewName, model);
	}
	
	@RequestMapping(value="/streaming/config", method = RequestMethod.GET)
	public ModelAndView streamingConfig(HttpSession httpSession, ModelMap model) {
		User user = (User) httpSession.getAttribute("userSession");
		if(isValidUser(user)){
			model.addAttribute("config", new StreamingConfig());
			viewName = "streamingConfig";
		} else {
			model.addAttribute("userSession", new User());
			viewName = "login";
		}
		return new ModelAndView(viewName, model);
	}
	
	@RequestMapping(value="/streaming/config/add", method = RequestMethod.POST)
	public ModelAndView addStreamingConfig(HttpSession httpSession, @ModelAttribute("config") StreamingConfig config, 
										   BindingResult results, ModelMap model) {
		StreamingConfigValidator validator = new StreamingConfigValidator();
		validator.validate(config, results);
		
		if(!results.hasErrors()) {
			config.setDateCreated(Calendar.getInstance());
			config.setDateLastUpdated(Calendar.getInstance());
			config.setStatus(StreamingStatusType.COMING.getDescription());
			streamingConfigService.save(config);
			model.addAttribute("notificationMessage", "Streaming config successfully saved.");
			viewName = "streamingConfigManagement";
		} else {
			viewName = "streamingConfig";
		}
		return new ModelAndView(viewName, model);
	}
	
	@RequestMapping(value="/streaming/config/update", method = RequestMethod.POST)
	public ModelAndView updateStreamingConfig(HttpSession httpSession, @ModelAttribute("config") StreamingConfig config, 
										      BindingResult results, ModelMap model) {
		StreamingConfigValidator validator = new StreamingConfigValidator();
		validator.validate(config, results);
		
		if(!results.hasErrors()) {
			streamingConfigService.update(config);
			model.put("configs", streamingConfigService.getAllStreamingConfigs());
			model.addAttribute("notificationMessage", "Streaming config successfully updated.");
			viewName = "streamingConfigManagement";
		} else {
			viewName = "updateStreamingConfig";
		}
		return new ModelAndView(viewName, model);
	}
	
	@RequestMapping(value="/streaming/config/update/prep", method = RequestMethod.GET)
	public ModelAndView prepUpdateStreamingConfig(HttpSession httpSession, @RequestParam("id") String id, ModelMap model) {
		User user = (User) httpSession.getAttribute("userSession");
		if(isValidUser(user)){
			StreamingConfig config = (StreamingConfig) streamingConfigService.getStreamingConfigBy(Long.parseLong(id));
			if(config == null) {
				config = new StreamingConfig();
			}
			model.addAttribute("config", config);
			viewName = "updateStreamingConfig";
		} else {
			model.addAttribute("userSession", new User());
			viewName = "login";
		}
		return new ModelAndView(viewName, model);
	}
	
	@RequestMapping(value="/streaming/config/management", method = RequestMethod.GET)
	public ModelAndView streamingConfigManagement(HttpSession httpSession, ModelMap model) {
		User user = (User) httpSession.getAttribute("userSession");
		if(isValidUser(user)){
			model.put("configs", streamingConfigService.getAllStreamingConfigs());
			viewName = "streamingConfigManagement";
		} else {
			model.addAttribute("userSession", new User());
			viewName = "login";
		}
		return new ModelAndView(viewName, model);
	}
}
