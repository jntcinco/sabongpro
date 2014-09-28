package com.tekusource.sabongpro.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.tekusource.sabongpro.cache.control.CacheControl;
import com.tekusource.sabongpro.cache.control.CachePolicy;
import com.tekusource.sabongpro.constants.SabongProConstants;
import com.tekusource.sabongpro.email.notification.impl.EmailNotificationService;
import com.tekusource.sabongpro.model.RoleType;
import com.tekusource.sabongpro.model.StreamingConfig;
import com.tekusource.sabongpro.model.User;
import com.tekusource.sabongpro.model.UserProfile;
import com.tekusource.sabongpro.model.UserRole;
import com.tekusource.sabongpro.service.StreamingConfigService;
import com.tekusource.sabongpro.service.UserProfileService;
import com.tekusource.sabongpro.service.UserRoleService;
import com.tekusource.sabongpro.service.UserService;
import com.tekusource.sabongpro.util.CommonUtil;
import com.tekusource.sabongpro.validator.RegisterValidator;

@Controller
@RequestMapping(value="/")
public class FrontPageController extends AbstractController {

	@Autowired
	private UserService userService;
	
	@Autowired
	private UserRoleService userRoleService;
	
	@Autowired
	private UserProfileService userProfileService;
	
	@Autowired
	private StreamingConfigService streamingConfigService;
	
	@Resource
	private EmailNotificationService emailNotificationService;
	
	private static final Logger logger = Logger.getLogger(FrontPageController.class);
	
	@Override
	@CacheControl(maxAge=300)
	@RequestMapping(method=RequestMethod.GET)
	public ModelAndView pageInitializer(HttpSession httpSession, ModelMap model) {
		model.addAttribute("userSession", new User());
		return new ModelAndView("home");
	}
	
	@RequestMapping(value="/register", method = RequestMethod.GET)
	public ModelAndView register(HttpSession httpSession, ModelMap model) {
		model.addAttribute(SabongProConstants.USER_MODEL_MAP, new User());
		return new ModelAndView("register", model);
	}
	
	@RequestMapping(value="/about", method = RequestMethod.GET)
	public ModelAndView about(HttpSession httpSession, ModelMap model) {
		// TODO:
		return new ModelAndView("about", model);
	}
	
	@RequestMapping(value="/contact", method = RequestMethod.GET)
	public ModelAndView contact(HttpSession httpSession, ModelMap model) {
		// TODO:
		return new ModelAndView("contact", model);
	}
	
	@RequestMapping(value="/schedule", method = RequestMethod.GET)
	public ModelAndView schedule(HttpSession httpSession, ModelMap model) {
		model.addAttribute("streams", (List<StreamingConfig>) streamingConfigService.getAllStreamingConfigs());
		return new ModelAndView("schedule", model);
	}
	
	@RequestMapping(value="/faqs", method = RequestMethod.GET)
	public ModelAndView faqs(HttpSession httpSession, ModelMap model) {
		// TODO:
		return new ModelAndView("faqs", model);
	}
	
	@CacheControl(policy = { CachePolicy.NO_STORE })
	@RequestMapping(value="/forgot", method = RequestMethod.GET)
	public ModelAndView forgot(HttpSession httpSession, ModelMap model){
		return new ModelAndView("forgot", model);
	}
	
	@CacheControl(policy = { CachePolicy.NO_STORE })
	@RequestMapping(value="/verification", method=RequestMethod.GET)
	public ModelAndView verifyUser(HttpServletRequest request, ModelMap model) {
		viewName = "login";
		if(userService.isUserTokenValid(request.getParameter("username"), request.getParameter("userToken"))) {
			model.addAttribute("notificationMessage", SabongProConstants.USER_NOTIFICATION_CAN_LOGIN);
		} else {
			model.addAttribute("notificationMessage", SabongProConstants.USER_NOTIFICATION_NOT_VERIFIED);
		}
		model.addAttribute("userSession", new User());
		return new ModelAndView(viewName, model);
	}
	
