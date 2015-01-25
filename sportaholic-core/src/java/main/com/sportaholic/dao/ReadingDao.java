package com.sportaholic.dao;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.sportaholic.model.Reading;

@Repository
public class ReadingDao extends GenericDao<Reading, Integer> {

	@Autowired
	public ReadingDao(SessionFactory sessionFactory) {
		super(sessionFactory, Reading.class);
	}

}
