package com.sportaholic.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.sportaholic.dto.Sitemap;
import com.sportaholic.dto.UrlXml;
import com.sportaholic.model.Uri;
import com.sportaholic.service.ArticleService;
import com.sportaholic.service.ProductService;
import com.sportaholic.service.UriService;
import com.sportaholic.transformer.UrlXmlTransformer;

@Controller
public class StaticPageController {

	private ArticleService articleService;
	private ProductService productService;
	private UriService uriService;
	private UrlXmlTransformer urlXmlTransformer;
	
	@Autowired
	public StaticPageController(ArticleService articleService, 
			UriService uriService, UrlXmlTransformer urlXmlTransformer,
			ProductService productService) {
		this.articleService = articleService;
		this.productService = productService;
		this.uriService = uriService;
		this.urlXmlTransformer = urlXmlTransformer;
	}
	
	@RequestMapping("/")
	public ModelAndView home(HttpServletRequest request) {
		try {
			ModelAndView modelAndView = new ModelAndView("static-pages/home");
			
			if(request.getParameter("logout") != null)
				modelAndView.addObject("infos", "<strong>Tchau!</strong> Seu logout foi efetuado com sucesso.");
			if(request.getParameter("success") != null)
				modelAndView.addObject("successes", "<strong>Bem-vindo!</strong> Login efetuado com sucesso.");
			
			modelAndView.addObject("articles", this.articleService.getPublishedPaginated(1, 9));
			modelAndView.addObject("products", this.productService.getActiveBySetPaginated(null, null, 1, 12));
			
			return modelAndView;
		} catch (Exception e) {
			e.printStackTrace();
			return new ModelAndView("errors/unexpected-error");
		}
	}
	
	@RequestMapping("/login")
	public ModelAndView login(HttpServletRequest request) {
		ModelAndView modelAndView = new ModelAndView("static-pages/login");
		
		if(request.getParameter("error") != null)
			modelAndView.addObject("errors", "<strong>Erro!</strong> Usuário e/ou senha incorretos.");
		if(request.getParameter("subscribed") != null)
			modelAndView.addObject("successes", "<strong>Sucesso!</strong> Obrigado por se cadastrar. Faça seu login e usufrua de todas as funcionalidades da Sportaholic.");
		
		return modelAndView;
	}
	
	@RequestMapping("/403")
	public ModelAndView accessDenied() {
		return new ModelAndView("errors/403");
	}
	
	@RequestMapping("/404")
	public ModelAndView pageNotFound() {
		return new ModelAndView("errors/404");
	}
	
	@RequestMapping("/about-us")
	public ModelAndView aboutUs() {
		return new ModelAndView("static-pages/about-us");
	}
	
	@RequestMapping("/contact-us")
	public ModelAndView contactUs() {
		return new ModelAndView("static-pages/contact-us");
	}
	
	@RequestMapping("/sitemap.xml")
	public @ResponseBody Sitemap sitemap(HttpServletRequest request) {
		try {
			List<Uri> uris = this.uriService.getAll();
			List<UrlXml> urlXmls = new ArrayList<UrlXml>();
			
			for (Uri uri : uris) {
				urlXmls.add(this.urlXmlTransformer.urlToUrlXml(uri));
			}
			
			Sitemap sitemap = new Sitemap();
			sitemap.setUrlXmls(urlXmls);
			return sitemap;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
}
