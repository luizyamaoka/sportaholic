package com.sportaholic.service;

import java.util.List;

import com.sportaholic.dto.ArticleDto;
import com.sportaholic.model.Article;

public interface AdmArticleService {

	public List<String> create(ArticleDto articleDto) throws Exception;
	
	public List<String> update(ArticleDto articleDto) throws Exception;
	
	public Article getEager(int id) throws Exception;
}
