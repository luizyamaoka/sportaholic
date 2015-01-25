package com.sportaholic.dao;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.sportaholic.model.ProductIsType;

@Repository
public class ProductIsTypeDao extends GenericDao<ProductIsType, Integer> {

	@Autowired
	public ProductIsTypeDao(SessionFactory sessionFactory) {
		super(sessionFactory, ProductIsType.class);
	}

}
