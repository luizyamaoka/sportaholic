package com.sportaholic.dao;

import java.util.List;

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

	public ProductCategory getByName(String name, Integer sportId) throws Exception {
		Session session = null;
		session = this.sessionFactory.getCurrentSession();
		Criteria criteria = session.createCriteria(ProductCategory.class);
		criteria.add(Restrictions.eq("name", name));
		criteria.createAlias("sport", "s");
		criteria.add(Restrictions.eq("s.id", sportId));
		ProductCategory productCategory = (ProductCategory) criteria.uniqueResult();
		return productCategory;
	}
	
	@SuppressWarnings("unchecked")
	public List<ProductCategory> getBySet(Integer sportId) throws Exception {
		Session session = null;
		session = this.sessionFactory.getCurrentSession();
		Criteria criteria = session.createCriteria(ProductCategory.class);
		criteria.createAlias("sport", "s");
		if (sportId != null) criteria.add(Restrictions.eq("s.id", sportId));
		List<ProductCategory> productCategories = criteria.list();
		return productCategories;
	}
	
}
