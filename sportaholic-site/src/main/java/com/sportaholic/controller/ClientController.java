package com.sportaholic.controller;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.sportaholic.dto.ClientDto;
import com.sportaholic.helper.Helper;
import com.sportaholic.model.Client;
import com.sportaholic.model.Uri;
import com.sportaholic.model.UrlConstants;
import com.sportaholic.service.ClientService;
import com.sportaholic.service.SmtpService;
import com.sportaholic.service.UriService;

@Controller
@RequestMapping(UrlConstants.URL_CLIENT)
public class ClientController {

	private ClientService clientService;
	private SmtpService smtpService;
	
	private static final Map<String, String> ERROR_MESSAGES;
	static {
		ERROR_MESSAGES = new HashMap<String, String>();
		ERROR_MESSAGES.put("firstName.required", "O nome precisa ser preenchido.");
		ERROR_MESSAGES.put("email.required", "O email precisa ser preenchido.");
		ERROR_MESSAGES.put("birthYear.past", "O ano de nascimento não pode ser no futuro.");
		ERROR_MESSAGES.put("birthMonth.impossible", "O mês de nascimento é inválido.");
		ERROR_MESSAGES.put("birthDay.impossible", "O dia de nascimento é inválido.");
		ERROR_MESSAGES.put("birthDate.incomplete", "Sua data de nascimento está incompleta.");
		ERROR_MESSAGES.put("password.required", "A senha precisa ser preenchido.");
		ERROR_MESSAGES.put("passwordConfirmation.required", "A confirmação da senha precisa ser preenchido.");
		ERROR_MESSAGES.put("password.match", "A senha e a confirmação precisam ser iguais.");
		ERROR_MESSAGES.put("email.existant", "Este e-mail já possui um cadastro.");
	}
	
	@Autowired
	public ClientController(ClientService clientService, SmtpService smtpService) {
		this.clientService = clientService;
		this.smtpService = smtpService;
	}
	
	@RequestMapping(value="/{id}")
	public ModelAndView get(@PathVariable Integer id, HttpServletRequest request) {
		try {
			ModelAndView modelAndView = new ModelAndView("clients/show");
			Client client = this.clientService.get(id);
			modelAndView.addObject("client", client);
			modelAndView.addObject("username", SecurityContextHolder.getContext().getAuthentication().getName());
			
			UriService uriService = (UriService)request.getAttribute("uriService");
			List<Uri> uris = new ArrayList<Uri>();
			uris.add(uriService.getByUri("/"));
			uris.add(new Uri(null, client.getCompleteName(), client.getUri(), client.getUri()));
			modelAndView.addObject("uris", uris);
			
			if(request.getParameter("success") != null)
				modelAndView.addObject("successes", "<strong>Sucesso!</strong> Cadastro concluído com sucesso.");
			if(request.getParameter("edited") != null)
				modelAndView.addObject("successes", "<strong>Sucesso!</strong> Cadastro alterado com sucesso.");
			return modelAndView;
		} catch (Exception e) {
			e.printStackTrace();
			return new ModelAndView("errors/unexpected-error");
		}
	}
	
	@RequestMapping(value="/profile")
	public ModelAndView gerProfile(HttpServletRequest request) {
		try {
			ModelAndView modelAndView = new ModelAndView("clients/show");
			
			String email = SecurityContextHolder.getContext().getAuthentication().getName();
			
			modelAndView.addObject("client", this.clientService.getByEmail(email));
			modelAndView.addObject("username", email);
			
			return modelAndView;
		} catch (Exception e) {
			e.printStackTrace();
			return new ModelAndView("errors/unexpected-error");
		}
	}
	
	@RequestMapping(value="/new", method=RequestMethod.GET)
	public ModelAndView newForm() {
		try {
			ModelAndView modelAndView = new ModelAndView("clients/new");
			modelAndView.addObject("clientDto", new ClientDto());
			return modelAndView;
		} catch (Exception e) {
			e.printStackTrace();
			return new ModelAndView("errors/unexpected-error");
		}
	}
	
	@RequestMapping(value="/new", method=RequestMethod.POST)
    public String insert(ClientDto clientDto, BindingResult result, Model m, 
    		HttpServletResponse response, HttpServletRequest request) {
		try {
			List<String> status = this.clientService.create(clientDto);
			
	        if(status.get(0).equals("error")) {
	        	List<String> errors = new ArrayList<String>();
	        	for (int i = 1; i < status.size(); i++)
	        		errors.add(ERROR_MESSAGES.get(status.get(i)));
	        	m.addAttribute("errors", errors);
	            return "clients/new";
	        }
	        
	        try {
		        InputStream resourceContent = request.getSession()
		        		.getServletContext().getResourceAsStream("/resources/transactional-emails/welcome.html");
		        
		        String emailBody = Helper.getStringFromInputStream(resourceContent);
		        emailBody = emailBody.replaceAll(":clientName", clientDto.getFirstName());
		        
		        String subject = clientDto.getGender().equals("f") ? "Bem-vinda à Sportaholic" : "Bem-vindo à Sportaholic";
		        emailBody = emailBody.replaceAll(":welcomeMessage", subject);
		        
				this.smtpService.sendEmail("Sportaholic", clientDto.getEmail(), subject, emailBody);
	        } catch (Exception e) {
	        	e.printStackTrace();
	        }
	        return "redirect:/login?subscribed";
		} catch (Exception e) {
			e.printStackTrace();
			return "errors/unexpected-error";
		}
    }
	
