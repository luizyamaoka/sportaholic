package com.sportaholic.dao;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.sportaholic.model.ProductIsSport;

@Repository
public class ProductIsSportDao extends GenericDao<ProductIsSport, Integer> {

	@Autowired
	public ProductIsSportDao(SessionFactory sessionFactory) {
		super(sessionFactory, ProductIsSport.class);
	}

}
