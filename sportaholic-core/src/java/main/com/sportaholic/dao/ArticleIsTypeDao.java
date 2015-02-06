package com.sportaholic.dao;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.sportaholic.model.ArticleIsType;

@Repository
public class ArticleIsTypeDao extends GenericDao<ArticleIsType, Integer> {

	@Autowired
	public ArticleIsTypeDao(SessionFactory sessionFactory) {
		super(sessionFactory, ArticleIsType.class);
	}
	
}
