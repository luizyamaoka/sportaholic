package com.sportaholic.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.sportaholic.model.Sport;

@Repository
public class SportDao extends GenericDao<Sport, Integer> {

	@Autowired
	public SportDao(SessionFactory sessionFactory) {
		super(sessionFactory, Sport.class);
	}

	@Override
	public List<Sport> getAll() throws Exception {
		Session session = null;
		session = this.sessionFactory.getCurrentSession();
		Criteria criteria = session.createCriteria(Sport.class);
		criteria.addOrder(Order.asc("name"));
		@SuppressWarnings("unchecked")
		List<Sport> sports = criteria.list();
		return sports;
	}
}
