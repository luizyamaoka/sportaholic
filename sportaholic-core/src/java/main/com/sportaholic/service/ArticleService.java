package com.sportaholic.service;

import java.util.List;

import com.sportaholic.model.Article;

public interface ArticleService {

	public Article get(int id) throws Exception;
	
	public Article getEager(int id) throws Exception;
	
	public List<Article> getAll() throws Exception;
	
	public List<Article> getPublishedPaginated(int pageNumber, int pageSize) throws Exception;
	
	public List<Article> getPublishedBySetPaginated(Integer sportId, Integer articleTypeId, int pageNumber, int pageSize) throws Exception;
	
}
