package com.sportaholic.dao;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.sportaholic.model.ProductView;

@Repository
public class ProductViewDao extends GenericDao<ProductView, Integer> {

	@Autowired
	public ProductViewDao(SessionFactory sessionFactory) {
		super(sessionFactory, ProductView.class);
	}

}
