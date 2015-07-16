package com.sportaholic.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.sportaholic.model.UrlConstants;
import com.sportaholic.service.ArticleService;

@Controller
@RequestMapping(UrlConstants.URL_PURCHASE)
public class PurchaseController {

	private ArticleService articleService;
	
	@Autowired
	public PurchaseController(ArticleService articleService) {
		this.articleService = articleService;
	}
	
	@RequestMapping(value="")
	public ModelAndView index(HttpServletRequest request, HttpServletResponse response) {
		try {
			String transactionId = request.getParameter("transactionId");
			
			if (transactionId == null || transactionId.equals("")) {
				response.sendRedirect("/");
				return null;
			} else {
				ModelAndView modelAndView = new ModelAndView("purchases/index");
				modelAndView.addObject("transactionId", transactionId);
				modelAndView.addObject("articles", this.articleService.getPublishedPaginated(1, 3));
				return modelAndView;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return new ModelAndView("errors/unexpected-error");
		}
	}
}
