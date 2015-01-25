package com.sportaholic.dao;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.sportaholic.model.ProductType;

@Repository
public class ProductTypeDao extends GenericDao<ProductType, Integer> {

	@Autowired
	public ProductTypeDao(SessionFactory sessionFactory) {
		super(sessionFactory, ProductType.class);
	}

}
