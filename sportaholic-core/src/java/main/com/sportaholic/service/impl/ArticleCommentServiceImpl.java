package com.sportaholic.service.impl;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.sportaholic.dao.ArticleCommentDao;
import com.sportaholic.model.ArticleComment;
import com.sportaholic.service.ArticleCommentService;

@Component
public class ArticleCommentServiceImpl implements ArticleCommentService {

	private ArticleCommentDao articleCommentDao;
	
	@Autowired
	public ArticleCommentServiceImpl(ArticleCommentDao articleCommentDao) {
		this.articleCommentDao = articleCommentDao;
	}
	
	@Override
	@Transactional
	public ArticleComment get(int id) throws Exception {
		return this.articleCommentDao.get(id);
	}

	@Override
	@Transactional
	public List<String> create(ArticleComment articleComment) throws Exception {
		List<String> status = this.testArticleComment(articleComment);
		
		if (status.get(0).equals("success")) {
			articleComment.setCreatedAt(Calendar.getInstance().getTime());
			articleComment.setUpdatedAt(Calendar.getInstance().getTime());
			Integer id = this.articleCommentDao.create(articleComment);
			status.add(id.toString());
		}
		return status;
	}
	
	private List<String> testArticleComment(ArticleComment articleComment) throws Exception {
		List<String> status = new ArrayList<String>();
		status.add("success");
		return status;
	}

}
