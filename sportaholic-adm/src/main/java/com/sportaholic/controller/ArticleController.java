package com.sportaholic.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.sportaholic.service.ArticleService;
import com.sportaholic.service.ArticleTypeService;
import com.sportaholic.service.AuthorService;
import com.sportaholic.service.SportService;

@Controller
@RequestMapping("/articles")
public class ArticleController {

	private ArticleService articleService;
	private SportService sportService;
	private ArticleTypeService articleTypeService;
	private AuthorService authorService;
	
}
