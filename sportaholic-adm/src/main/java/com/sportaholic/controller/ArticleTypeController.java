package com.sportaholic.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.sportaholic.service.ArticleTypeService;

@Controller
@RequestMapping("/article-types")
public class ArticleTypeController {

	private ArticleTypeService articleTypeService;
	
	@Autowired
	public ArticleTypeController(ArticleTypeService articleTypeService) {
		this.articleTypeService = articleTypeService;
	}
	
	@RequestMapping("")
	public ModelAndView index() {
		try {
			ModelAndView modelAndView = new ModelAndView("article-types/index");
			modelAndView.addObject("articleTypes", this.articleTypeService.getAll());
			return modelAndView;
		} catch (Exception e) {
			e.printStackTrace();
			return new ModelAndView("errors/unexpected-error");
		}
	}
	
}
