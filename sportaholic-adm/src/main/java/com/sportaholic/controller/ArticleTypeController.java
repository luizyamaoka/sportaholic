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

import com.sportaholic.dto.ArticleTypeDto;
import com.sportaholic.model.ArticleType;
import com.sportaholic.service.ArticleTypeService;
import com.sportaholic.transformer.ArticleTypeDtoTransformer;

@Controller
@RequestMapping("/article-types")
public class ArticleTypeController {

	private ArticleTypeService articleTypeService;
	private ArticleTypeDtoTransformer articleTypeDtoTransformer;
	
	private static final Map<String, String> ERROR_MESSAGES;
	static {
		ERROR_MESSAGES = new HashMap<String, String>();
		ERROR_MESSAGES.put("name.required", "O nome do tipo de artigo precisa ser fornecido.");
		ERROR_MESSAGES.put("name.existant", "O nome do tipo de artigo já existe.");
	}
	
	@Autowired
	public ArticleTypeController(ArticleTypeService articleTypeService,
			ArticleTypeDtoTransformer articleTypeDtoTransformer) {
		this.articleTypeService = articleTypeService;
		this.articleTypeDtoTransformer = articleTypeDtoTransformer;
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
	
	@RequestMapping(value="/{id}", method=RequestMethod.GET)
	public ModelAndView show(@PathVariable Integer id, HttpServletRequest request) {
		try {
			ModelAndView modelAndView = new ModelAndView("article-types/edit");
			
			ArticleType articleType = this.articleTypeService.get(id);
			ArticleTypeDto articleTypeDto = this.articleTypeDtoTransformer.articleTypeToArticleTypeDto(articleType);
			modelAndView.addObject("articleTypeDto", articleTypeDto);
			
			if(request.getParameter("success") != null)
				modelAndView.addObject("successes", "<strong>Sucesso!</strong> Tipo de artigo criado com sucesso.");
			if(request.getParameter("edited") != null)
				modelAndView.addObject("successes", "<strong>Sucesso!</strong> Tipo de artigo editado com sucesso.");
			
			return modelAndView;
		} catch (Exception e) {
			e.printStackTrace();
			return new ModelAndView("errors/unexpected-error");
		}
	}
	
	@RequestMapping(value="/{id}", method=RequestMethod.POST)
	public String edit(@PathVariable Integer id, ArticleTypeDto articleTypeDto, BindingResult result, Model m) {
		try {			
			articleTypeDto.setId(id);
			ArticleType articleType = this.articleTypeDtoTransformer.articleTypeDtoToArticleType(articleTypeDto);
			List<String> status = this.articleTypeService.update(articleType);
			
			if(status.get(0).equals("error")) {
	        	List<String> errors = new ArrayList<String>();
	        	for (int i = 1; i < status.size(); i++)
	        		errors.add(ERROR_MESSAGES.get(status.get(i)));
	        	m.addAttribute("errors", errors);
	        	
	            return "article-types/edit";
	        }
			
			return "redirect:" + "/article-types" + "/" + status.get(1) + "?edited";
		} catch (Exception e) {
			e.printStackTrace();
			return "errors/unexpected-error";
		}
	}
	
	@RequestMapping(value="/new", method=RequestMethod.GET)
	public ModelAndView newForm() {
		try {
			ModelAndView modelAndView = new ModelAndView("article-types/new");
			modelAndView.addObject("articleTypeDto", new ArticleTypeDto());
			
			return modelAndView;
		} catch (Exception e) {
			e.printStackTrace();
			return new ModelAndView("errors/unexpected-error");
		}
	}
	
	@RequestMapping(value="/new", method=RequestMethod.POST)
	public String insert(ArticleTypeDto articleTypeDto, BindingResult result, Model m) {
		try {			
			ArticleType articleType = this.articleTypeDtoTransformer.articleTypeDtoToArticleType(articleTypeDto);
			List<String> status = this.articleTypeService.create(articleType);
			
			if(status.get(0).equals("error")) {
	        	List<String> errors = new ArrayList<String>();
	        	for (int i = 1; i < status.size(); i++) {
	        		errors.add(ERROR_MESSAGES.get(status.get(i)));
	        	}
	        	m.addAttribute("errors", errors);
	        	
	            return "article-types/new";
	        }
			
			return "redirect:" + "/article-types" + "/" + status.get(1) + "?success";
		} catch (Exception e) {
			e.printStackTrace();
			return "errors/unexpected-error";
		}
	}
	
}
