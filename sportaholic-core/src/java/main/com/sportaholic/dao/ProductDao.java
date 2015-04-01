package com.sportaholic.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.sportaholic.model.Product;

@Repository
public class ProductDao extends GenericDao<Product, Integer> {

	@Autowired
	public ProductDao(SessionFactory sessionFactory) {
		super(sessionFactory, Product.class);
	}
	
	@SuppressWarnings("unchecked")
	public List<Product> getActivePaginated(int pageNumber, int pageSize) throws Exception {
		Session session = null;
		session = this.sessionFactory.getCurrentSession();
		Criteria criteria = session.createCriteria(Product.class);
		criteria.add(Restrictions.eq("isActive", true));
		criteria.setFirstResult((pageNumber - 1) * pageSize);
		criteria.setMaxResults(pageSize);
		criteria.addOrder(Order.asc("name"));
		List<Product> objects = criteria.list();
		return objects;
	}

}