	@RequestMapping(value="/{id}/edit", method=RequestMethod.GET)
    public ModelAndView edit(@PathVariable int id, Model m, HttpServletRequest request) {
		try {
			String email = SecurityContextHolder.getContext().getAuthentication().getName();
			Client client = this.clientService.getByEmail(email);
			
			// if user is trying to access the edit page from another user
			if (client.getId() != id) 
				return new ModelAndView("error/403");
			
			ModelAndView modelAndView = new ModelAndView("clients/edit");
			modelAndView.addObject("clientDto", client.toClientDto());
			
			UriService uriService = (UriService)request.getAttribute("uriService");
			List<Uri> uris = new ArrayList<Uri>();
			uris.add(uriService.getByUri("/"));
			uris.add(new Uri(null, "Meu perfil", client.getUri(), client.getUri()));
			Uri activeUri = new Uri(-1, "Editar perfil", client.getEditUri(), client.getEditUri());
			uris.add(activeUri);
			modelAndView.addObject("uris", uris);
			modelAndView.addObject("activeUri", activeUri);
			
	        return modelAndView;
		} catch (Exception e) {
			e.printStackTrace();
			return new ModelAndView("error/unexpected-error");
		}
    }
	
	@RequestMapping(value="/{id}/edit", method=RequestMethod.POST)
    public String update(@PathVariable int id, ClientDto clientDto, BindingResult result, Model m) {
		try {
			String email = SecurityContextHolder.getContext().getAuthentication().getName();
			Client client = this.clientService.getByEmail(email);
			
			// if user is trying to access the edit page from another user
			if (client.getId() != id) 
				return "error/403";
			
			List<String> status = this.clientService.update(clientDto);
			
	        if(status.get(0).equals("error")) {
	        	List<String> errors = new ArrayList<String>();
	        	for (int i = 1; i < status.size(); i++)
	        		errors.add(ERROR_MESSAGES.get(status.get(i)));
	        	m.addAttribute("errors", errors);
	            return "clients/edit";
	        }
	         
	        return "redirect:/clients/" + status.get(1) + "?edited";
		} catch (Exception e) {
			e.printStackTrace();
			return "error/unexpected-error";
		}
    }
	
	@RequestMapping(value="/edit-password", method=RequestMethod.GET)
	public ModelAndView editPassword(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView modelAndView = new ModelAndView("clients/edit-password");
		if(request.getParameter("success") != null)
			modelAndView.addObject("successes", "<strong>Sucesso!</strong> Senha alterada com sucesso.");
		return modelAndView;
	}
	
	@RequestMapping(value="/edit-password", method=RequestMethod.POST)
    public String updatePassword(Model m,
    		@RequestParam String password,
    		@RequestParam String newPassword,
    		@RequestParam String newPasswordConfirmation) {
		try {
			String email = SecurityContextHolder.getContext().getAuthentication().getName();
			Client client = this.clientService.getByEmail(email);
			
			if (!client.isPasswordCorrect(password)) {
				m.addAttribute("errors", "<strong>Senha incorreta!</strong> Tente novamente.");
				return "clients/edit-password";
			}
				
			ClientDto clientDto = client.toClientDto();
			clientDto.setPassword(newPassword);
			clientDto.setPasswordConfirmation(newPasswordConfirmation);
			List<String> status = this.clientService.update(clientDto);
			
	        if(status.get(0).equals("error")) {
	        	List<String> errors = new ArrayList<String>();
	        	for (int i = 1; i < status.size(); i++)
	        		errors.add(ERROR_MESSAGES.get(status.get(i)));
	        	m.addAttribute("errors", errors);
	            return "clients/edit-password";
	        }
	         
	        return "redirect:/clients/edit-password?success";
		} catch (Exception e) {
			e.printStackTrace();
			return "error/unexpected-error";
		}
    }
	
	@RequestMapping(value="/forgot-password", method=RequestMethod.GET)
	public ModelAndView forgotPassword(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView modelAndView = new ModelAndView("clients/forgot-password");
		if(request.getParameter("success") != null)
			modelAndView.addObject("successes", "<strong>Sucesso!</strong> Um e-mail foi enviado com a sua nova senha.");
		return modelAndView;
	}
	
	@RequestMapping(value="/forgot-password", method=RequestMethod.POST)
    public String updatePassword(Model m, HttpServletRequest request,
    		@RequestParam String email) {
		try {
			Client client = this.clientService.getByEmail(email);
			
			if (client == null) {
				m.addAttribute("errors", "<strong>Erro!</strong> E-mail inexistente.");
	            return "clients/forgot-password";
			}
				
			InputStream resourceContent = request.getSession()
	        		.getServletContext().getResourceAsStream("/resources/transactional-emails/password-changed.html");
	        
	        String emailBody = Helper.getStringFromInputStream(resourceContent);
			this.clientService.changeRandomPassword(client, emailBody);
	         
	        return "redirect:/clients/forgot-password?success";
		} catch (Exception e) {
			e.printStackTrace();
			return "error/unexpected-error";
		}
    }
	
}
