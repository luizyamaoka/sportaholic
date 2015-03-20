package com.sportaholic.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.sportaholic.model.Product;
import com.sportaholic.model.UrlConstants;
import com.sportaholic.service.ProductService;

@Controller
@RequestMapping(UrlConstants.URL_PRODUCT)
public class ProductController {

	private ProductService productService;
	
	@Autowired
	public ProductController(ProductService productService) {
		this.productService = productService;
	}
	
	@RequestMapping("/{id}")
	public ModelAndView show(@PathVariable Integer id) {
		try {
			ModelAndView modelAndView = new ModelAndView("products/show");
			Product product = this.productService.getEager(id);
			modelAndView.addObject("product", product);
			
			List<Product> recommendedProducts = product.getBrand().getProducts();
			modelAndView.addObject("products", recommendedProducts);
			
			return modelAndView;
		} catch (Exception e) {
			e.printStackTrace();
			return new ModelAndView("errors/unexpected-error");
		}
	}
	
}
