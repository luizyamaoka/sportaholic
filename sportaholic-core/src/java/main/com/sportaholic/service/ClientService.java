package com.sportaholic.service;

import java.util.List;

import com.sportaholic.dto.ClientDto;
import com.sportaholic.model.Client;

public interface ClientService {

	public Client get(int id) throws Exception;
	
	public Client getByEmail(String email) throws Exception;
	
	public List<String> create(ClientDto clientDto) throws Exception;
	
	public List<String> update(ClientDto clientDto) throws Exception;
	
	public void changeRandomPassword(Client client, String emailBody) throws Exception;
}
