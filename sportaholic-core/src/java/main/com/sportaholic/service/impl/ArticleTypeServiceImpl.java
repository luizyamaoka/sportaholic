package com.sportaholic.service.impl;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.sportaholic.dao.ArticleTypeDao;
import com.sportaholic.model.ArticleType;
import com.sportaholic.service.ArticleTypeService;

@Component
public class ArticleTypeServiceImpl implements ArticleTypeService {

	private ArticleTypeDao articleTypeDao;
	
	@Autowired
	public ArticleTypeServiceImpl(ArticleTypeDao articleTypeDao) {
		this.articleTypeDao = articleTypeDao;
	}
	
	@Override
	@Transactional
	public List<ArticleType> getAll() throws Exception {
		return this.articleTypeDao.getAll();
	}

}
