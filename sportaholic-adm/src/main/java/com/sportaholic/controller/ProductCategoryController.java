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

import com.sportaholic.dto.ProductCategoryDto;
import com.sportaholic.model.ProductCategory;
import com.sportaholic.model.UrlConstants;
import com.sportaholic.service.ProductCategoryService;
import com.sportaholic.service.SportService;
import com.sportaholic.transformer.ProductCategoryDtoTransformer;

@Controller
@RequestMapping(UrlConstants.URL_PRODUCT_CATEGORY)
public class ProductCategoryController {

	private ProductCategoryService productCategoryService;
	private ProductCategoryDtoTransformer productCategoryDtoTransformer;
	private SportService sportService;
	
	private static final Map<String, String> ERROR_MESSAGES;
	static {
		ERROR_MESSAGES = new HashMap<String, String>();
		ERROR_MESSAGES.put("name.required", "O nome da categoria de produtos precisa ser fornecido.");
		ERROR_MESSAGES.put("name.existant", "O nome da categoria de produtos já existe.");
	}
	
	@Autowired
	public ProductCategoryController(ProductCategoryService productCategoryService,
			ProductCategoryDtoTransformer productCategoryDtoTransformer, SportService sportService) {
		this.productCategoryService = productCategoryService;
		this.productCategoryDtoTransformer = productCategoryDtoTransformer;
		this.sportService = sportService;
	}
	
	@RequestMapping("")
	public ModelAndView index() {
		try {
			ModelAndView modelAndView = new ModelAndView("product-categories/index");
			modelAndView.addObject("productCategories", this.productCategoryService.getAll());
			return modelAndView;
		} catch (Exception e) {
			e.printStackTrace();
			return new ModelAndView("errors/unexpected-error");
		}
	}
	
	@RequestMapping(value="/{id}", method=RequestMethod.GET)
	public ModelAndView show(@PathVariable Integer id, HttpServletRequest request) {
		try {
			ModelAndView modelAndView = new ModelAndView("product-categories/edit");
			
			ProductCategory productCategory = this.productCategoryService.get(id);
			ProductCategoryDto productCategoryDto = this.productCategoryDtoTransformer.productCategoryToProductCategoryDto(productCategory);
			modelAndView.addObject("productCategoryDto", productCategoryDto);
			modelAndView.addObject("sports", this.sportService.getAll());
			
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
	public String edit(@PathVariable Integer id, ProductCategoryDto productCategoryDto, BindingResult result, Model m) {
		try {			
			productCategoryDto.setId(id);
			ProductCategory productCategory = this.productCategoryDtoTransformer.ProductCategoryDtoToProductCategory(productCategoryDto);
			List<String> status = this.productCategoryService.update(productCategory);
			
			if(status.get(0).equals("error")) {
	        	List<String> errors = new ArrayList<String>();
	        	for (int i = 1; i < status.size(); i++)
	        		errors.add(ERROR_MESSAGES.get(status.get(i)));
	        	m.addAttribute("errors", errors);
	        	
	            return "product-categories/edit";
	        }
			
			return "redirect:" + UrlConstants.URL_PRODUCT_CATEGORY + "/" + id + "?edited";
		} catch (Exception e) {
			e.printStackTrace();
			return "errors/unexpected-error";
		}
	}
	
	@RequestMapping(value="/new", method=RequestMethod.GET)
	public ModelAndView newForm() {
		try {
			ModelAndView modelAndView = new ModelAndView("product-categories/new");
			modelAndView.addObject("productCategoryDto", new ProductCategoryDto());
			modelAndView.addObject("sports", this.sportService.getAll());
			
			return modelAndView;
		} catch (Exception e) {
			e.printStackTrace();
			return new ModelAndView("errors/unexpected-error");
		}
	}
	
	@RequestMapping(value="/new", method=RequestMethod.POST)
	public String insert(ProductCategoryDto productCategoryDto, BindingResult result, Model m) {
		try {			
			ProductCategory productCategory = this.productCategoryDtoTransformer.ProductCategoryDtoToProductCategory(productCategoryDto);
			List<String> status = this.productCategoryService.create(productCategory);
			
			if(status.get(0).equals("error")) {
	        	List<String> errors = new ArrayList<String>();
	        	for (int i = 1; i < status.size(); i++) {
	        		errors.add(ERROR_MESSAGES.get(status.get(i)));
	        	}
	        	m.addAttribute("errors", errors);
	        	
	            return "product-categories/new";
	        }
			
			return "redirect:" + UrlConstants.URL_PRODUCT_CATEGORY + "/" + status.get(1) + "?success";
		} catch (Exception e) {
			e.printStackTrace();
			return "errors/unexpected-error";
		}
	}
	
}
