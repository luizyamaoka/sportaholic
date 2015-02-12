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

import com.sportaholic.dto.BrandDto;
import com.sportaholic.model.Brand;
import com.sportaholic.model.UrlConstants;
import com.sportaholic.service.AdmBrandService;
import com.sportaholic.service.BrandService;
import com.sportaholic.service.UriService;
import com.sportaholic.transformer.BrandDtoTransformer;

@Controller
@RequestMapping(UrlConstants.URL_BRAND)
public class BrandController {

	private BrandService brandService;
	private UriService uriService;
	private AdmBrandService admBrandService;
	private BrandDtoTransformer brandDtoTransformer;
	
	private static final Map<String, String> ERROR_MESSAGES;
	static {
		ERROR_MESSAGES = new HashMap<String, String>();
		ERROR_MESSAGES.put("name.required", "O nome do autor precisa ser fornecido.");
		ERROR_MESSAGES.put("description.required", "A descrição do autor precisa ser fornecida");
		ERROR_MESSAGES.put("logo.required", "O logo precisa ser fornecido");
		ERROR_MESSAGES.put("friendlyUri.required", "A url amigável precisa ser preenchida.");
		ERROR_MESSAGES.put("friendlyUri.startsWithSlash", "A url amigável precisa começar com /");
		ERROR_MESSAGES.put("friendlyUri.containsSpaces", "A url amigável nao pode conter espaços");
		ERROR_MESSAGES.put("friendlyUri.existant", "A url amigável escolhida já existe.");
		ERROR_MESSAGES.put("urName.required", "O nome da url precisa ser preenchido.");
		ERROR_MESSAGES.put("parentId.required", "Selecione a url pai.");
		ERROR_MESSAGES.put("metaDescription.required", "A meta description precisa ser preenchida.");
	}
	
	@Autowired
	public BrandController(BrandService brandService, UriService uriService,
			AdmBrandService admBrandService, BrandDtoTransformer brandDtoTransformer) {
		this.brandService = brandService;
		this.uriService = uriService;
		this.admBrandService = admBrandService;
		this.brandDtoTransformer = brandDtoTransformer;
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
	
	@RequestMapping(value="/{id}", method=RequestMethod.GET)
	public ModelAndView show(@PathVariable Integer id, HttpServletRequest request) {
		try {
			ModelAndView modelAndView = new ModelAndView("brands/edit");
			
			Brand brand = this.brandService.get(id);
			BrandDto brandDto = this.brandDtoTransformer.brandToBrandDto(brand);
			modelAndView.addObject("brandDto", brandDto);
			
			modelAndView.addObject("uris", this.uriService.getAll());
			
			if(request.getParameter("success") != null)
				modelAndView.addObject("successes", "<strong>Sucesso!</strong> Marca criado com sucesso.");
			if(request.getParameter("edited") != null)
				modelAndView.addObject("successes", "<strong>Sucesso!</strong> Marca editado com sucesso.");
			
			return modelAndView;
		} catch (Exception e) {
			e.printStackTrace();
			return new ModelAndView("errors/unexpected-error");
		}
	}
	
	@RequestMapping(value="/{id}", method=RequestMethod.POST)
	public String edit(@PathVariable Integer id, BrandDto brandDto, BindingResult result, Model m) {
		try {			
			brandDto.setId(id);
			List<String> status = this.admBrandService.update(brandDto);
			
			if(status.get(0).equals("error")) {
	        	List<String> errors = new ArrayList<String>();
	        	for (int i = 1; i < status.size(); i++)
	        		errors.add(ERROR_MESSAGES.get(status.get(i)));
	        	m.addAttribute("errors", errors);
	        	
				m.addAttribute("uris", this.uriService.getAll());
	        	
	            return "brands/edit";
	        }
			
			return "redirect:" + UrlConstants.URL_BRAND + "/" + status.get(1) + "?edited";
		} catch (Exception e) {
			e.printStackTrace();
			return "errors/unexpected-error";
		}
	}
	
	@RequestMapping(value="/new", method=RequestMethod.GET)
	public ModelAndView newForm() {
		try {
			ModelAndView modelAndView = new ModelAndView("brands/new");
			modelAndView.addObject("brandDto", new BrandDto());
			
			modelAndView.addObject("uris", this.uriService.getAll());
			
			return modelAndView;
		} catch (Exception e) {
			e.printStackTrace();
			return new ModelAndView("errors/unexpected-error");
		}
	}
	
	@RequestMapping(value="/new", method=RequestMethod.POST)
	public String insert(BrandDto brandDto, BindingResult result, Model m) {
		try {			
			List<String> status = this.admBrandService.create(brandDto);
			
			if(status.get(0).equals("error")) {
	        	List<String> errors = new ArrayList<String>();
	        	for (int i = 1; i < status.size(); i++) {
	        		errors.add(ERROR_MESSAGES.get(status.get(i)));
	        	}
	        	m.addAttribute("errors", errors);
	        	
				m.addAttribute("uris", this.uriService.getAll());
	        	
	            return "brands/new";
	        }
			
			return "redirect:" + UrlConstants.URL_BRAND + "/" + status.get(1) + "?success";
		} catch (Exception e) {
			e.printStackTrace();
			return "errors/unexpected-error";
		}
	}
	
}
