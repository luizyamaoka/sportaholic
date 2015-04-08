package com.sportaholic.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.sportaholic.model.ProductType;
import com.sportaholic.model.UrlConstants;
import com.sportaholic.service.ProductService;
import com.sportaholic.service.ProductTypeService;
import com.sportaholic.service.SportService;

@Controller
@RequestMapping(UrlConstants.URL_PRODUCT_TYPE)
public class ProductTypeController {

	private ProductTypeService productTypeService;
	private ProductService productService;
	private SportService sportService;
	
	@Autowired
	public ProductTypeController(ProductTypeService productTypeService,
			ProductService productService, SportService sportService) {
		this.productTypeService = productTypeService;
		this.productService = productService;
		this.sportService = sportService;
	}
	
	@RequestMapping("/{id}")
	public ModelAndView getProductTypes(@PathVariable Integer id) {
		try {
			ModelAndView modelAndView = new ModelAndView("products/index");
			ProductType productType = this.productTypeService.get(id);
			modelAndView.addObject("products", this.productService.getActiveBySetPaginated(null, id, 1, 12));
			modelAndView.addObject("sports", this.sportService.getEagerBySet(productType.getProductCategory().getSport().getId()));
			
			return modelAndView;
		} catch (Exception e) {
			e.printStackTrace();
			return new ModelAndView("errors/unexpected-error");
		}
	}
	
}
