package com.sportaholic.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.sportaholic.dto.ArticleDto;
import com.sportaholic.model.Article;
import com.sportaholic.model.UrlConstants;
import com.sportaholic.service.AdmArticleService;
import com.sportaholic.service.ArticleService;
import com.sportaholic.service.ArticleTypeService;
import com.sportaholic.service.AuthorService;
import com.sportaholic.service.SportService;
import com.sportaholic.service.UriService;
import com.sportaholic.transformer.ArticleDtoTransformer;

@Controller
@RequestMapping(UrlConstants.URL_ARTICLE)
public class ArticleController {

	private ArticleService articleService;
	private SportService sportService;
	private ArticleTypeService articleTypeService;
	private AuthorService authorService;
	private AdmArticleService admArticleService;
	private ArticleDtoTransformer articleDtoTransformer;
	private UriService uriService;
	
	private static final Map<String, String> ERROR_MESSAGES;
	static {
		ERROR_MESSAGES = new HashMap<String, String>();
		ERROR_MESSAGES.put("articleId.required", "O id do artigo precisa ser fornecido.");
		ERROR_MESSAGES.put("title.required", "O título precisa ser preenchido.");
		ERROR_MESSAGES.put("subtitle.required", "O subtítulo precisa ser preenchido.");
		ERROR_MESSAGES.put("content.required", "O conteúdo precisa ser preenchido.");
		ERROR_MESSAGES.put("author.required", "Selecione o autor do artigo.");
		ERROR_MESSAGES.put("publishedAtYear.required", "O ano da publicação precisa ser preenchido");
		ERROR_MESSAGES.put("publishedAtMonth.required", "O mês da publicação precisa ser preenchido");
		ERROR_MESSAGES.put("publishedAtDay.required", "O dia da publicação precisa ser preenchido");
		ERROR_MESSAGES.put("publishedAtHour.required", "A hora da publicação precisa ser preenchido");
		ERROR_MESSAGES.put("publishedAtMinute.required", "O minuto da publicação precisa ser preenchido");
		ERROR_MESSAGES.put("publishedAtMonth.impossible", "O mês da publicação precisa estar entre 1 e 12");
		ERROR_MESSAGES.put("publishedAtDay.impossible", "O dia da publicação precisa estar entre 1 e 31");
		ERROR_MESSAGES.put("publishedAtHour.impossible", "A hora da publicação precisa estar entre 0 e 23");
		ERROR_MESSAGES.put("publishedAtMinute.impossible", "O minuto da publicação precisa estar entre 0 e 59");
		ERROR_MESSAGES.put("sports.required", "Selecione pelo menos 1 esporte para este artigo.");
		ERROR_MESSAGES.put("articleTypes.required", "Selecione pelo menos 1 tipo de artigo.");
		ERROR_MESSAGES.put("friendlyUri.required", "A url amigável precisa ser preenchida.");
		ERROR_MESSAGES.put("friendlyUri.startsWithSlash", "A url amigável precisa começar com /");
		ERROR_MESSAGES.put("friendlyUri.containsSpaces", "A url amigável nao pode conter espaços");
		ERROR_MESSAGES.put("friendlyUri.existant", "A url amigável escolhida já existe.");
		ERROR_MESSAGES.put("name.required", "O nome da url precisa ser preenchido.");
		ERROR_MESSAGES.put("parentId.required", "Selecione a url pai.");
		ERROR_MESSAGES.put("metaDescription.required", "A meta description precisa ser preenchida.");
		ERROR_MESSAGES.put("metaDescription.length", "A meta description deve ter no máximo 160 caracteres.");
		
	}
	
	@Autowired
	public ArticleController(ArticleService articleService, SportService sportService, 
			ArticleTypeService articleTypeService, AuthorService authorService, AdmArticleService admArticleService,
			ArticleDtoTransformer articleDtoTransformer, UriService uriService) {
		this.articleService = articleService;
		this.sportService = sportService;
		this.articleTypeService = articleTypeService;
		this.authorService = authorService;
		this.admArticleService = admArticleService;
		this.articleDtoTransformer = articleDtoTransformer;
		this.uriService = uriService;
	}
	
