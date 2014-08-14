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

import com.tekusource.sabongpro.model.RoleType;
import com.tekusource.sabongpro.model.User;
import com.tekusource.sabongpro.service.UserService;

@Controller
@RequestMapping( value = "/" )
public class LoginController extends AbstractController {

	@Autowired
	private UserService userService;
	
	@Override
	@RequestMapping(method = RequestMethod.GET )
	public ModelAndView pageInitializer(HttpSession httpSession, ModelMap model) {
		model.addAttribute("userSession", new User());
		return new ModelAndView("signin", model);
	}
	
	@RequestMapping(value="/logout", method=RequestMethod.GET)
	public ModelAndView signOut(HttpSession httpSession, ModelMap model) {
		if(httpSession != null) {
			httpSession.invalidate();
			model.addAttribute("userSession", new User());
		}
		return new ModelAndView("signin");
	}
	
	@RequestMapping(value="/login", method=RequestMethod.POST)
	public ModelAndView login(HttpSession httpSession, @ModelAttribute("userSession") User userSession, BindingResult result) {
		Map<String, Object> model = new HashMap<String, Object>();
		
		try{
			User user = userService.getUserByUserName(userSession.getUserName());
			String password = userService.decryptString(user.getPassword());
			if(password.equals(userSession.getPassword())){
				model.put("user", user);
				if(user.getUserRole().getRole().equals(RoleType.ADMIN.getDescription())){
					viewName = "adminManagement";
				}else if(user.getUserRole().getRole().equals(RoleType.GUEST.getDescription())){
					viewName = "livestreaming";
				}
			}else{
				viewName = "signin";
				model.put("loginMessage", "Invalid username/password. Please try again.");
			}
		}catch(Exception e){
			System.err.println(e);
		}
		return new ModelAndView(viewName, model);
	}
}
