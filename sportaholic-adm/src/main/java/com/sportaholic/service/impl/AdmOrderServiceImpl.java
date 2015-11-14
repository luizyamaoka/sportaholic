package com.sportaholic.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.sportaholic.dao.ProductDao;
import com.sportaholic.model.Product;
import com.sportaholic.service.AdmOrderService;
import com.sportaholic.service.SmtpService;
import com.sportaholic.service.UriService;

@Component
public class AdmOrderServiceImpl implements AdmOrderService {

	private ProductDao productDao;
	private SmtpService smtpService;
	private UriService uriService;
	
	@Autowired
	public AdmOrderServiceImpl(ProductDao productDao,
			SmtpService smtpService, UriService uriService) throws Exception {
		this.productDao = productDao;
		this.smtpService = smtpService;
		this.uriService = uriService;
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public List<String> askForReview(String emailBody, String clientEmail, 
			String clientName, Integer productId) throws Exception {
		
		List<String> status = new ArrayList<String>();
		status.add("success");
		
		if (productId == null) {
			status.set(0, "error");
			status.add("product.required");
		}
		if (clientEmail == null || clientEmail.equals("")) {
			status.set(0, "error");
			status.add("client.email.required");
		}
		if (clientName == null || clientName.equals("")) {
			status.set(0, "error");
			status.add("client.name.required");
		}
		
		if (status.get(0).equals("error")) return status;
		
		Product product = this.productDao.get(productId);
		
		if (product == null) {
			status.set(0, "error");
			status.add("product.not_found");
		}
		
		if (status.get(0).equals("error")) return status;
		
        emailBody = emailBody.replaceAll(":clientName", clientName);
        emailBody = emailBody.replaceAll(":productImage", "https://s3-sa-east-1.amazonaws.com/sportaholic/images/" + product.getImage());
        emailBody = emailBody.replaceAll(":productName", product.getName());
        emailBody = emailBody.replaceAll(":productUrl", "http://www.sportaholic.com.br" + this.uriService.getFriendlyUri("/products/" + product.getId().toString()));
        
		this.smtpService.sendEmail("Sportaholic", clientEmail, "Gostou do produto: " + product.getName() + "?", emailBody);
		
		return status;
	}
	
}
