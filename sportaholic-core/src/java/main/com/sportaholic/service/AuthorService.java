package com.sportaholic.service;

import java.util.List;

import com.sportaholic.model.Author;

public interface AuthorService {

	public Author get(int id) throws Exception;
	
	public Author getEager(int id) throws Exception;
	
	public List<Author> getAll() throws Exception;
	
}
