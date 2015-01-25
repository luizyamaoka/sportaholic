package com.sportaholic.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.sportaholic.model.Author;
import com.sportaholic.model.UrlConstants;
import com.sportaholic.service.AuthorService;

@Controller
@RequestMapping(UrlConstants.URL_AUTHOR)
public class AuthorController {

	private AuthorService authorService;
	
	@Autowired
	public AuthorController(AuthorService authorService) {
		this.authorService = authorService;
	}
	
	@RequestMapping(value="/{id}")
	public ModelAndView get(@PathVariable Integer id) {
		try {
			ModelAndView modelAndView = new ModelAndView("authors/show");
			Author author = this.authorService.getEager(id);
			modelAndView.addObject("author", author);
			modelAndView.addObject("authors", this.authorService.getAll());
			modelAndView.addObject("articles", author.getArticles());
			return modelAndView;
		} catch (Exception e) {
			e.printStackTrace();
			return new ModelAndView("errors/unexpected-error");
		}
	}
	
	@RequestMapping(value="")
	public ModelAndView index(HttpServletRequest request) {
		try {
			ModelAndView modelAndView = new ModelAndView("authors/index");
			modelAndView.addObject("authors", this.authorService.getAll());
			return modelAndView;
		} catch (Exception e) {
			e.printStackTrace();
			return new ModelAndView("errors/error");
		}
	}
	
}
