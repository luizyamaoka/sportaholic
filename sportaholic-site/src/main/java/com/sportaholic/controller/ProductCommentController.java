package com.sportaholic.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.sportaholic.dto.ProductCommentDto;
import com.sportaholic.model.Client;
import com.sportaholic.model.ProductComment;
import com.sportaholic.model.UrlConstants;
import com.sportaholic.service.ClientService;
import com.sportaholic.service.ProductCommentService;
import com.sportaholic.transformer.ProductCommentTransformer;

@Controller
@RequestMapping(UrlConstants.URL_PRODUCT_COMMENT)
public class ProductCommentController {

	private ProductCommentService productCommentService;
	private ProductCommentTransformer productCommentTransformer;
	private ClientService clientService;
	
	@Autowired
	public ProductCommentController(ProductCommentService productCommentService,
			ProductCommentTransformer productCommentTransformer,
			ClientService clientService) {
		this.productCommentService = productCommentService;
		this.productCommentTransformer = productCommentTransformer;
		this.clientService = clientService;
	}

	@RequestMapping(value="/new", method=RequestMethod.POST)
	public String create(@RequestParam Integer productId, @RequestParam String content, @RequestParam Integer grade) {
		try {
			String email = SecurityContextHolder.getContext().getAuthentication().getName();
			Client client = this.clientService.getByEmail(email);
			
			ProductCommentDto productCommentDto = new ProductCommentDto();
			productCommentDto.setClientId(client.getId());
			productCommentDto.setProductId(productId);
			productCommentDto.setContent(content);
			productCommentDto.setGrade(grade);
			
			ProductComment productComment = this.productCommentTransformer.productCommentDtoToProductComment(productCommentDto);
			
			List<String> status = this.productCommentService.create(productComment);
	         
	        return "redirect:" + UrlConstants.URL_PRODUCT + "/" + productId + "?commented";
		} catch (Exception e) {
			e.printStackTrace();
			return "errors/unexpected-error";
		}
	}
	
}
