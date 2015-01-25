package com.sportaholic.service;

public interface SmtpService {

	public void sendEmail(String from, String to, String subject, String body) throws Exception;
	
}
