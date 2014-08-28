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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.tekusource.sabongpro.cache.control.CacheControl;
import com.tekusource.sabongpro.cache.control.CachePolicy;
import com.tekusource.sabongpro.constants.SabongProConstants;
import com.tekusource.sabongpro.email.notification.impl.EmailNotificationService;
import com.tekusource.sabongpro.model.StreamingConfig;
import com.tekusource.sabongpro.model.User;
import com.tekusource.sabongpro.service.StreamingConfigService;
import com.tekusource.sabongpro.service.UserService;
import com.tekusource.sabongpro.util.CommonUtil;

@Controller
@RequestMapping(value="/")
public class FrontPageController extends AbstractController {

	@Autowired
	private UserService userService;
	
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
		model.addAttribute("user", new User());
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
