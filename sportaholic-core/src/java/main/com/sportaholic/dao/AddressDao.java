package com.sportaholic.dao;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.sportaholic.model.Address;

@Repository
public class AddressDao extends GenericDao<Address, Integer> {

	@Autowired
	public AddressDao(SessionFactory sessionFactory) {
		super(sessionFactory, Address.class);
	}

}
