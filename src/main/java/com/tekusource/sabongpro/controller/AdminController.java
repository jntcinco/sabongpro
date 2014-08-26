package com.tekusource.sabongpro.controller;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.tekusource.sabongpro.cache.control.CacheControl;
import com.tekusource.sabongpro.cache.control.CachePolicy;
import com.tekusource.sabongpro.constants.ServiceConstants;
import com.tekusource.sabongpro.model.RoleType;
import com.tekusource.sabongpro.model.StreamingConfig;
import com.tekusource.sabongpro.model.User;
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
	@CacheControl(policy = { CachePolicy.PRIVATE, CachePolicy.MUST_REVALIDATE })
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
	
	@CacheControl(policy = { CachePolicy.NO_STORE })
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
	
	@CacheControl(policy = { CachePolicy.PRIVATE, CachePolicy.MUST_REVALIDATE })
	@RequestMapping(value="/user/allow/access", method=RequestMethod.POST)
	@ResponseBody
	public Map<String, ? extends Object> userAllowAccess(@RequestParam("userId") Long userId, 
														 @RequestParam("streamingAccess") Integer streamingAccess){
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			User user = (User) userService.getUserBy(userId);
			if(user != null) {
				boolean activateStreaming = false;
				if(streamingAccess == 1) {
					activateStreaming = true;
				}
				user.setStreamAllowed(activateStreaming);
				userService.update(user);
				map.put("streamingAccess", user.isStreamAllowed());
				map.put("message", "User profile sucessfully updated.");
			}
		} catch(Exception e) {
			map.put("message", "Error while updating user profile.");
		}
		return map;
	}

	@CacheControl(policy = { CachePolicy.PRIVATE, CachePolicy.MUST_REVALIDATE })
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
	
	@CacheControl(policy = { CachePolicy.PRIVATE, CachePolicy.MUST_REVALIDATE })
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
	
	@CacheControl(policy = { CachePolicy.NO_STORE })
	@RequestMapping(value="/streaming/config/add", method = RequestMethod.POST)
	public ModelAndView addStreamingConfig(HttpSession httpSession, @ModelAttribute("config") StreamingConfig config, 
										   BindingResult results, ModelMap model) {
		StreamingConfigValidator validator = new StreamingConfigValidator();
		validator.validate(config, results);
		
		if(!results.hasErrors()) {
			config.setDateCreated(Calendar.getInstance());
			config.setDateLastUpdated(Calendar.getInstance());
			config.setStreamOnline(false);
			streamingConfigService.save(config);
			model.addAttribute("notificationMessage", "Streaming config successfully saved.");
			viewName = "streamingConfigManagement";
		} else {
			viewName = "streamingConfig";
		}
		return new ModelAndView(viewName, model);
	}
	
	@CacheControl(policy = { CachePolicy.NO_STORE })
	@RequestMapping(value="/streaming/config/update", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, ? extends Object> updateStreamingConfig(@RequestParam("configId") Long configId, @RequestParam("description") String description, 
															   @RequestParam("streamUrl") String streamUrl, @RequestParam("streamStatus") Integer streamStatus) {
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			StreamingConfig config = (StreamingConfig) streamingConfigService.getStreamingConfigBy(configId);
			if(config != null) {
				boolean isStreamOnline = false;
				config.setDescription(description);
				config.setUrl(streamUrl);
				if(streamStatus == 1) {
					isStreamOnline = true;
				}
				config.setStreamOnline(isStreamOnline);
				config.setDateLastUpdated(Calendar.getInstance());
				streamingConfigService.update(config);
				map.put("config", config);
				map.put("message", "Streaming config successfully updated.");
			} else {
				map.put("config", new StreamingConfig());
			}
		} catch(Exception e) {
			map.put("message", "Error while updating streaming details.");
		}
		return map;
	}
	
	@CacheControl(policy = { CachePolicy.NO_STORE })
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
	
	@CacheControl(policy = { CachePolicy.PRIVATE, CachePolicy.MUST_REVALIDATE })
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
