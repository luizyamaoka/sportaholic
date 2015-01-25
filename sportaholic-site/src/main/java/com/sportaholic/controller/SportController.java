package com.sportaholic.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.sportaholic.model.UrlConstants;
import com.sportaholic.service.ArticleService;
import com.sportaholic.service.ArticleTypeService;
import com.sportaholic.service.SportService;

@Controller
@RequestMapping(UrlConstants.URL_SPORT)
public class SportController {
	
	private SportService sportService;
	private ArticleService articleService;
	private ArticleTypeService articleTypeService;
	
	@Autowired
	public SportController(SportService sportService, ArticleService articleService,
			ArticleTypeService articleTypeService) {
		this.sportService = sportService;
		this.articleService = articleService;
		this.articleTypeService = articleTypeService;
	}
	
	@RequestMapping("/{id}")
	public ModelAndView get(@PathVariable Integer id) {
		try {
			ModelAndView modelAndView = new ModelAndView("sports/show");
			modelAndView.addObject("sport", this.sportService.get(id));
			modelAndView.addObject("articles", this.articleService.getBySet(id, null));
			modelAndView.addObject("sports", this.sportService.getAll());
			modelAndView.addObject("articleTypes", this.articleTypeService.getAll());
			return modelAndView;
		} catch (Exception e) {
			e.printStackTrace();
			return new ModelAndView("errors/unexpected-error");
		}
	}
	
	@RequestMapping("/{id}/articles")
	public ModelAndView getArticles(@PathVariable Integer id) {
		try {
			ModelAndView modelAndView = new ModelAndView("articles/index");
			modelAndView.addObject("articles", this.articleService.getBySet(id, null));
			modelAndView.addObject("sports", this.sportService.getAll());
			modelAndView.addObject("articleTypes", this.articleTypeService.getAll());
			
			return modelAndView;
		} catch (Exception e) {
			e.printStackTrace();
			return new ModelAndView("errors/unexpected-error");
		}
	}
	
	@RequestMapping("/{id}/{articleTypeId}")
	public ModelAndView getArticleTypes(@PathVariable Integer id, @PathVariable Integer articleTypeId) {
		try {
			ModelAndView modelAndView = new ModelAndView("articles/index");
			modelAndView.addObject("articles", this.articleService.getBySet(id, articleTypeId));
			modelAndView.addObject("sports", this.sportService.getAll());
			modelAndView.addObject("articleTypes", this.articleTypeService.getAll());
			
			return modelAndView;
		} catch (Exception e) {
			e.printStackTrace();
			return new ModelAndView("errors/unexpected-error");
		}
	}

}
