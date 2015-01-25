package com.sportaholic.dao;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.sportaholic.model.Status;

@Repository
public class StatusDao extends GenericDao<Status, Integer> {

	@Autowired
	public StatusDao(SessionFactory sessionFactory) {
		super(sessionFactory, Status.class);
	}

}
