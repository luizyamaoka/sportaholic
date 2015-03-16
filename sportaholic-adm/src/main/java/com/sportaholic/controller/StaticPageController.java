package com.sportaholic.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.sportaholic.service.UriService;

@Controller
public class StaticPageController {
	
	@RequestMapping(value="/")
	public ModelAndView home() {
		ModelAndView modelAndView = new ModelAndView("static-pages/home");
		return modelAndView;
	}
	
	@RequestMapping("/login")
	public ModelAndView login(HttpServletRequest request) {
		ModelAndView modelAndView = new ModelAndView("static-pages/login");
		
		if(request.getParameter("error") != null)
			modelAndView.addObject("errors", "<strong>Erro!</strong> Usuário e/ou senha incorretos.");
		
		return modelAndView;
	}
	
}
