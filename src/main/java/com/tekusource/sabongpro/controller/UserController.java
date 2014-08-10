package com.tekusource.sabongpro.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.tekusource.sabongpro.model.User;
import com.tekusource.sabongpro.service.UserService;
import com.tekusource.sabongpro.validator.RegisterValidator;

@Controller
@RequestMapping(value = "/user")
public class UserController extends AbstractController {

	@Autowired
	private UserService userService;
	
	@Override
	@RequestMapping(method=RequestMethod.GET)
	public ModelAndView pageInitializer(HttpSession httpSession, ModelMap model) {
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
			if(userService.isUserExist(user.getUsername(), user.getPassword())) {
				registerMessages.put("errorMessage", "User already exist.");
			} else {
				userService.save(user);
				registerMessages.put("successMessage", "User successfully saved.");
				registerMessages.put("notificationMessage", "NOTE: We will send a confirmation e-mail to complete the registration process. You have to click on the link provided in this e-mail to verify the authenticity of account ownership. If you are using your Yahoo, GMail or Hotmail account, please also look in the Bulk or Spam folders. Thank you.");
			}
		}
		return new ModelAndView(viewName, registerMessages);
	}
}
