package com.sportaholic.controller;

import javax.servlet.http.HttpServletRequest;

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
	public ModelAndView getProductTypes(@PathVariable Integer id, HttpServletRequest request) {
		try {
			String currentPageString = request.getParameter("page");
			int currentPage = currentPageString == null || currentPageString.equals("") ? 1 : Integer.valueOf(currentPageString);
			
			long pages = this.productService.getActiveBySetPages(null, id, 12);
			
			ModelAndView modelAndView = new ModelAndView("products/index");			
			modelAndView.addObject("currentPage", currentPage);
			modelAndView.addObject("firstPage", currentPage - 2 < 1 ? 1 : currentPage - 2);
			modelAndView.addObject("lastPage", currentPage + 2 > pages ? pages : currentPage + 2);
			
			ProductType productType = this.productTypeService.get(id);
			modelAndView.addObject("products", this.productService.getActiveBySetPaginated(null, id, currentPage, 12));
			modelAndView.addObject("sports", this.sportService.getEagerBySet(productType.getProductCategory().getSport().getId()));
			
			return modelAndView;
		} catch (Exception e) {
			e.printStackTrace();
			return new ModelAndView("errors/unexpected-error");
		}
	}
	
}
