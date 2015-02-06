package com.sportaholic.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.sportaholic.model.Article;
import com.sportaholic.model.Client;
import com.sportaholic.model.Reading;
import com.sportaholic.model.UrlConstants;
import com.sportaholic.service.ArticleService;
import com.sportaholic.service.ArticleTypeService;
import com.sportaholic.service.ClientService;
import com.sportaholic.service.ReadingService;
import com.sportaholic.service.SportService;

@Controller
@RequestMapping(UrlConstants.URL_ARTICLE)
public class ArticleController {

	private ArticleService articleService;
	private SportService sportService;
	private ArticleTypeService articleTypeService;
	private ReadingService readingService;
	private ClientService clientService;
	
	@Autowired
	public ArticleController(ArticleService articleService, SportService sportService, 
			ArticleTypeService articleTypeService, ReadingService readingService, ClientService clientService) {
		this.articleService = articleService;
		this.sportService = sportService;
		this.articleTypeService = articleTypeService;
		this.readingService = readingService;
		this.clientService = clientService;
	}
	
	@RequestMapping("/{id}")
	public ModelAndView get(@PathVariable Integer id, HttpServletRequest request) {
		try {
			ModelAndView modelAndView = new ModelAndView("articles/show");
			Article article = this.articleService.getEager(id);
			modelAndView.addObject("article", article);
			modelAndView.addObject("sports", this.sportService.getAll());
			modelAndView.addObject("articleTypes", this.articleTypeService.getAll());
			
			if(request.getParameter("commented") != null)
				modelAndView.addObject("successes", "<strong>Sucesso!</strong> Obrigado pelo seu comentário.");
			
			if(SecurityContextHolder.getContext().getAuthentication().isAuthenticated()) {
				String email = SecurityContextHolder.getContext().getAuthentication().getName();
				Client client = this.clientService.getByEmail(email);
				Reading reading = new Reading();
				reading.setArticle(article);
				reading.setClient(client);
				if (client != null) 
					this.readingService.create(reading);
			}
			
			return modelAndView;
		} catch(Exception e) {
			e.printStackTrace();
			return new ModelAndView("errors/unexpected-error");
		}
	}
	
	@RequestMapping("")
	public ModelAndView index(HttpServletRequest request,
			@RequestParam(value="sportId", required=false) Integer sportId,
			@RequestParam(value="articleTypeId", required=false) Integer articleTypeId) {
		try {
			ModelAndView modelAndView = new ModelAndView("articles/index");
			
			modelAndView.addObject("articles", this.articleService.getPublishedBySet(sportId, articleTypeId));
			modelAndView.addObject("sports", this.sportService.getAll());
			modelAndView.addObject("articleTypes", this.articleTypeService.getAll());
			return modelAndView;
		} catch(Exception e) {
			e.printStackTrace();
			return new ModelAndView("errors/unexpected-error");
		}
	}
	
}
