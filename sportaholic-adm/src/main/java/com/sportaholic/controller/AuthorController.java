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

import com.sportaholic.dto.AuthorDto;
import com.sportaholic.model.Author;
import com.sportaholic.model.UrlConstants;
import com.sportaholic.service.AdmAuthorService;
import com.sportaholic.service.AuthorService;
import com.sportaholic.service.UriService;
import com.sportaholic.transformer.AuthorDtoTransformer;

@Controller
@RequestMapping(UrlConstants.URL_AUTHOR)
public class AuthorController {

	private AuthorService authorService;
	private UriService uriService;
	private AuthorDtoTransformer authorDtoTransformer;
	private AdmAuthorService admAuthorService;
	
	private static final Map<String, String> ERROR_MESSAGES;
	static {
		ERROR_MESSAGES = new HashMap<String, String>();
		ERROR_MESSAGES.put("name.required", "O nome do autor precisa ser fornecido.");
		ERROR_MESSAGES.put("description.required", "A descrição do autor precisa ser fornecida");
		ERROR_MESSAGES.put("friendlyUri.required", "A url amigável precisa ser preenchida.");
		ERROR_MESSAGES.put("friendlyUri.startsWithSlash", "A url amigável precisa começar com /");
		ERROR_MESSAGES.put("friendlyUri.containsSpaces", "A url amigável nao pode conter espaços");
		ERROR_MESSAGES.put("friendlyUri.existant", "A url amigável escolhida já existe.");
		ERROR_MESSAGES.put("urName.required", "O nome da url precisa ser preenchido.");
		ERROR_MESSAGES.put("parentId.required", "Selecione a url pai.");
		ERROR_MESSAGES.put("metaDescription.required", "A meta description precisa ser preenchida.");
		ERROR_MESSAGES.put("metaDescription.length", "A meta description deve ter no máximo 160 caracteres.");
		
	}
	
	@Autowired
	public AuthorController(AuthorService authorService, UriService uriService, 
			AuthorDtoTransformer authorDtoTransformer, AdmAuthorService admAuthorService) {
		this.authorService = authorService;
		this.uriService = uriService;
		this.authorDtoTransformer = authorDtoTransformer;
		this.admAuthorService = admAuthorService;
	}
	
	@RequestMapping("")
	public ModelAndView index() {
		try {
			ModelAndView modelAndView = new ModelAndView("authors/index");
			
			modelAndView.addObject("authors", this.authorService.getAll());
			
			return modelAndView;
		} catch (Exception e) {
			e.printStackTrace();
			return new ModelAndView("errors/unexpected-error");
		}
	}
	
	@RequestMapping(value="/{id}", method=RequestMethod.GET)
	public ModelAndView show(@PathVariable Integer id, HttpServletRequest request) {
		try {
			ModelAndView modelAndView = new ModelAndView("authors/edit");
			
			Author author = this.authorService.get(id);
			AuthorDto authorDto = this.authorDtoTransformer.authorToAuthorDto(author);
			modelAndView.addObject("authorDto", authorDto);
			
			modelAndView.addObject("uris", this.uriService.getAll());
			
			if(request.getParameter("success") != null)
				modelAndView.addObject("successes", "<strong>Sucesso!</strong> Autor criado com sucesso.");
			if(request.getParameter("edited") != null)
				modelAndView.addObject("successes", "<strong>Sucesso!</strong> Autor editado com sucesso.");
			
			return modelAndView;
		} catch (Exception e) {
			e.printStackTrace();
			return new ModelAndView("errors/unexpected-error");
		}
	}
	
	@RequestMapping(value="/{id}", method=RequestMethod.POST)
	public String edit(@PathVariable Integer id, AuthorDto authorDto, BindingResult result, Model m) {
		try {			
			authorDto.setId(id);
			List<String> status = this.admAuthorService.update(authorDto);
			
			if(status.get(0).equals("error")) {
	        	List<String> errors = new ArrayList<String>();
	        	for (int i = 1; i < status.size(); i++)
	        		errors.add(ERROR_MESSAGES.get(status.get(i)));
	        	m.addAttribute("errors", errors);
	        	
				m.addAttribute("uris", this.uriService.getAll());
	        	
	            return "authors/edit";
	        }
			
			return "redirect:" + UrlConstants.URL_AUTHOR + "/" + status.get(1) + "?edited";
		} catch (Exception e) {
			e.printStackTrace();
			return "errors/unexpected-error";
		}
	}
	
	@RequestMapping(value="/new", method=RequestMethod.GET)
	public ModelAndView newForm() {
		try {
			ModelAndView modelAndView = new ModelAndView("authors/new");
			modelAndView.addObject("authorDto", new AuthorDto());
			
			modelAndView.addObject("uris", this.uriService.getAll());
			
			return modelAndView;
		} catch (Exception e) {
			e.printStackTrace();
			return new ModelAndView("errors/unexpected-error");
		}
	}
	
	@RequestMapping(value="/new", method=RequestMethod.POST)
	public String insert(AuthorDto authorDto, BindingResult result, Model m) {
		try {			
			List<String> status = this.admAuthorService.create(authorDto);
			
			if(status.get(0).equals("error")) {
	        	List<String> errors = new ArrayList<String>();
	        	for (int i = 1; i < status.size(); i++) {
	        		errors.add(ERROR_MESSAGES.get(status.get(i)));
	        	}
	        	m.addAttribute("errors", errors);
	        	
				m.addAttribute("uris", this.uriService.getAll());
	        	
	            return "authors/new";
	        }
			
			return "redirect:" + UrlConstants.URL_AUTHOR + "/" + status.get(1) + "?success";
		} catch (Exception e) {
			e.printStackTrace();
			return "errors/unexpected-error";
		}
	}
	
}
