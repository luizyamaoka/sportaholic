package com.sportaholic.dao;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.sportaholic.model.Client;

@Repository
public class ClientDao extends GenericDao<Client, Integer> {

	@Autowired
	public ClientDao(SessionFactory sessionFactory) {
		super(sessionFactory, Client.class);
	}
	
	public Client getByEmail(String email) throws Exception {
		Session session = super.sessionFactory.getCurrentSession();
		Criteria cr = session.createCriteria(Client.class);
		cr.add(Restrictions.eq("email", email));
		Client user = (Client) cr.uniqueResult();
		return user;
	}

}
