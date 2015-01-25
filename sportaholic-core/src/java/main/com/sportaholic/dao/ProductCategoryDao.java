package com.sportaholic.dao;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.sportaholic.model.ProductCategory;

@Repository
public class ProductCategoryDao extends GenericDao<ProductCategory, Integer> {

	@Autowired
	public ProductCategoryDao(SessionFactory sessionFactory) {
		super(sessionFactory, ProductCategory.class);
	}

}
