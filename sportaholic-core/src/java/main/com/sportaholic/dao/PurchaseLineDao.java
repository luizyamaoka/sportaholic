package com.sportaholic.dao;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.sportaholic.model.PurchaseLine;

@Repository
public class PurchaseLineDao extends GenericDao<PurchaseLine, Integer> {

	@Autowired
	public PurchaseLineDao(SessionFactory sessionFactory) {
		super(sessionFactory, PurchaseLine.class);
	}

}
