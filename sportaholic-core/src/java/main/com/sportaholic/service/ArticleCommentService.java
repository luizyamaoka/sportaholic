package com.sportaholic.service;

import java.util.List;

import com.sportaholic.model.ArticleComment;

public interface ArticleCommentService {

	public ArticleComment get(int id) throws Exception;
	
	public List<String> create(ArticleComment articleComment) throws Exception;
	
}
