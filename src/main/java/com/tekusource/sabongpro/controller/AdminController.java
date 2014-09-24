package com.tekusource.sabongpro.controller;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
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
import com.tekusource.sabongpro.constants.SabongProConstants;
import com.tekusource.sabongpro.constants.ServiceConstants;
import com.tekusource.sabongpro.model.Entry;
import com.tekusource.sabongpro.model.RoleType;
import com.tekusource.sabongpro.model.StreamingConfig;
import com.tekusource.sabongpro.model.User;
import com.tekusource.sabongpro.model.UserProfile;
import com.tekusource.sabongpro.model.UserRole;
import com.tekusource.sabongpro.service.EntryService;
import com.tekusource.sabongpro.service.StreamingConfigService;
import com.tekusource.sabongpro.service.UserProfileService;
import com.tekusource.sabongpro.service.UserRoleService;
import com.tekusource.sabongpro.service.UserService;
import com.tekusource.sabongpro.util.CommonUtil;
import com.tekusource.sabongpro.validator.EntryValidator;
import com.tekusource.sabongpro.validator.RegisterValidator;
import com.tekusource.sabongpro.validator.StreamingConfigValidator;

@Controller
@RequestMapping(value="/admin")
public class AdminController extends AbstractController {

	@Autowired
	private UserService userService;
	
	@Autowired
	private UserRoleService userRoleService;
	
	@Autowired
	private UserProfileService userProfileService;
	
	@Autowired
	private StreamingConfigService streamingConfigService;
	
	@Autowired
	private EntryService entryService;
	
	private static final Logger logger = Logger.getLogger(AdminController.class);

	@Override
	@CacheControl(policy = { CachePolicy.PRIVATE, CachePolicy.MUST_REVALIDATE })
	@RequestMapping(value="/management", method = RequestMethod.GET)
	public ModelAndView pageInitializer(HttpSession httpSession, ModelMap model) {
		viewName = "adminManagement";
		
		String userName = null;

		//get the principal user from security context
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if (!(auth instanceof AnonymousAuthenticationToken)) {
			UserDetails userDetail = (UserDetails) auth.getPrincipal();
			userName = userDetail.getUsername();
		}
		
		try{
			User user = userService.getUserByUserName(userName);
			if(user != null){
				model.put("user", user);
				httpSession.setAttribute("userSession", user);
				isUserSessionValid(httpSession);
				
				UserProfile profile = userProfileService.getUserProfileByUserId(user.getId());
				model.put("profile", profile);
				httpSession.setAttribute("profileSession", profile);
			}
		}catch(Exception e){
			logger.error("Error retreiving and validating user.", e);
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
			logger.error("Error occurred during search.", e);
		}
		return new ModelAndView("userManagement", model);
	}
	
	@CacheControl(policy = { CachePolicy.PRIVATE, CachePolicy.MUST_REVALIDATE })
	@RequestMapping(value="/user/allow/access", method=RequestMethod.POST)
	@ResponseBody
	public Map<String, ? extends Object> userAllowAccess(@RequestParam("userId") Long userId, 
														 @RequestParam("streamingAccess") Integer streamingAccess, @RequestParam("virtualPoints") double virtualPoints){
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			User user = (User) userService.getUserBy(userId);
			if(user != null) {
				boolean activateStreaming = false;
				if(streamingAccess == 1) {
					activateStreaming = true;
				}
				user.setStreamAllowed(activateStreaming);
				user.setVirtualPoints(virtualPoints);
				userService.update(user);
				map.put("streamingAccess", user.isStreamAllowed());
				map.put("message", "User profile sucessfully updated.");
			}
		} catch(Exception e) {
			map.put("message", "Error while updating user profile.");
		}
		return map;
	}
	
