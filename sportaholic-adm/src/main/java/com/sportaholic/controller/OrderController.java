package com.sportaholic.controller;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.sportaholic.helper.Helper;
import com.sportaholic.model.UrlConstants;
import com.sportaholic.service.AdmOrderService;
import com.sportaholic.service.AdmProductService;

@Controller
@RequestMapping(UrlConstants.URL_ORDER)
public class OrderController {
	
	private AdmOrderService admOrderService;
	private AdmProductService admProductService;
	
	private static final Map<String, String> ERROR_MESSAGES;
	static {
		ERROR_MESSAGES = new HashMap<String, String>();
		ERROR_MESSAGES.put("product.required", "O produto precisa ser fornecido");
		ERROR_MESSAGES.put("product.not_found", "O produto especificado não foi encontrado");
		ERROR_MESSAGES.put("client.email.required", "O e-mail precisa ser fornecido");
		ERROR_MESSAGES.put("client.name.required", "O nome do cliente precisa ser fornecido");
		

	}
	
	@Autowired
	public OrderController(AdmOrderService admOrderService, 
			AdmProductService admProductService) {
		this.admOrderService = admOrderService;
		this.admProductService = admProductService;
	}
	
	@RequestMapping(value="/ask-for-review", method=RequestMethod.GET)
	public ModelAndView askForReview(HttpServletRequest request) {
		try {
			ModelAndView modelAndView = new ModelAndView("orders/ask-for-review");
			
			modelAndView.addObject("products", this.admProductService.getAll());
			
			if(request.getParameter("success") != null)
				modelAndView.addObject("successes", "<strong>Sucesso!</strong> E-mail de review enviado com sucesso");
			
			return modelAndView;
		} catch (Exception e) {
			e.printStackTrace();
			return new ModelAndView("errors/unexpected-error");
		}
	}
	
	@RequestMapping(value="/ask-for-review", method=RequestMethod.POST)
	public String askForReviewPost(HttpServletRequest request, Model m,
			@RequestParam Integer productId, @RequestParam String clientEmail, @RequestParam String clientName) {
		try {
				
			InputStream resourceContent = request.getSession()
	        		.getServletContext().getResourceAsStream("/resources/transactional-emails/ask-for-review.html");
	        
	        String emailBody = Helper.getStringFromInputStream(resourceContent);
			List<String> status = this.admOrderService.askForReview(emailBody, clientEmail, clientName, productId);
	         
			if (status.get(0).equals("error")) {
				List<String> errors = new ArrayList<String>();
	        	for (int i = 1; i < status.size(); i++)
	        		errors.add(ERROR_MESSAGES.get(status.get(i)));
	        	m.addAttribute("errors", errors);
	        	m.addAttribute("products", this.admProductService.getAll());
	            return "orders/ask-for-review";
			}
			
	        return "redirect:" + UrlConstants.URL_ORDER + "/ask-for-review?success";
		} catch (Exception e) {
			e.printStackTrace();
			return "errors/unexpected-error";
		}
	}
	

}
