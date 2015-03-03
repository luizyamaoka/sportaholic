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

import com.sportaholic.dto.ProductTypeDto;
import com.sportaholic.model.ProductType;
import com.sportaholic.model.UrlConstants;
import com.sportaholic.service.ProductCategoryService;
import com.sportaholic.service.ProductTypeService;
import com.sportaholic.transformer.ProductTypeDtoTransformer;

@Controller
@RequestMapping(UrlConstants.URL_PRODUCT_TYPE)
public class ProductTypeController {
	
	private ProductTypeService productTypeService;
	private ProductCategoryService productCategoryService;
	private ProductTypeDtoTransformer productTypeDtoTransformer; 
	
	private static final Map<String, String> ERROR_MESSAGES;
	static {
		ERROR_MESSAGES = new HashMap<String, String>();
		ERROR_MESSAGES.put("name.required", "O nome da categoria de produtos precisa ser fornecido.");
		ERROR_MESSAGES.put("name.existant", "O nome da categoria de produtos já existe.");
	}
	
	@Autowired
	public ProductTypeController(ProductTypeService productTypeService,
			ProductCategoryService productCategoryService,
			ProductTypeDtoTransformer productTypeDtoTransformer) {
		this.productTypeService = productTypeService;
		this.productCategoryService = productCategoryService;
		this.productTypeDtoTransformer = productTypeDtoTransformer;
	}
	
	@RequestMapping("")
	public ModelAndView index() {
		try {
			ModelAndView modelAndView = new ModelAndView("product-types/index");
			modelAndView.addObject("productTypes", this.productTypeService.getAll());
			return modelAndView;
		} catch (Exception e) {
			e.printStackTrace();
			return new ModelAndView("errors/unexpected-error");
		}
	}
	
	@RequestMapping(value="/{id}", method=RequestMethod.GET)
	public ModelAndView show(@PathVariable Integer id, HttpServletRequest request) {
		try {
			ModelAndView modelAndView = new ModelAndView("product-types/edit");
			
			ProductType productType = this.productTypeService.get(id);
			ProductTypeDto productTypeDto = this.productTypeDtoTransformer.productTypeToProductTypeDto(productType);
			modelAndView.addObject("productTypeDto", productTypeDto);
			modelAndView.addObject("productCategories", this.productCategoryService.getAll());
			
			if(request.getParameter("success") != null)
				modelAndView.addObject("successes", "<strong>Sucesso!</strong> Categoria de produtos criada com sucesso.");
			if(request.getParameter("edited") != null)
				modelAndView.addObject("successes", "<strong>Sucesso!</strong> Categoria de produtos editada com sucesso.");
			
			return modelAndView;
		} catch (Exception e) {
			e.printStackTrace();
			return new ModelAndView("errors/unexpected-error");
		}
	}
	
	@RequestMapping(value="/{id}", method=RequestMethod.POST)
	public String edit(@PathVariable Integer id, ProductTypeDto productTypeDto, BindingResult result, Model m) {
		try {			
			productTypeDto.setId(id);
			ProductType productType = this.productTypeDtoTransformer.ProductTypeDtoToProductType(productTypeDto);
			List<String> status = this.productTypeService.update(productType);
			
			if(status.get(0).equals("error")) {
	        	List<String> errors = new ArrayList<String>();
	        	for (int i = 1; i < status.size(); i++)
	        		errors.add(ERROR_MESSAGES.get(status.get(i)));
	        	m.addAttribute("errors", errors);
	        	
	            return "product-types/edit";
	        }
			
			return "redirect:" + UrlConstants.URL_PRODUCT_TYPE + "/" + status.get(1) + "?edited";
		} catch (Exception e) {
			e.printStackTrace();
			return "errors/unexpected-error";
		}
	}
	
	@RequestMapping(value="/new", method=RequestMethod.GET)
	public ModelAndView newForm() {
		try {
			ModelAndView modelAndView = new ModelAndView("product-types/new");
			modelAndView.addObject("productTypeDto", new ProductTypeDto());
			modelAndView.addObject("productCategories", this.productCategoryService.getAll());
			
			return modelAndView;
		} catch (Exception e) {
			e.printStackTrace();
			return new ModelAndView("errors/unexpected-error");
		}
	}
	
	@RequestMapping(value="/new", method=RequestMethod.POST)
	public String insert(ProductTypeDto productTypeDto, BindingResult result, Model m) {
		try {			
			ProductType productType = this.productTypeDtoTransformer.ProductTypeDtoToProductType(productTypeDto);
			List<String> status = this.productTypeService.create(productType);
			
			if(status.get(0).equals("error")) {
	        	List<String> errors = new ArrayList<String>();
	        	for (int i = 1; i < status.size(); i++) {
	        		errors.add(ERROR_MESSAGES.get(status.get(i)));
	        	}
	        	m.addAttribute("errors", errors);
	        	
	            return "product-types/new";
	        }
			
			return "redirect:" + UrlConstants.URL_PRODUCT_TYPE + "/" + status.get(1) + "?success";
		} catch (Exception e) {
			e.printStackTrace();
			return "errors/unexpected-error";
		}
	}

}
