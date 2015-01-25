package com.sportaholic.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.sportaholic.model.ArticleType;

@Repository
public class ArticleTypeDao extends GenericDao<ArticleType, Integer> {

	@Autowired
	public ArticleTypeDao(SessionFactory sessionFactory) {
		super(sessionFactory, ArticleType.class);
	}

	@Override
	public List<ArticleType> getAll() throws Exception {
		Session session = null;
		session = this.sessionFactory.getCurrentSession();
		Criteria criteria = session.createCriteria(ArticleType.class);
		criteria.addOrder(Order.asc("name"));
		@SuppressWarnings("unchecked")
		List<ArticleType> articleTypes = criteria.list();
		return articleTypes;
	}
	
}
