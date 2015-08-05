package com.sportaholic.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.sportaholic.model.UrlConstants;
import com.sportaholic.service.ArticleService;
import com.sportaholic.service.ArticleTypeService;
import com.sportaholic.service.ProductService;
import com.sportaholic.service.SportService;

@Controller
@RequestMapping(UrlConstants.URL_SPORT)
public class SportController {
	
	private SportService sportService;
	private ArticleService articleService;
	private ArticleTypeService articleTypeService;
	private ProductService productService;
	
	@Autowired
	public SportController(SportService sportService, ArticleService articleService,
			ArticleTypeService articleTypeService, ProductService productService) {
		this.sportService = sportService;
		this.articleService = articleService;
		this.articleTypeService = articleTypeService;
		this.productService = productService;
	}
	
	@RequestMapping("")
	public ModelAndView index() {
		try {
			ModelAndView modelAndView = new ModelAndView("sports/index");
			modelAndView.addObject("sports", this.sportService.getAll());
			return modelAndView;
		} catch (Exception e) {
			e.printStackTrace();
			return new ModelAndView("errors/unexpected-error");
		}
	}
	
	@RequestMapping("/{id}")
	public ModelAndView get(@PathVariable Integer id) {
		try {
			ModelAndView modelAndView = new ModelAndView("sports/show");
			modelAndView.addObject("sport", this.sportService.get(id));
			modelAndView.addObject("articles", this.articleService.getPublishedBySetPaginated(id, null, 1, 3));
			modelAndView.addObject("products", this.productService.getActiveBySetPaginated(id, null, 1, 4));
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
			modelAndView.addObject("articles", this.articleService.getPublishedBySetPaginated(id, null, 1, 12));
			modelAndView.addObject("sports", this.sportService.getAll());
			modelAndView.addObject("articleTypes", this.articleTypeService.getAll());
			
			return modelAndView;
		} catch (Exception e) {
			e.printStackTrace();
			return new ModelAndView("errors/unexpected-error");
		}
	}
	
	@RequestMapping("/{id}/articles/{articleTypeId}")
	public ModelAndView getArticleTypes(@PathVariable Integer id, @PathVariable Integer articleTypeId) {
		try {
			ModelAndView modelAndView = new ModelAndView("articles/index");
			modelAndView.addObject("articles", this.articleService.getPublishedBySetPaginated(id, articleTypeId, 1, 12));
			modelAndView.addObject("sports", this.sportService.getAll());
			modelAndView.addObject("articleTypes", this.articleTypeService.getAll());
			
			return modelAndView;
		} catch (Exception e) {
			e.printStackTrace();
			return new ModelAndView("errors/unexpected-error");
		}
	}
	
	@RequestMapping("/{id}/products")
	public ModelAndView getProducts(@PathVariable Integer id, HttpServletRequest request) {
		try {
			String currentPageString = request.getParameter("page");
			int currentPage = currentPageString == null || currentPageString.equals("") ? 1 : Integer.valueOf(currentPageString);
			
			long pages = this.productService.getActiveBySetPages(null, null, 12);
			
			ModelAndView modelAndView = new ModelAndView("products/index");			
			modelAndView.addObject("currentPage", currentPage);
			modelAndView.addObject("firstPage", currentPage - 2 < 1 ? 1 : currentPage - 2);
			modelAndView.addObject("lastPage", currentPage + 2 > pages ? pages : currentPage + 2);
			
			modelAndView.addObject("products", this.productService.getActiveBySetPaginated(id, null, currentPage, 12));
			modelAndView.addObject("sports", this.sportService.getEagerBySet(id));
			
			return modelAndView;
		} catch (Exception e) {
			e.printStackTrace();
			return new ModelAndView("errors/unexpected-error");
		}
	}

}
