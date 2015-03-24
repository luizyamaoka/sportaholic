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

import com.sportaholic.dto.UriDto;
import com.sportaholic.model.Uri;
import com.sportaholic.model.UrlConstants;
import com.sportaholic.service.AdmUriService;
import com.sportaholic.transformer.UriDtoTransformer;

@Controller
@RequestMapping(UrlConstants.URL_URI)
public class UriController {

	private AdmUriService admUriService;
	private UriDtoTransformer uriDtoTransformer;
	
	private static final Map<String, String> ERROR_MESSAGES;
	static {
		ERROR_MESSAGES = new HashMap<String, String>();
		ERROR_MESSAGES.put("uri.required", "A url precisa ser preenchida.");
		ERROR_MESSAGES.put("uri.startsWithSlash", "A url precisa começar com /");
		ERROR_MESSAGES.put("uri.containsSpaces", "A url nao pode conter espaços");
		ERROR_MESSAGES.put("uri.existant", "A url escolhida já existe.");
		ERROR_MESSAGES.put("friendlyUri.required", "A url amigável precisa ser preenchida.");
		ERROR_MESSAGES.put("friendlyUri.startsWithSlash", "A url amigável precisa começar com /");
		ERROR_MESSAGES.put("friendlyUri.containsSpaces", "A url amigável nao pode conter espaços");
		ERROR_MESSAGES.put("friendlyUri.existant", "A url amigável escolhida já existe.");
		ERROR_MESSAGES.put("uriName.required", "O nome da url precisa ser preenchido.");
		ERROR_MESSAGES.put("parentId.required", "Selecione a url pai.");
		ERROR_MESSAGES.put("metaDescription.required", "A meta description precisa ser preenchida.");
		ERROR_MESSAGES.put("metaDescription.length", "A meta description deve ter no máximo 160 caracteres.");
	}
	
	@Autowired
	public UriController(AdmUriService admUriService, UriDtoTransformer uriDtoTransformer) {
		this.admUriService = admUriService;
		this.uriDtoTransformer = uriDtoTransformer;
	}
	
	@RequestMapping("")
	public ModelAndView index() {
		try {
			ModelAndView modelAndView = new ModelAndView("uris/index");
			modelAndView.addObject("uris", this.admUriService.getAll());
			return modelAndView;
		} catch (Exception e) {
			e.printStackTrace();
			return new ModelAndView("errors/unexpected-error");
		}
	}
	
	@RequestMapping(value="/{id}", method=RequestMethod.GET)
	public ModelAndView show(@PathVariable Integer id, HttpServletRequest request) {
		try {
			ModelAndView modelAndView = new ModelAndView("uris/edit");
			
			Uri uri = this.admUriService.get(id);
			UriDto uriDto = this.uriDtoTransformer.uriToUriDto(uri);
			modelAndView.addObject("uriDto", uriDto);
			
			modelAndView.addObject("uris", this.admUriService.getAll());
			
			if(request.getParameter("success") != null)
				modelAndView.addObject("successes", "<strong>Sucesso!</strong> Uri criado com sucesso.");
			if(request.getParameter("edited") != null)
				modelAndView.addObject("successes", "<strong>Sucesso!</strong> Uri editado com sucesso.");
			
			return modelAndView;
		} catch (Exception e) {
			e.printStackTrace();
			return new ModelAndView("errors/unexpected-error");
		}
	}
	
	@RequestMapping(value="/{id}", method=RequestMethod.POST)
	public String edit(@PathVariable Integer id, UriDto uriDto, BindingResult result, Model m) {
		try {			
			uriDto.setUriId(id);
			List<String> status = this.admUriService.update(uriDto);
			
			if(status.get(0).equals("error")) {
	        	List<String> errors = new ArrayList<String>();
	        	for (int i = 1; i < status.size(); i++)
	        		errors.add(ERROR_MESSAGES.get(status.get(i)));
	        	m.addAttribute("errors", errors);
	        	
				m.addAttribute("uris", this.admUriService.getAll());
	        	
	            return "uris/edit";
	        }
			
			return "redirect:" + UrlConstants.URL_URI + "/" + status.get(1) + "?edited";
		} catch (Exception e) {
			e.printStackTrace();
			return "errors/unexpected-error";
		}
	}
	
	@RequestMapping(value="/new", method=RequestMethod.GET)
	public ModelAndView newForm() {
		try {
			ModelAndView modelAndView = new ModelAndView("uris/new");
			modelAndView.addObject("uriDto", new UriDto());
			
			modelAndView.addObject("uris", this.admUriService.getAll());
			
			return modelAndView;
		} catch (Exception e) {
			e.printStackTrace();
			return new ModelAndView("errors/unexpected-error");
		}
	}
	
	@RequestMapping(value="/new", method=RequestMethod.POST)
	public String insert(UriDto uriDto, BindingResult result, Model m) {
		try {			
			List<String> status = this.admUriService.create(uriDto);
			
			if(status.get(0).equals("error")) {
	        	List<String> errors = new ArrayList<String>();
	        	for (int i = 1; i < status.size(); i++) {
	        		errors.add(ERROR_MESSAGES.get(status.get(i)));
	        	}
	        	m.addAttribute("errors", errors);
	        	
				m.addAttribute("uris", this.admUriService.getAll());
	        	
	            return "uris/new";
	        }
			
			return "redirect:" + UrlConstants.URL_URI + "/" + status.get(1) + "?success";
		} catch (Exception e) {
			e.printStackTrace();
			return "errors/unexpected-error";
		}
	}
	
}
