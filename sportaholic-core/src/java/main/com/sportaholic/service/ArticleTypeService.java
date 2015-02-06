package com.sportaholic.service;

import java.util.List;

import com.sportaholic.model.ArticleType;

public interface ArticleTypeService {

	public List<ArticleType> getAll() throws Exception;
	
	public ArticleType get(Integer id) throws Exception;
	
	public List<String> update(ArticleType articleType) throws Exception;
	
	public List<String> create(ArticleType articleType) throws Exception;
}
