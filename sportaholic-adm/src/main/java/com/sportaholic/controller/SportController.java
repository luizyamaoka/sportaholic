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

import com.sportaholic.dto.SportDto;
import com.sportaholic.model.Sport;
import com.sportaholic.model.UrlConstants;
import com.sportaholic.service.AdmSportService;
import com.sportaholic.service.SportService;
import com.sportaholic.service.UriService;
import com.sportaholic.transformer.SportDtoTransformer;

@Controller
@RequestMapping(UrlConstants.URL_SPORT)
public class SportController {

	private SportService sportService;
	private UriService uriService;
	private AdmSportService admSportService;
	private SportDtoTransformer sportDtoTransformer;
	
	private static final Map<String, String> ERROR_MESSAGES;
	static {
		ERROR_MESSAGES = new HashMap<String, String>();
		ERROR_MESSAGES.put("name.required", "O nome do autor precisa ser fornecido.");
		ERROR_MESSAGES.put("description.required", "A descrição do autor precisa ser fornecida");
		ERROR_MESSAGES.put("banner.required", "O banner precisa ser fornecido");
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
	public SportController(SportService sportService, UriService uriService, 
			AdmSportService admSportService, SportDtoTransformer sportDtoTransformer) {
		this.sportService = sportService;
		this.uriService = uriService;
		this.admSportService = admSportService;
		this.sportDtoTransformer = sportDtoTransformer;
	}
	
	@RequestMapping("")
	public ModelAndView index() {
		try {
			ModelAndView modelAndView = new ModelAndView("sports/index");
			modelAndView.addObject("sports", this.sportService.getAll());
			return modelAndView;
			
		} catch (Exception e) {
			e.printStackTrace();
			return new ModelAndView("errors/unexpected-error");
		}
	}
	
	@RequestMapping(value="/{id}", method=RequestMethod.GET)
	public ModelAndView show(@PathVariable Integer id, HttpServletRequest request) {
		try {
			ModelAndView modelAndView = new ModelAndView("sports/edit");
			
			Sport sport = this.sportService.get(id);
			SportDto sportDto = this.sportDtoTransformer.sportToSportDto(sport);
			modelAndView.addObject("sportDto", sportDto);
			
			modelAndView.addObject("uris", this.uriService.getAll());
			
			if(request.getParameter("success") != null)
				modelAndView.addObject("successes", "<strong>Sucesso!</strong> Esporte criado com sucesso.");
			if(request.getParameter("edited") != null)
				modelAndView.addObject("successes", "<strong>Sucesso!</strong> Esporte editado com sucesso.");
			
			return modelAndView;
		} catch (Exception e) {
			e.printStackTrace();
			return new ModelAndView("errors/unexpected-error");
		}
	}
	
	@RequestMapping(value="/{id}", method=RequestMethod.POST)
	public String edit(@PathVariable Integer id, SportDto sportDto, BindingResult result, Model m) {
		try {			
			sportDto.setId(id);
			List<String> status = this.admSportService.update(sportDto);
			
			if(status.get(0).equals("error")) {
	        	List<String> errors = new ArrayList<String>();
	        	for (int i = 1; i < status.size(); i++)
	        		errors.add(ERROR_MESSAGES.get(status.get(i)));
	        	m.addAttribute("errors", errors);
	        	
				m.addAttribute("uris", this.uriService.getAll());
	        	
	            return "sports/edit";
	        }
			
			return "redirect:" + UrlConstants.URL_SPORT + "/" + status.get(1) + "?edited";
		} catch (Exception e) {
			e.printStackTrace();
			return "errors/unexpected-error";
		}
	}
	
	@RequestMapping(value="/new", method=RequestMethod.GET)
	public ModelAndView newForm() {
		try {
			ModelAndView modelAndView = new ModelAndView("sports/new");
			modelAndView.addObject("sportDto", new SportDto());
			
			modelAndView.addObject("uris", this.uriService.getAll());
			
			return modelAndView;
		} catch (Exception e) {
			e.printStackTrace();
			return new ModelAndView("errors/unexpected-error");
		}
	}
	
	@RequestMapping(value="/new", method=RequestMethod.POST)
	public String insert(SportDto sportDto, BindingResult result, Model m) {
		try {			
			List<String> status = this.admSportService.create(sportDto);
			
			if(status.get(0).equals("error")) {
	        	List<String> errors = new ArrayList<String>();
	        	for (int i = 1; i < status.size(); i++) {
	        		errors.add(ERROR_MESSAGES.get(status.get(i)));
	        	}
	        	m.addAttribute("errors", errors);
	        	
				m.addAttribute("uris", this.uriService.getAll());
	        	
	            return "sports/new";
	        }
			
			return "redirect:" + UrlConstants.URL_SPORT + "/" + status.get(1) + "?success";
		} catch (Exception e) {
			e.printStackTrace();
			return "errors/unexpected-error";
		}
	}
	
}
