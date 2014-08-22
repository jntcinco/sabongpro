package com.tekusource.sabongpro.controller;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.tekusource.sabongpro.model.User;

@Controller
@RequestMapping(value="/")
public class FrontPageController extends AbstractController {

	@Override
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
		// TODO:
		return new ModelAndView("schedule", model);
	}
}
