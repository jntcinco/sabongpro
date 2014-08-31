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
import com.tekusource.sabongpro.model.StatusType;
import com.tekusource.sabongpro.model.User;
import com.tekusource.sabongpro.model.UserProfile;
import com.tekusource.sabongpro.model.UserRole;
import com.tekusource.sabongpro.service.StreamingConfigService;
import com.tekusource.sabongpro.service.UserProfileService;
import com.tekusource.sabongpro.service.UserRoleService;
import com.tekusource.sabongpro.service.UserService;
import com.tekusource.sabongpro.util.CommonUtil;
import com.tekusource.sabongpro.validator.EditProfileValidator;
import com.tekusource.sabongpro.validator.RegisterValidator;

@Controller
@RequestMapping(value="/guest")
public class GuestController extends AbstractController {
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private UserRoleService userRoleService;
	
	@Autowired
	private UserProfileService userProfileService;
	
	@Resource
	private EmailNotificationService emailNotificationService;
	
	@Autowired
	private StreamingConfigService streamingConfigService;
	
	private static final Logger logger = Logger.getLogger(GuestController.class);

	@CacheControl(policy = { CachePolicy.PRIVATE, CachePolicy.MUST_REVALIDATE }) 
	@RequestMapping(method = RequestMethod.GET)
	public ModelAndView pageInitializer(HttpSession httpSession, ModelMap model) {
		if(isUserSessionValid(httpSession)){
			User user = new User();
			if(httpSession.getAttribute("userSession")!= null)
				user = (User)httpSession.getAttribute("userSession");
			
			model.addAttribute("user", user);
			viewName = "profile";
		}else{
			model.addAttribute("userSession", new User());
			viewName = "login";
		}
		
		return new ModelAndView(viewName, model);
	}
	
	@CacheControl(policy = { CachePolicy.NO_STORE })
	@RequestMapping(value="/livestreaming", method=RequestMethod.GET)
	public ModelAndView liveStreaming(HttpSession httpSession, ModelMap model) {
		if(this.isUserSessionValid(httpSession)) {
			User user = (User) userService.getUserBy(userSession.getId());
			model.put("isStreamAllowed", user.isStreamAllowed());
	        viewName = "livestreaming";
		} else {
			model.addAttribute("userSession", new User());
			viewName = "login";
		}
		return new ModelAndView(viewName, model);
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
	
	@CacheControl(policy = { CachePolicy.PRIVATE, CachePolicy.MUST_REVALIDATE }) 
	@RequestMapping(value="/profileform", method=RequestMethod.GET)
	public ModelAndView profileForm(HttpSession httpSession, ModelMap model){
		if(isUserSessionValid(httpSession)){
			User user = new User();
			UserProfile profile = new UserProfile();
			if(httpSession.getAttribute("userSession")!= null){
				user = (User)httpSession.getAttribute("userSession");
				profile = user.getProfile();
			}
			model.addAttribute("user", user);
			model.addAttribute("profile", profile);
			viewName = "editprofile";
		}else{
			model.addAttribute("userSession", new User());
			viewName = "login";
		}
		return new ModelAndView(viewName, model);
	}
	
	@CacheControl(policy = { CachePolicy.NO_STORE })
	@RequestMapping(value="/editprofile", method=RequestMethod.POST)
	public ModelAndView editProfile(HttpSession session, @ModelAttribute("profile") UserProfile profile, BindingResult results){
		Map<String,Object> model = new HashMap<String,Object>();
		
		if(isUserSessionValid(session)){
			EditProfileValidator validator = new EditProfileValidator();
			validator.validate(profile, results);
			viewName = "editprofile";

			if(!results.hasErrors()){
				try{
					User user = (User) session.getAttribute("userSession");
					UserProfile up = user.getProfile();

					up.setFirstName(profile.getFirstName());
					up.setMiddleName(profile.getMiddleName());
					up.setLastName(profile.getLastName());
					up.setStreet(profile.getStreet());
					up.setCity(profile.getCity());
					up.setZip(profile.getZip());
					up.setContactNumber(profile.getContactNumber());

					userProfileService.update(up);
					model.put("notificationMessage", "Your profile is successfully updated.");

					//update user session copy
					user.setProfile(up);
					session.setAttribute("userSession", user);
				}catch(Exception e){
					logger.error("Error updating profile.", e);
				}
			}
		}else{
			model.put("userSession", new User());
			viewName = "login";
		}
		return new ModelAndView(viewName, model);
	}
	
	@CacheControl(policy = { CachePolicy.PRIVATE, CachePolicy.MUST_REVALIDATE }) 
	@RequestMapping(value="/account", method=RequestMethod.GET)
	public ModelAndView viewAccount(HttpSession session, ModelMap model){
		viewName = "account";
		if(isUserSessionValid(session)){
			User user = (User) session.getAttribute("userSession");
			model.addAttribute("user", user);
		}else{
			viewName = "login";
			model.addAttribute("userSession", new User());
		}
		return new ModelAndView(viewName, model);
	}
	
	@CacheControl(policy = { CachePolicy.NO_STORE })
	@RequestMapping(value="/changepassword", method=RequestMethod.POST)
	public ModelAndView changePassword(HttpServletRequest request, HttpSession session){
		Map<String,Object> messages = new HashMap<String,Object>();
		viewName = "account";
		
		if(isUserSessionValid(session)){
			User user = (User) session.getAttribute("userSession");

			messages = getPasswordErrors(request, user);
			if(messages.isEmpty()){
				try{
					String newPassword = request.getParameter("newPassword");

					user.setPassword(userService.encryptPassword(newPassword));
					userService.update(user);

					//update session copy
					session.setAttribute("userSession", user);

					messages.put("notificationMessage", "Your password is successfully updated.");
				}catch(Exception e){
					messages.put("notificationMessage", "Error updating your password.");
				}
			}
		}else{
			messages.put("userSession", new User());
			viewName = "login";
		}
		return new ModelAndView(viewName, messages);
		
	}
	
	private Map<String,Object> getPasswordErrors(HttpServletRequest request, User user){
		Map<String,Object> errors = new HashMap<String,Object>();
		
		String currentPassword = request.getParameter("currentPassword");
		String newPassword = request.getParameter("newPassword");
		String confirmNewPassword = request.getParameter("confirmNewPassword");
		
		if(CommonUtil.isBlankOrNull(currentPassword)){
			errors.put("currentPasswordError","Current password must not empty.\n");
		}
		if(CommonUtil.isBlankOrNull(newPassword)){
			errors.put("newPasswordError","New password must not empty.\n");
		}
		if(CommonUtil.isBlankOrNull(confirmNewPassword)){
			errors.put("confirmNewPasswordError","Confirm new password must not empty.\n");
		}
		if(!CommonUtil.isBlankOrNull(newPassword)){
			if(!currentPassword.equals(userService.decryptPassword(user.getPassword()))){
				errors.put("notificationMessage","Invalid current password. Please try again.\n");
			}else{
				if(!newPassword.equals(confirmNewPassword)){
					errors.put("notificationMessage","Confirm and new passwords do not match.\n");
				}
			}
		}
		return errors;
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
					user.setStatus(StatusType.INACTIVE.getDescription());
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
		urlBuilder.append("/sabongpro/guest/verification");
		
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
}