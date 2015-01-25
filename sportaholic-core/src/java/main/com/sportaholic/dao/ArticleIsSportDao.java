package com.sportaholic.dao;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.sportaholic.model.ArticleIsSport;

@Repository
public class ArticleIsSportDao extends GenericDao<ArticleIsSport, Integer> {

	@Autowired
	public ArticleIsSportDao(SessionFactory sessionFactory) {
		super(sessionFactory, ArticleIsSport.class);
	}

}
