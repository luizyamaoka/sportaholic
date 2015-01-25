package com.sportaholic.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.sportaholic.service.ArticleService;

@Controller
public class StaticPageController {

	private ArticleService articleService;
	
	@Autowired
	public StaticPageController(ArticleService articleService) {
		this.articleService = articleService;
	}
	
	@RequestMapping("/")
	public ModelAndView home(HttpServletRequest request) {
		try {
			ModelAndView modelAndView = new ModelAndView("static-pages/home");
			
			if(request.getParameter("logout") != null)
				modelAndView.addObject("infos", "<strong>Tchau!</strong> Seu logout foi efetuado com sucesso.");
			if(request.getParameter("success") != null)
				modelAndView.addObject("successes", "<strong>Bem-vindo!</strong> Login efetuado com sucesso.");
			
			modelAndView.addObject("articles", this.articleService.getBySet(null, null));
			
			return modelAndView;
		} catch (Exception e) {
			e.printStackTrace();
			return new ModelAndView("errors/unexpected-error");
		}
	}
	
	@RequestMapping("/login")
	public ModelAndView login(HttpServletRequest request) {
		ModelAndView modelAndView = new ModelAndView("static-pages/login");
		
		if(request.getParameter("error") != null)
			modelAndView.addObject("errors", "<strong>Erro!</strong> Usuário e/ou senha incorretos.");
		
		return modelAndView;
	}
	
	@RequestMapping("/403")
	public ModelAndView accessDenied() {
		return new ModelAndView("errors/403");
	}
	
	@RequestMapping("/404")
	public ModelAndView pageNotFound() {
		return new ModelAndView("errors/404");
	}
	
	@RequestMapping("/about-us")
	public ModelAndView aboutUs() {
		return new ModelAndView("static-pages/about-us");
	}
	
	@RequestMapping("/contact-us")
	public ModelAndView contactUs() {
		return new ModelAndView("static-pages/contact-us");
	}
}
