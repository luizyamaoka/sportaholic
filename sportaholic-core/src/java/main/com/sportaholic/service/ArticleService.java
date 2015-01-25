package com.sportaholic.service;

import java.util.List;

import com.sportaholic.model.Article;

public interface ArticleService {

	public Article get(int id) throws Exception;
	
	public Article getEager(int id) throws Exception;
	
	public List<Article> getBySet(Integer sportId, Integer articleTypeId) throws Exception;
	
}
