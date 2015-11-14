package com.sportaholic.service;

import java.util.List;

public interface AdmOrderService {

	public List<String> askForReview(String emailBody, String clientEmail,
			String clientName, Integer productId) throws Exception;
	
}
