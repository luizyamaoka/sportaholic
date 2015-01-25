package com.sportaholic.dao;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.sportaholic.model.Purchase;

@Repository
public class PurchaseDao extends GenericDao<Purchase, Integer> {

	@Autowired
	public PurchaseDao(SessionFactory sessionFactory) {
		super(sessionFactory, Purchase.class);
	}

}
