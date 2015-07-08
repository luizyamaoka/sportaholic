package com.sportaholic.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.criterion.Subqueries;
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
	@Override
	public List<Product> getAll() throws Exception {
		Session session = null;
		session = this.sessionFactory.getCurrentSession();
		Criteria criteria = session.createCriteria(Product.class);
		criteria.createAlias("brand", "b");
		criteria.addOrder(Order.asc("b.name"));
		criteria.addOrder(Order.asc("name"));
		List<Product> objects = criteria.list();
		return objects;
	}
	
	@SuppressWarnings("unchecked")
	public List<Product> getActiveBySetPaginated(Integer sportId, Integer productTypeId, int pageNumber, int pageSize) throws Exception {
		Session session = null;
		session = this.sessionFactory.getCurrentSession();
		Criteria criteria = session.createCriteria(Product.class);

		if(sportId != null) {
			criteria.createAlias("productIsSports", "pis");
			criteria.createAlias("pis.sport", "s");
			criteria.add(Restrictions.eq("s.id", sportId));
		}
		if (productTypeId != null) {
			if (sportId == null) {
				criteria.createAlias("productIsTypes", "pit");
				criteria.createAlias("pit.productType", "pt");
				criteria.add(Restrictions.eq("pt.id", productTypeId));
			} else {
				DetachedCriteria detachedCriteria = DetachedCriteria.forClass(Product.class);
				detachedCriteria.createAlias("productIsTypes", "pit");
				detachedCriteria.createAlias("pit.productType", "pt");
				detachedCriteria.add(Restrictions.eq("pt.id", productTypeId));
				detachedCriteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
				detachedCriteria.setProjection(Projections.property("id"));
				
				criteria.add(Subqueries.propertyIn("id", detachedCriteria));
			}
		}
		
		criteria.add(Restrictions.eq("isActive", true));
		criteria.setFirstResult((pageNumber - 1) * pageSize);
		criteria.setMaxResults(pageSize);
		criteria.addOrder(Order.asc("name"));
		List<Product> objects = criteria.list();
		return objects;
	}

}
