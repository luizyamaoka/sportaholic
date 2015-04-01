package com.sportaholic.service.impl;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.sportaholic.dao.ClientDao;
import com.sportaholic.dto.ClientDto;
import com.sportaholic.helper.Helper;
import com.sportaholic.model.Client;
import com.sportaholic.service.ClientService;
import com.sportaholic.service.SmtpService;

@Component
public class ClientServiceImpl implements ClientService {

	private ClientDao clientDao;
	private SmtpService smtpService;
	
	@Autowired
	public ClientServiceImpl(ClientDao clientDao, SmtpService smtpService) {
		this.clientDao = clientDao;
		this.smtpService = smtpService;
	}
	
	@Override
	@Transactional(rollbackFor = Exception.class)
	public Client get(int id) throws Exception {
		return this.clientDao.get(id);
	}
	
	@Override
	@Transactional(rollbackFor = Exception.class)
	public Client getByEmail(String email) throws Exception {
		return this.clientDao.getByEmail(email);
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public List<String> create(ClientDto clientDto) throws Exception {
		List<String> status = this.testClientDto(clientDto);
		
		if(clientDto.getPassword().isEmpty()) {
			status.set(0, "error");
			status.add("password.required");
		}
		if(clientDto.getPasswordConfirmation().isEmpty()) {
			status.set(0, "error");
			status.add("passwordConfirmation.required");
		}
		
		if (status.get(0).equals("success")) {
			Client client = this.clientDtoToClient(clientDto);
			client.setIsAdministrator(false);
			client.setCreatedAt(Calendar.getInstance().getTime());
			client.setUpdatedAt(Calendar.getInstance().getTime());
			Integer id = this.clientDao.create(client);
			status.add(id.toString());
		}
		
		return status;
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public List<String> update(ClientDto clientDto) throws Exception {
		List<String> status = this.testClientDto(clientDto);
			
		if (status.get(0).equals("success")) {
			Client client = this.clientDtoToClient(clientDto);
			client.setUpdatedAt(Calendar.getInstance().getTime());
			this.clientDao.update(client);
			status.add(clientDto.getId().toString());
		}
		
		return status;
	}
	
	@Override
	@Transactional(rollbackFor = Exception.class)
	public void changeRandomPassword(Client client, String emailBody) throws Exception {

		String newPassword = Helper.getRandomString(10);
		client.setPassword(newPassword);
		this.clientDao.update(client);
		
        emailBody = emailBody.replaceAll(":clientName", client.getFirstName());
        emailBody = emailBody.replaceAll(":newPassword", newPassword);
        
		this.smtpService.sendEmail("Sportaholic", client.getEmail(), "Sua senha da Sportaholic foi alterada", emailBody);
	}
	
	private List<String> testClientDto(ClientDto clientDto) throws Exception {
		List<String> status = new ArrayList<String>();
		status.add("success");
		
		if (clientDto.getFirstName() == null) {
			status.set(0, "error");
			status.add("firstName.required");
		}
		if (clientDto.getEmail() == null) {
			status.set(0, "error");
			status.add("email.required");
		}
		if (clientDto.getBirthDay() != null && (clientDto.getBirthDay() < 1 || clientDto.getBirthDay() > 31)) {
			status.set(0, "error");
			status.add("birthDay.impossible");
		}
		if (clientDto.getBirthMonth() != null && (clientDto.getBirthMonth() < 1 || clientDto.getBirthMonth() > 12)) {
			status.set(0, "error");
			status.add("birthMonth.impossible");
		}
		if ((clientDto.getBirthYear() != null || clientDto.getBirthMonth() != null || clientDto.getBirthDay() != null) &&
				(clientDto.getBirthYear() == null || clientDto.getBirthMonth() == null || clientDto.getBirthDay() == null)) {
			status.set(0, "error");
			status.add("birthDate.incomplete");
		}
		if (clientDto.getBirthYear() != null && clientDto.getBirthYear() > Calendar.getInstance().get(Calendar.YEAR)) {
			status.set(0, "error");
			status.add("birthYear.past");
		}
		if (clientDto.getPassword() != null && !clientDto.isPasswordConfirmed()) {
			status.set(0, "error");
			status.add("password.match");
		}
		Client existantUser = this.clientDao.getByEmail(clientDto.getEmail());
		if(existantUser != null && existantUser.getId() != clientDto.getId()) {
			status.set(0, "error");
			status.add("email.existant");
		}
		
		return status;
	}
	
	private Client clientDtoToClient(ClientDto clientDto) throws Exception {
		Client client = clientDto.getId() == null ? new Client() : this.clientDao.get(clientDto.getId());
		
		if (clientDto.getFirstName() != null)
			client.setFirstName(clientDto.getFirstName());
		if (clientDto.getLastName() != null)
			client.setLastName(clientDto.getLastName());
		if (clientDto.getEmail() != null)
			client.setEmail(clientDto.getEmail());
		if (clientDto.getCpf() != null)
			client.setCpf(clientDto.getCpf());
		if (clientDto.getPassword() != null)
			client.setPassword(clientDto.getPassword());
		if (clientDto.getBirthDay() != null && clientDto.getBirthMonth() != null && clientDto.getBirthYear() != null)
			client.setBirthDate(Helper.buildDate(clientDto.getBirthYear(), clientDto.getBirthMonth(), clientDto.getBirthDay(), null, null));
		if (clientDto.getGender() != null)
			client.setGender(clientDto.getGender().equals("") ? null : clientDto.getGender());
		
		return client;
	}

	

}
