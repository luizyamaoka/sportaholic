package com.sportaholic.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.sportaholic.model.Article;
import com.sportaholic.model.ArticleComment;
import com.sportaholic.model.Client;
import com.sportaholic.model.UrlConstants;
import com.sportaholic.service.ArticleCommentService;
import com.sportaholic.service.ArticleService;
import com.sportaholic.service.ClientService;

@Controller
@RequestMapping(UrlConstants.URL_ARTICLE_COMMENT)
public class ArticleCommentController {

	private ArticleCommentService articleCommentService;
	private ClientService clientService;
	private ArticleService articleService;
	
	@Autowired
	public ArticleCommentController(ArticleCommentService articleCommentService, 
			ClientService clientService, ArticleService articleService) {
		this.articleCommentService = articleCommentService;
		this.clientService = clientService;
		this.articleService = articleService;
	}
	
	@RequestMapping(value="/new", method=RequestMethod.POST)
	public String create(@RequestParam Integer articleId, @RequestParam String content) {
		try {
			String email = SecurityContextHolder.getContext().getAuthentication().getName();
			Client client = this.clientService.getByEmail(email);
			Article article = this.articleService.get(articleId);
			
			ArticleComment articleComment = new ArticleComment();
			articleComment.setClient(client);
			articleComment.setArticle(article);
			articleComment.setContent(content);
			
			List<String> status = this.articleCommentService.create(articleComment);
	         
	        return "redirect:" + UrlConstants.URL_ARTICLE + "/" + article.getId() + "?commented";
		} catch (Exception e) {
			e.printStackTrace();
			return "errors/unexpected-error";
		}
	}
	
}
