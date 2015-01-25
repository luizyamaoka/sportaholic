package com.sportaholic.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class StaticPagesController {

	@RequestMapping(value="/")
	public ModelAndView home() {
		ModelAndView modelAndView = new ModelAndView("static-pages/home");
		return modelAndView;
	}
	
}