	@CacheControl(policy = { CachePolicy.NO_STORE })
	@RequestMapping(value="/register", method=RequestMethod.POST)
	public ModelAndView register(HttpServletRequest request, HttpSession session, @ModelAttribute("user") User user, BindingResult results) {
		Map<String, String> registerMessages = new HashMap<String, String>();
		RegisterValidator registerValidator = new RegisterValidator();
		registerValidator.validate(user, results);
		viewName = "register";
		
		if(!results.hasErrors()) {
			if(userService.isUserNameExist(user.getUserName())) {
				registerMessages.put("notificationMessage", SabongProConstants.USERNAME_EXIST);
			} else {
				try{
					String userToken = userService.createUserToken(user);
					String encryptedPassword = userService.encryptPassword(user.getPassword());
					user.setPassword(encryptedPassword);
					user.setEnabled(false);
					user.setStreamAllowed(false);
					
					UserRole role = (UserRole) userRoleService.getUserRoleBy(RoleType.GUEST.getDescription());
					user.setUserToken(userToken);
					user.setUserRole(role);
					userService.save(user);
					
					UserProfile profile = new UserProfile();
					profile.setUser(user);
					userProfileService.save(profile);
					
					sendEmailNotification(getVerificationUrl(request), user.getEmail(), user.getUserName(), userToken);
					registerMessages.put("successMessage", SabongProConstants.USER_SAVED);
					registerMessages.put("notificationMessage", SabongProConstants.USER_NOTIFICATION_REGISTERED);
				}catch(Exception e){
					logger.error("Error registering user.", e);
				}
			}
		}
		return new ModelAndView(viewName, registerMessages);
	}
	
	private String getVerificationUrl(HttpServletRequest request){
		StringBuilder urlBuilder = new StringBuilder("http://");
		
		if(request.getServerPort() != 80)
			urlBuilder.append(request.getServerName()).append(":").append(request.getServerPort());
		else
			urlBuilder.append(request.getServerName());
		urlBuilder.append("/sabongpro/verification");
		
		return urlBuilder.toString();
	}
	
	private void sendEmailNotification(String uri, String email, String username, String userToken) {
		StringBuilder url = new StringBuilder();
		url.append(uri).append("?userToken=").append(userToken).append("&username=").append(username);
		
		String message = composeEmailMessage(url.toString(), username);
		
        List<String> recipients = new ArrayList<String>();
        recipients.add(email);
        
        emailNotificationService.sendEmailNotification(recipients, message, SabongProConstants.MAIL_SUBJECT, SabongProConstants.MAIL_USERNAME);
	}
	
	private String composeEmailMessage(String url, String userName){
		StringBuilder message = new StringBuilder("Dear ");
		message.append(userName).append(",<br/><br/>").append(SabongProConstants.MAIL_BODY_PART);
		message.append("<a href=\"").append(url).append("\">").append(url).append("</a><br/><br/>");
		message.append(SabongProConstants.MAIL_SENDER);
		message.append(SabongProConstants.MAIL_FOOTER_SEPARATOR);
		message.append(SabongProConstants.MAIL_FOOTER_NOTE);
		message.append(SabongProConstants.MAIL_FOOTER_SEPARATOR);
		
		return message.toString();
	}
	
	@CacheControl(policy = { CachePolicy.NO_STORE })
	@RequestMapping(value="/emailpwd", method = RequestMethod.POST)
	public ModelAndView emailpassword(HttpServletRequest request){
		Map<String,Object> messages = new HashMap<String,Object>();
		String userName = request.getParameter("userName");
		if(CommonUtil.isBlankOrNull(userName)){
			messages.put("userNameError", "User name must not be empty.");
		}else{
			try{
				User user = userService.getUserByUserName(userName);
				
				List<String> recipients = new ArrayList<String>();
				recipients.add(user.getEmail());
				
				emailNotificationService.sendEmailNotification(recipients, composeEmailMessage(user.getPassword()), SabongProConstants.FORGOT_PASSWORD_EMAIL_SUBJECT, SabongProConstants.MAIL_USERNAME);
				
				messages.put("notificationMessage", "Your password has been email to you. Please check your inbox.");
			}catch(Exception e){
				logger.error("Error getting password for username: " + userName, e);
			}
		}
		return new ModelAndView("forgot", messages);
	}
	
	private String composeEmailMessage(String password){
		StringBuilder message = new StringBuilder();
		message.append(SabongProConstants.FORGOT_PASSWORD_EMAIL_BODY).append(userService.decryptPassword(password));
		message.append("<br/><br/>");
		message.append(SabongProConstants.MAIL_SENDER);
		message.append(SabongProConstants.MAIL_FOOTER_SEPARATOR);
		message.append(SabongProConstants.MAIL_FOOTER_NOTE);
		message.append(SabongProConstants.MAIL_FOOTER_SEPARATOR);
		
		return message.toString();
	}
}
