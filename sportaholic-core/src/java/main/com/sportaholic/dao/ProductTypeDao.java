package com.sportaholic.dao;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.sportaholic.model.ProductType;

@Repository
public class ProductTypeDao extends GenericDao<ProductType, Integer> {

	@Autowired
	public ProductTypeDao(SessionFactory sessionFactory) {
		super(sessionFactory, ProductType.class);
	}

	public ProductType getByName(String name, Integer productCategoryId) throws Exception {
		Session session = null;
		session = this.sessionFactory.getCurrentSession();
		Criteria criteria = session.createCriteria(ProductType.class);
		criteria.createAlias("productCategory", "pc");
		
		criteria.add(Restrictions.eq("name", name));
		criteria.add(Restrictions.eq("pc.id", productCategoryId));
		
		ProductType productType = (ProductType) criteria.uniqueResult();
		return productType;
	}
	
}
