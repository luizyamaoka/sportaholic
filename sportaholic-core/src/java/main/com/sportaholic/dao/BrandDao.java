package com.sportaholic.dao;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.sportaholic.model.Brand;

@Repository
public class BrandDao extends GenericDao<Brand, Integer> {

	@Autowired
	public BrandDao(SessionFactory sessionFactory) {
		super(sessionFactory, Brand.class);
	}

}