//	private Map<String,Object> validateUser(String userName, String email, String password, String confirmPassword){
//		Map<String,Object> errors = new HashMap<String,Object>();
//		if(CommonUtil.isBlankOrNull(userName))
//			errors.put("userNameError","User name must not be empty.");
//		if(CommonUtil.isBlankOrNull(email))
//			errors.put("emailError","Email must not be empty.");
//		if(CommonUtil.isBlankOrNull(password))
//			errors.put("passwordError", "Password must not be empty.");
//		if(CommonUtil.isBlankOrNull(confirmPassword))
//			errors.put("confirmPasswordError","Confirm password must not be empty.");
//		if(!CommonUtil.isBlankOrNull(password) && !CommonUtil.isBlankOrNull(confirmPassword)){
//			if(!password.equals(confirmPassword))
//				errors.put("notificationMessage", "Passwords do not match.");
//		}
//		return errors;
//	}

	@CacheControl(policy = { CachePolicy.PRIVATE, CachePolicy.MUST_REVALIDATE })
	@RequestMapping(value="/user/management", method = RequestMethod.GET)
	public ModelAndView userManagement(HttpSession httpSession, ModelMap model) {
		viewName = "userManagement";
		try {
			List<User> users = userService.getAllUser();
			model.put("users", users);
		} catch(Exception e){
			logger.error("Error accessing user management.",e);
		}
		return new ModelAndView(viewName, model);
	}
	
	@CacheControl(policy = { CachePolicy.PRIVATE, CachePolicy.MUST_REVALIDATE })
	@RequestMapping(value="/streaming/config", method = RequestMethod.GET)
	public ModelAndView streamingConfig(HttpSession httpSession, ModelMap model) {
		model.addAttribute("config", new StreamingConfig());
		viewName = "streamingConfig";
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
			viewName = "streamingConfig";
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
	@RequestMapping(value="/add/user", method=RequestMethod.GET)
	public ModelAndView addUser(){
		ModelAndView model = new ModelAndView();
		model.addObject("user", new User());
		model.setViewName("addUser");
		return model;
	}
	
	@CacheControl(policy = { CachePolicy.NO_STORE })
	@RequestMapping(value="/user/add", method=RequestMethod.POST)
	public ModelAndView saveUser(HttpSession httpSession, @RequestParam("role") String role, @ModelAttribute("user") User user, BindingResult results, ModelMap map){
		viewName = "addUser";
		
		try{
			RegisterValidator registerValidator = new RegisterValidator();
			registerValidator.validate(user, results);
			
			if(!results.hasErrors()){
				if(userService.isUserNameExist(user.getUserName())) {
					map.put("notificationMessage", SabongProConstants.USERNAME_EXIST);
				}else{
					String userToken = userService.createUserToken(user);
					String encryptedPassword = userService.encryptPassword(user.getPassword());
					user.setPassword(encryptedPassword);

					UserRole userRole = (UserRole) userRoleService.getUserRoleBy(role);
					user.setUserToken(userToken);
					user.setUserRole(userRole);
					userService.save(user);

					UserProfile profile = new UserProfile();
					profile.setUser(user);
					userProfileService.save(profile);

					map.put("notificationMessage", SabongProConstants.USER_SAVED);
				}
			}
		}catch(Exception e){
			logger.error("Error adding user.",e);
		}
		return new ModelAndView(viewName, map);
	}
	
	@CacheControl(policy = { CachePolicy.NO_STORE })
	@RequestMapping(value="/streaming/config/update/prep", method = RequestMethod.GET)
	public ModelAndView prepUpdateStreamingConfig(HttpSession httpSession, @RequestParam("id") String id, ModelMap model) {
		viewName = "updateStreamingConfig";
		try{
			StreamingConfig config = (StreamingConfig) streamingConfigService.getStreamingConfigBy(Long.parseLong(id));
			if(config == null) {
				config = new StreamingConfig();
			}
			model.addAttribute("config", config);
			
		}catch(Exception e){
			logger.error("Error preparing config updates.",e);
		}
		return new ModelAndView(viewName, model);
	}
	
	@CacheControl(policy = { CachePolicy.PRIVATE, CachePolicy.MUST_REVALIDATE })
	@RequestMapping(value="/streaming/config/management", method = RequestMethod.GET)
	public ModelAndView streamingConfigManagement(HttpSession httpSession, ModelMap model) {
		try{
			model.put("configs", streamingConfigService.getAllStreamingConfigs());
			viewName = "streamingConfigManagement";
		} catch(Exception e) {
			logger.error("Error accessing config management",e);
		}
		return new ModelAndView(viewName, model);
	}
	
	@CacheControl(policy = { CachePolicy.NO_STORE })
	@RequestMapping(value="/entry/management", method=RequestMethod.GET)
	public ModelAndView entryManagement(HttpSession httpSession, ModelMap map) {
		map.addAttribute("entries", entryService.getAllEntries());
		viewName = "entryManagement";
		map.addAttribute("entry", new Entry());
		return new ModelAndView(viewName, map);
	}
	
	@CacheControl(policy = { CachePolicy.NO_STORE })
	@RequestMapping(value="/entry/prep/add", method=RequestMethod.GET)
	public ModelAndView prepAddEntry(HttpSession httpSession, ModelMap map) {
		map.addAttribute("entry", new Entry());
		return new ModelAndView(viewName, map);
	}
	
	@CacheControl(policy = { CachePolicy.NO_STORE })
	@RequestMapping(value="/entry/add", method=RequestMethod.POST)
	public ModelAndView addEntry(HttpSession httpSession, @ModelAttribute("entry") Entry entry, BindingResult results, ModelMap map){
		EntryValidator entryValidator = new EntryValidator();
		entryValidator.validate(entry, results);
		if(!results.hasErrors()) {
			entryService.save(entry);
			map.put("notificationMessage", SabongProConstants.ENTRY_SAVED);
			map.addAttribute("entry", new Entry());
		} else {
			map.addAttribute("entry", entry);
		}
		viewName = "addEntry";
		return new ModelAndView(viewName, map);
	}
}
