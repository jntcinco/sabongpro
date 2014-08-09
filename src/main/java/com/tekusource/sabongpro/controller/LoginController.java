package com.tekusource.sabongpro.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.tekusource.sabongpro.model.User;

@Controller
@RequestMapping( value = "/" )
public class LoginController extends AbstractController {

	@Override
	@RequestMapping( method = RequestMethod.GET )
	public ModelAndView pageInitializer(HttpSession httpSession, ModelMap model) {
		model.addAttribute("userSession", new User());
		return new ModelAndView("", model);
	}
	
	@RequestMapping(value="/signout", method=RequestMethod.GET)
	public ModelAndView signOut(HttpSession httpSession, ModelMap model) {
		if(httpSession != null) {
			httpSession.invalidate();
			model.addAttribute("userSession", new User());
		}
		return new ModelAndView("");
	}
	
	@RequestMapping(value="/login", method=RequestMethod.POST)
	public ModelAndView login(HttpSession httpSession, @ModelAttribute("userSession") User userSession, BindingResult result) {
		Map<String, Object> model = new HashMap<String, Object>();
//		LoginValidator loginValidator = new LoginValidator();
//		loginValidator.validate(userSession, result);
//		
//		if(result.hasErrors()) {
//			viewName = "";
//		} else {
//			Map<String, Object> values = new HashMap<String, Object>();
//			values.put("username", userSession.getUsername());
//			values.put("password", userSession.getPassword());
//			UserDetail user = (UserDetail) userService.getUserBy(values);
//			if(user == null) {
//				model.put("loginMessage", SuperflyMessages.INVALID_CREDENTIALS);
//				viewName = "";
//			} else {
//				userSession = new UserSession(user);
//				httpSession.setAttribute("userSession", userSession);
//				viewName = "";
//			}
//		}
		return new ModelAndView(viewName, model);
	}
}
