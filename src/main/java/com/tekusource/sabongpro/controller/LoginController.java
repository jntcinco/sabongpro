package com.tekusource.sabongpro.controller;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.CredentialsExpiredException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.web.WebAttributes;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.tekusource.sabongpro.cache.control.CacheControl;
import com.tekusource.sabongpro.cache.control.CachePolicy;
import com.tekusource.sabongpro.service.StreamingConfigService;
import com.tekusource.sabongpro.service.UserProfileService;
import com.tekusource.sabongpro.service.UserService;
import com.tekusource.sabongpro.util.CommonUtil;

@Controller
public class LoginController {

	@Autowired
	private UserService userService;
	
	@Autowired
	private UserProfileService userProfileService;
	
	@Autowired
	private StreamingConfigService streamingConfigService;
	
	private static final Logger logger = Logger.getLogger(LoginController.class);
	
//	@Override
//	@CacheControl(policy = { CachePolicy.NO_STORE })
//	@RequestMapping(method = RequestMethod.GET )
//	public ModelAndView pageInitializer(HttpSession httpSession, ModelMap model) {
//		model.addAttribute("userSession", new User());
//		return new ModelAndView("login", model);
//	}
	
//	@CacheControl(policy = { CachePolicy.NO_STORE })
//	@RequestMapping(value="/logout", method=RequestMethod.GET)
//	public ModelAndView signOut(HttpSession httpSession, ModelMap model) {
//		if(httpSession != null) {
//			httpSession.invalidate();
//			model.addAttribute("userSession", new User());
//		}
//		return new ModelAndView("login").addObject("notificationMessage", "You have been log out.");
//	}
	
//	@CacheControl(policy = { CachePolicy.NO_STORE })
//	@RequestMapping(value="/login", method=RequestMethod.POST)
//	public ModelAndView authenticate(HttpSession httpSession, @ModelAttribute("userSession") User userSession, BindingResult result) {
//		LoginValidator validator = new LoginValidator();
//		validator.validate(userSession, result);
//		Map<String, Object> model = new HashMap<String, Object>();
//		viewName = "login";
//		if(!result.hasErrors()){
//			try{
//				User user = userService.getUserByUserName(userSession.getUserName());
//				if(user.getStatus().equals(StatusType.INACTIVE.getDescription())){
//					model.put("notificationMessage", "Your account is inactive. If you have registered please verify your authenticity by logging in to your email account.");
//				}else{
//					String password = userService.decryptPassword(user.getPassword());
//					if(password.equals(userSession.getPassword())){
//						model.put("user", user);
//						httpSession.setAttribute("userSession", user);
//						if(user.getUserRole().getRole().equals(RoleType.ADMIN.getDescription())) {
//							viewName = "adminManagement";
//						}else if(user.getUserRole().getRole().equals(RoleType.GUEST.getDescription())) {
//							viewName = "profile";
//
//							List<StreamingConfig> configs = (List<StreamingConfig>) streamingConfigService.getStreamingConfigBy(true);
//							if (!configs.isEmpty()) {
//								model.put("config", configs.get(0));
//							}
//						}
//						UserProfile profile = userProfileService.getUserProfileByUserId(user.getId());
//						model.put("profile", profile);
//						httpSession.setAttribute("profileSession", profile);
//					}else{
//						model.put("notificationMessage", "Invalid username/password. Please try again.");
//					}
//				}
//			}catch(Exception e){
//				logger.error("Error getting user credentials for user: " + userSession.getUserName(), e);
//			}
//		}
//		return new ModelAndView(viewName, model);
//	}
	
	@CacheControl(policy = { CachePolicy.NO_STORE })
	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public ModelAndView login(@RequestParam(value = "error", required = false) String error,
			@RequestParam(value = "logout", required = false) String logout, HttpServletRequest request) {
		
		ModelAndView model = new ModelAndView();
		if(!CommonUtil.isBlankOrNull(error)) {
			if("true".equals(error)){
				model.addObject("notificationMessage", "Invalid username or password.");
			}else{
				model.addObject("notificationMessage", getErrorMessage(request, WebAttributes.AUTHENTICATION_EXCEPTION));
			}
		}

		if (logout != null) {
			model.addObject("notificationMessage", "You've been logged out successfully.");
		}
		model.setViewName("login");

		return model;

	}
	
	// customize the error message
	private String getErrorMessage(HttpServletRequest request, String key) {
		String error = "";
		Exception exception = (Exception) request.getSession().getAttribute(key);
		if(exception != null){
			if(exception instanceof BadCredentialsException) {
				error = "Invalid username and password!";
			}else if(exception instanceof LockedException) {
				error = "Your account is locked. Please contact your administrator.";
			}else if(exception instanceof DisabledException){
				error = "Your account is inactive. If you have registered please verify your authenticity by logging in to your email account.";
			}else if(exception instanceof CredentialsExpiredException){
				error = "Your account already expired.";
			}else{
				exception.getMessage();
			}
		}
		return error;
	}
}
