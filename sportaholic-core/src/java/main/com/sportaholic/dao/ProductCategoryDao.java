package com.sportaholic.dao;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.sportaholic.model.ProductCategory;

@Repository
public class ProductCategoryDao extends GenericDao<ProductCategory, Integer> {

	@Autowired
	public ProductCategoryDao(SessionFactory sessionFactory) {
		super(sessionFactory, ProductCategory.class);
	}

	public ProductCategory getByName(String name) throws Exception {
		Session session = null;
		session = this.sessionFactory.getCurrentSession();
		Criteria criteria = session.createCriteria(ProductCategory.class);
		criteria.add(Restrictions.eq("name", name));
		ProductCategory productCategory = (ProductCategory) criteria.uniqueResult();
		return productCategory;
	}
	
}
