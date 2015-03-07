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

import com.sportaholic.dto.ProductDto;
import com.sportaholic.model.Product;
import com.sportaholic.model.UrlConstants;
import com.sportaholic.service.AdmProductService;
import com.sportaholic.service.BrandService;
import com.sportaholic.service.ProductTypeService;
import com.sportaholic.service.SportService;
import com.sportaholic.service.UriService;
import com.sportaholic.transformer.ProductDtoTransformer;

@Controller
@RequestMapping(UrlConstants.URL_PRODUCT)
public class ProductController {

	private AdmProductService admProductService;
	private SportService sportService;
	private ProductTypeService productTypeService;
	private UriService uriService;
	private ProductDtoTransformer productDtoTransformer;
	private BrandService brandService;
	
	private static final Map<String, String> ERROR_MESSAGES;
	static {
		ERROR_MESSAGES = new HashMap<String, String>();
		ERROR_MESSAGES.put("productId.required", "O id do produto precisa ser fornecido.");
		ERROR_MESSAGES.put("name.required", "O nome do produto precisa ser fornecido.");
		ERROR_MESSAGES.put("description.required", "A descrição do produto precisa ser fornecida.");
		ERROR_MESSAGES.put("image.required", "A imagem do produto precisa ser fornecida.");
		ERROR_MESSAGES.put("price.required", "O preço do produto precisa ser fornecido.");
		ERROR_MESSAGES.put("price.negative", "O preço do produto precisa ser positivo.");
		ERROR_MESSAGES.put("isActive.required", "O produto precisa estar ativo ou inativo.");
		ERROR_MESSAGES.put("inStock.required", "O estoque do produto precisa ser fornecido.");
		ERROR_MESSAGES.put("brandId.required", "A marca do produto precisa ser fornecida.");
		ERROR_MESSAGES.put("productIsTypes.required", "Pelo menos uma categorização precisa ser selecionada.");
		ERROR_MESSAGES.put("productIsSports.required", "Pelo menos um esporte precisa ser selecionado.");
		
		ERROR_MESSAGES.put("friendlyUri.required", "A url amigável precisa ser preenchida.");
		ERROR_MESSAGES.put("friendlyUri.startsWithSlash", "A url amigável precisa começar com /");
		ERROR_MESSAGES.put("friendlyUri.containsSpaces", "A url amigável nao pode conter espaços");
		ERROR_MESSAGES.put("friendlyUri.existant", "A url amigável escolhida já existe.");
		ERROR_MESSAGES.put("name.required", "O nome da url precisa ser preenchido.");
		ERROR_MESSAGES.put("parentId.required", "Selecione a url pai.");
		ERROR_MESSAGES.put("metaDescription.required", "A meta description precisa ser preenchida.");
		
	}
	
	@Autowired
	public ProductController(AdmProductService admProductService,
			SportService sportService, ProductTypeService productTypeService,
			UriService uriService, ProductDtoTransformer productDtoTransformer,
			BrandService brandService) {
		this.admProductService = admProductService;
		this.sportService = sportService;
		this.productTypeService = productTypeService;
		this.uriService = uriService;
		this.productDtoTransformer = productDtoTransformer;
		this.brandService = brandService;
	}
	
	@RequestMapping("")
	public ModelAndView getAll() {
		try {
			ModelAndView modelAndView = new ModelAndView("products/index");
			modelAndView.addObject("products", this.admProductService.getAll());
			return modelAndView;
		} catch (Exception e) {
			e.printStackTrace();
			return new ModelAndView("errors/unexpected-error");
		}
	}
	
	@RequestMapping(value="/{id}", method=RequestMethod.GET)
	public ModelAndView show(@PathVariable Integer id, HttpServletRequest request) {
		try {
			ModelAndView modelAndView = new ModelAndView("products/edit");
			
			Product product = this.admProductService.getEager(id);
			ProductDto productDto = this.productDtoTransformer.productToProductDto(product);
			modelAndView.addObject("productDto", productDto);
			
			modelAndView.addObject("sports", this.sportService.getAll());
			modelAndView.addObject("brands", this.brandService.getAll());
			modelAndView.addObject("productTypes", this.productTypeService.getAll());
			modelAndView.addObject("uris", this.uriService.getAll());
			
			if(request.getParameter("success") != null)
				modelAndView.addObject("successes", "<strong>Sucesso!</strong> Produto criado com sucesso.");
			if(request.getParameter("edited") != null)
				modelAndView.addObject("successes", "<strong>Sucesso!</strong> Produto editado com sucesso.");
			
			return modelAndView;
		} catch (Exception e) {
			e.printStackTrace();
			return new ModelAndView("errors/unexpected-error");
		}
	}
	
	@RequestMapping(value="/{id}", method=RequestMethod.POST)
	public String edit(@PathVariable Integer id, ProductDto productDto, BindingResult result, Model m) {
		try {			
			productDto.setId(id);
			List<String> status = this.admProductService.update(productDto);
			
			if(status.get(0).equals("error")) {
	        	List<String> errors = new ArrayList<String>();
	        	for (int i = 1; i < status.size(); i++)
	        		errors.add(ERROR_MESSAGES.get(status.get(i)));
	        	m.addAttribute("errors", errors);
	        	
				m.addAttribute("sports", this.sportService.getAll());
				m.addAttribute("brands", this.brandService.getAll());
				m.addAttribute("productTypes", this.productTypeService.getAll());
				m.addAttribute("uris", this.uriService.getAll());
	        	
	            return "products/edit";
	        }
			
			return "redirect:" + UrlConstants.URL_PRODUCT + "/" + id.toString() + "?edited";
		} catch (Exception e) {
			e.printStackTrace();
			return "errors/unexpected-error";
		}
	}
	
	@RequestMapping(value="/new", method=RequestMethod.GET)
	public ModelAndView newForm() {
		try {
			ModelAndView modelAndView = new ModelAndView("products/new");
			modelAndView.addObject("productDto", new ProductDto());
			
			modelAndView.addObject("sports", this.sportService.getAll());
			modelAndView.addObject("brands", this.brandService.getAll());
			modelAndView.addObject("productTypes", this.productTypeService.getAll());
			modelAndView.addObject("uris", this.uriService.getAll());
			
			return modelAndView;
		} catch (Exception e) {
			e.printStackTrace();
			return new ModelAndView("errors/unexpected-error");
		}
	}
	
	@RequestMapping(value="/new", method=RequestMethod.POST)
	public String insert(ProductDto productDto, BindingResult result, Model m) {
		try {			
			List<String> status = this.admProductService.create(productDto);
			
			if(status.get(0).equals("error")) {
	        	List<String> errors = new ArrayList<String>();
	        	for (int i = 1; i < status.size(); i++) {
	        		errors.add(ERROR_MESSAGES.get(status.get(i)));
	        	}
	        	m.addAttribute("errors", errors);
	        	
				m.addAttribute("sports", this.sportService.getAll());
				m.addAttribute("brands", this.brandService.getAll());
				m.addAttribute("productTypes", this.productTypeService.getAll());
				m.addAttribute("uris", this.uriService.getAll());
	        	
	            return "products/new";
	        }
			
			return "redirect:" + UrlConstants.URL_PRODUCT + "/" + status.get(1) + "?success";
		} catch (Exception e) {
			e.printStackTrace();
			return "errors/unexpected-error";
		}
	}
	
}
