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
		// TODO:
		return new ModelAndView(viewName, model);
	}
}
