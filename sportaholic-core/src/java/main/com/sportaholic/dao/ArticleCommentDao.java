package com.sportaholic.dao;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.sportaholic.model.ArticleComment;

@Repository
public class ArticleCommentDao extends GenericDao<ArticleComment, Integer> {

	@Autowired
	public ArticleCommentDao(SessionFactory sessionFactory) {
		super(sessionFactory, ArticleComment.class);
	}

}
