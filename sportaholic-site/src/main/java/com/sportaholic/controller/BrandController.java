package com.sportaholic.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.sportaholic.model.Brand;
import com.sportaholic.model.UrlConstants;
import com.sportaholic.service.BrandService;

@Controller
@RequestMapping(UrlConstants.URL_BRAND)
public class BrandController {

	private BrandService brandService;
	
	@Autowired
	public BrandController(BrandService brandService) {
		this.brandService = brandService;
	}

	@RequestMapping("")
	public ModelAndView index() {
		try {
			ModelAndView modelAndView = new ModelAndView("brands/index");
			modelAndView.addObject("brands", this.brandService.getAll());
			return modelAndView;
		} catch (Exception e) {
			e.printStackTrace();
			return new ModelAndView("errors/unexpected-error");
		}
	}
	
	@RequestMapping("/{id}")
	public ModelAndView show(@PathVariable Integer id) {
		try {
			ModelAndView modelAndView = new ModelAndView("brands/show");
			modelAndView.addObject("brand", this.brandService.get(id));

			Brand brand = this.brandService.getEager(id);
			modelAndView.addObject("brand", brand);
			modelAndView.addObject("sports", this.brandService.getPossibleSports(id));
			modelAndView.addObject("products", brand.getProducts());

			return modelAndView;
		} catch (Exception e) {
			e.printStackTrace();
			return new ModelAndView("errors/unexpected-error");
		}
	}
	
}
