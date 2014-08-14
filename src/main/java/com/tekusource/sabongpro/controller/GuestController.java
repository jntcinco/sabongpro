package com.tekusource.sabongpro.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.jasypt.spring.security3.PBEPasswordEncoder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.tekusource.sabongpro.email.notification.impl.EmailNotificationService;
import com.tekusource.sabongpro.model.RoleType;
import com.tekusource.sabongpro.model.StatusType;
import com.tekusource.sabongpro.model.User;
import com.tekusource.sabongpro.model.UserRole;
import com.tekusource.sabongpro.service.UserRoleService;
import com.tekusource.sabongpro.service.UserService;
import com.tekusource.sabongpro.util.SabongProConstants;
import com.tekusource.sabongpro.validator.RegisterValidator;

@Controller
@RequestMapping(value="/guest")
public class GuestController extends AbstractController {

	@Autowired
	private UserService userService;
	
	@Autowired
	private UserRoleService userRoleService;
	
	@Autowired
    private PBEPasswordEncoder passwordEncoder;
	
	@Resource
	private EmailNotificationService emailNotificationService;

	@Override
	@RequestMapping(value="/home", method = RequestMethod.GET)
	public ModelAndView pageInitializer(HttpSession httpSession, ModelMap model) {
		// TODO:
		return new ModelAndView("home", model);
	}
	
	@RequestMapping(value="/livestreaming", method=RequestMethod.GET)
	public ModelAndView liveStreaming(HttpSession httpSession, ModelMap model) {
		// TODO:
		return new ModelAndView("livestreaming", model);
	}

	@RequestMapping(value="/register", method = RequestMethod.GET)
	public ModelAndView register(HttpSession httpSession, ModelMap model) {
		model.addAttribute("user", new User());
		return new ModelAndView("register", model);
	}
	
	@RequestMapping(value="/register", method=RequestMethod.POST)
	public ModelAndView register(HttpSession session, @ModelAttribute("user") User user, BindingResult results) {
		Map<String, String> registerMessages = new HashMap<String, String>();
		RegisterValidator registerValidator = new RegisterValidator();
		registerValidator.validate(user, results);
		viewName = "register";
		
		if(!results.hasErrors()) {
			if(userService.isUsernameExist(user.getUsername())) {
				registerMessages.put("notificationMessage", SabongProConstants.USERNAME_EXIST);
			} else {
				String encryptedPassword = passwordEncoder.encodePassword(user.getPassword(), null);
				user.setPassword(encryptedPassword);
				user.setStatus(StatusType.INACTIVE.getDescription());
				UserRole role = (UserRole) userRoleService.getUserRoleBy(RoleType.GUEST.getDescription());
				user.setUserRole(role);
				userService.save(user);
				sendEmailNotification(user.getEmail(), user.getUsername());
				registerMessages.put("successMessage", SabongProConstants.USER_SAVED);
				registerMessages.put("notificationMessage", SabongProConstants.USER_NOTIFICATION);
			}
		}
		return new ModelAndView(viewName, registerMessages);
	}
	
	private void sendEmailNotification(String email, String firstname) {
		String message = "Dear " + firstname + ",<br/><br/>" + SabongProConstants.MAIL_BODY_PART + 
						 SabongProConstants.MAIL_BODY_PART1 + SabongProConstants.MAIL_BODY_PART2 +
						 SabongProConstants.MAIL_BODY_PART3 + SabongProConstants.MAIL_BODY_PART4 +
						 SabongProConstants.MAIL_BODY_PART5 + email + SabongProConstants.MAIL_BODY_PART6 +
						 SabongProConstants.MAIL_BODY_PART7 + SabongProConstants.MAIL_BODY_PART8 +
						 SabongProConstants.MAIL_BODY_PART9 + SabongProConstants.MAIL_BODY_PART10;
        List<String> recipients = new ArrayList<String>();
        recipients.add(email);
        
        emailNotificationService.sendEmailNotification(recipients, message, SabongProConstants.MAIL_SUBJECT, SabongProConstants.MAIL_USERNAME);
	}
}
