package com.tekusource.sabongpro.controller;

import javax.servlet.http.HttpSession;

import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.tekusource.sabongpro.model.User;

public class GuestController extends AbstractController {

	@Override
	@RequestMapping( method = RequestMethod.GET )
	public ModelAndView pageInitializer(HttpSession httpSession, ModelMap model) {
		model.addAttribute("userSession", new User());
		return new ModelAndView("", model);
	}
	
	@RequestMapping(value="/awards", method = RequestMethod.GET)
	public ModelAndView awards(HttpSession httpSession, ModelMap model) {
//		Content content = (Content) contentService.getContentByPage(PAGE_CATEGORY_AWARDS);
//		model.addAttribute("awardsContent", content);
		return new ModelAndView("", model);
	}
}
