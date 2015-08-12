package com.sportaholic.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.sportaholic.model.Uri;

@Repository
public class UriDao extends GenericDao<Uri, Integer> {

	@Autowired
	public UriDao(SessionFactory sessionFactory) {
		super(sessionFactory, Uri.class);
	}
	
	public Uri getByUri(String uriString) throws Exception {
		Session session = null;
		session = this.sessionFactory.getCurrentSession();
		Criteria criteria = session.createCriteria(Uri.class);
		criteria.add(Restrictions.eq("uri", uriString));
		Uri uri = (Uri) criteria.uniqueResult();
		return uri;
	}
	
	public Uri getByFriendlyUri(String friendlyUri) throws Exception {
		Session session = null;
		session = this.sessionFactory.getCurrentSession();
		Criteria criteria = session.createCriteria(Uri.class);
		criteria.add(Restrictions.eq("friendlyUri", friendlyUri));
		Uri uri = (Uri) criteria.uniqueResult();
		return uri;
	}
	
	@SuppressWarnings("unchecked")
	public List<Uri> getAll() throws Exception {
		Session session = null;
		session = this.sessionFactory.getCurrentSession();
		Criteria criteria = session.createCriteria(Uri.class);
		criteria.addOrder(Order.asc("friendlyUri"));
		List<Uri> objects = criteria.list();
		return objects;
	}
	
}
