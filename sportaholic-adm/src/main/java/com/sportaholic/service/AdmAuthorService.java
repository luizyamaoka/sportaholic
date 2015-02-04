package com.sportaholic.service;

import java.util.List;

import com.sportaholic.dto.AuthorDto;

public interface AdmAuthorService {

	public List<String> create(AuthorDto authorDto) throws Exception;
	
	public List<String> update(AuthorDto authorDto) throws Exception;
	
}