	@RequestMapping("")
	public ModelAndView getAll() {
		try {
			ModelAndView modelAndView = new ModelAndView("articles/index");
			modelAndView.addObject("articles", this.articleService.getAll());
			return modelAndView;
		} catch (Exception e) {
			e.printStackTrace();
			return new ModelAndView("errors/unexpected-error");
		}
	}
	
	@RequestMapping(value="/{id}", method=RequestMethod.GET)
	public ModelAndView show(@PathVariable Integer id, HttpServletRequest request) {
		try {
			ModelAndView modelAndView = new ModelAndView("articles/edit");
			
			Article article = this.admArticleService.getEager(id);
			ArticleDto articleDto = this.articleDtoTransformer.articleToArticleDto(article);
			modelAndView.addObject("articleDto", articleDto);
			
			modelAndView.addObject("authors", this.authorService.getAll());
			modelAndView.addObject("sports", this.sportService.getAll());
			modelAndView.addObject("articleTypes", this.articleTypeService.getAll());
			modelAndView.addObject("uris", this.uriService.getAll());
			
			if(request.getParameter("success") != null)
				modelAndView.addObject("successes", "<strong>Sucesso!</strong> Artigo criado com sucesso.");
			if(request.getParameter("edited") != null)
				modelAndView.addObject("successes", "<strong>Sucesso!</strong> Artigo editado com sucesso.");
			
			return modelAndView;
		} catch (Exception e) {
			e.printStackTrace();
			return new ModelAndView("errors/unexpected-error");
		}
	}
	
	@RequestMapping(value="/{id}", method=RequestMethod.POST)
	public String edit(@PathVariable Integer id, ArticleDto articleDto, BindingResult result, Model m) {
		try {			
			articleDto.setId(id);
			List<String> status = this.admArticleService.update(articleDto);
			
			if(status.get(0).equals("error")) {
	        	List<String> errors = new ArrayList<String>();
	        	for (int i = 1; i < status.size(); i++)
	        		errors.add(ERROR_MESSAGES.get(status.get(i)));
	        	m.addAttribute("errors", errors);
	        	
	        	m.addAttribute("authors", this.authorService.getAll());
				m.addAttribute("sports", this.sportService.getAll());
				m.addAttribute("articleTypes", this.articleTypeService.getAll());
				m.addAttribute("uris", this.uriService.getAll());
	        	
	            return "articles/edit";
	        }
			
			return "redirect:" + UrlConstants.URL_ARTICLE + "/" + status.get(1) + "?edited";
		} catch (Exception e) {
			e.printStackTrace();
			return "errors/unexpected-error";
		}
	}
	
	@RequestMapping(value="/new", method=RequestMethod.GET)
	public ModelAndView newForm() {
		try {
			ModelAndView modelAndView = new ModelAndView("articles/new");
			modelAndView.addObject("articleDto", new ArticleDto());
			
			modelAndView.addObject("authors", this.authorService.getAll());
			modelAndView.addObject("sports", this.sportService.getAll());
			modelAndView.addObject("articleTypes", this.articleTypeService.getAll());
			modelAndView.addObject("uris", this.uriService.getAll());
			
			return modelAndView;
		} catch (Exception e) {
			e.printStackTrace();
			return new ModelAndView("errors/unexpected-error");
		}
	}
	
	@RequestMapping(value="/new", method=RequestMethod.POST)
	public String insert(ArticleDto articleDto, BindingResult result, Model m) {
		try {			
			List<String> status = this.admArticleService.create(articleDto);
			
			if(status.get(0).equals("error")) {
	        	List<String> errors = new ArrayList<String>();
	        	for (int i = 1; i < status.size(); i++) {
	        		errors.add(ERROR_MESSAGES.get(status.get(i)));
	        	}
	        	m.addAttribute("errors", errors);
	        	
	        	m.addAttribute("authors", this.authorService.getAll());
				m.addAttribute("sports", this.sportService.getAll());
				m.addAttribute("articleTypes", this.articleTypeService.getAll());
				m.addAttribute("uris", this.uriService.getAll());
	        	
	            return "articles/new";
	        }
			
			return "redirect:" + UrlConstants.URL_ARTICLE + "/" + status.get(1) + "?success";
		} catch (Exception e) {
			e.printStackTrace();
			return "errors/unexpected-error";
		}
	}
}
