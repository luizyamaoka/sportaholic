package com.sportaholic.dao;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.sportaholic.model.Product;

@Repository
public class ProductDao extends GenericDao<Product, Integer> {

	@Autowired
	public ProductDao(SessionFactory sessionFactory) {
		super(sessionFactory, Product.class);
	}

}
