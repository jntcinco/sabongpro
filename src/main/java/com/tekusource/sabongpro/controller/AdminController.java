package com.tekusource.sabongpro.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.tekusource.sabongpro.model.StreamingConfig;
import com.tekusource.sabongpro.model.User;
import com.tekusource.sabongpro.service.UserService;
import com.tekusource.sabongpro.validator.StreamingConfigValidator;

@Controller
@RequestMapping(value="/admin")
public class AdminController extends AbstractController {

	@Autowired
	private UserService userService;
	
	@Override
	@RequestMapping(value="/management", method = RequestMethod.GET)
	public ModelAndView pageInitializer(HttpSession httpSession, ModelMap model) {
		// TODO:
		return new ModelAndView("adminManagement", model);
	}
	
	@RequestMapping(value="/user/search", method=RequestMethod.GET)
	public ModelAndView searchUser(HttpServletRequest request, @RequestParam("searchKey") String searchKey){
		try{
			User user = userService.getUserBy(searchKey);
		}catch(Exception e){
			
		}
		
		return null;
	}

	@RequestMapping(value="/user/management", method = RequestMethod.GET)
	public ModelAndView userManagement(HttpSession httpSession, ModelMap model) {
		try {
			List<User> users = userService.getAllUser();
			model.put("users", users);
		} catch(Exception e){
			System.err.println(e);
		}
		
		return new ModelAndView("userManagement", model);
	}
	
	@RequestMapping(value="/streamingConfig", method = RequestMethod.GET)
	public ModelAndView streamingConfig(HttpSession httpSession, ModelMap model) {
		model.addAttribute("config", new StreamingConfig());
		return new ModelAndView("streamingConfig", model);
	}
	
	@RequestMapping(value="/addStreamingConfig", method = RequestMethod.POST)
	public ModelAndView addStreamingConfig(HttpSession httpSession, @ModelAttribute("streamingConfig") StreamingConfig config, 
										   BindingResult results, ModelMap model) {
		StreamingConfigValidator validator = new StreamingConfigValidator();
		validator.validate(config, results);
		
		if(!results.hasErrors()) {
			
		}
		return new ModelAndView("streamingConfig", model);
	}
}
