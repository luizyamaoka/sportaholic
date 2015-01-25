package com.sportaholic.dao;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.sportaholic.model.Interest;

@Repository
public class InterestDao extends GenericDao<Interest, Integer> {

	@Autowired
	public InterestDao(SessionFactory sessionFactory) {
		super(sessionFactory, Interest.class);
	}

}
