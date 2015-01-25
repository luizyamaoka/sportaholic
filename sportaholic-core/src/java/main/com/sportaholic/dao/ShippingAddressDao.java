package com.sportaholic.dao;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.sportaholic.model.ShippingAddress;

@Repository
public class ShippingAddressDao extends GenericDao<ShippingAddress, Integer> {

	@Autowired
	public ShippingAddressDao(SessionFactory sessionFactory) {
		super(sessionFactory, ShippingAddress.class);
	}

}
