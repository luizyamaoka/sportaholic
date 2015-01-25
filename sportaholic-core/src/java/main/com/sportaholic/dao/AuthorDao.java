package com.sportaholic.dao;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.sportaholic.model.Author;

@Repository
public class AuthorDao extends GenericDao<Author, Integer> {

	@Autowired
	public AuthorDao(SessionFactory sessionFactory) {
		super(sessionFactory, Author.class);
	}

}
