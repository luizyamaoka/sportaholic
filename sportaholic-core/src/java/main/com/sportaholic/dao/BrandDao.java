package com.sportaholic.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.sportaholic.model.Brand;
import com.sportaholic.model.Sport;

@Repository
public class BrandDao extends GenericDao<Brand, Integer> {

	@Autowired
	public BrandDao(SessionFactory sessionFactory) {
		super(sessionFactory, Brand.class);
	}

	@SuppressWarnings("unchecked")
	public List<Sport> getPossibleSports(int brandId) throws Exception {
		Session session = null;
		session = this.sessionFactory.getCurrentSession();
		Criteria criteria = session.createCriteria(Sport.class);
		criteria.createAlias("productIsSports", "pis");
		criteria.createAlias("pis.product", "p");
		criteria.createAlias("p.brand", "b");
		criteria.add(Restrictions.eq("b.id", brandId));
		criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
		List<Sport> sports = criteria.list();
		return sports;
	}
	
}
