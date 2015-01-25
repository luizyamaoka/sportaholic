package com.sportaholic.dao;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.sportaholic.model.ProductComment;

@Repository
public class ProductCommentDao extends GenericDao<ProductComment, Integer> {

	@Autowired
	public ProductCommentDao(SessionFactory sessionFactory) {
		super(sessionFactory, ProductComment.class);
	}

}
