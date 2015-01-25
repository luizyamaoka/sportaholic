package com.sportaholic.service.impl;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.sportaholic.dao.ArticleDao;
import com.sportaholic.model.Article;
import com.sportaholic.model.ArticleComment;
import com.sportaholic.service.ArticleService;

@Component
public class ArticleServiceImpl implements ArticleService {

	private ArticleDao articleDao;
	
	@Autowired
	public ArticleServiceImpl(ArticleDao articleDao) {
		this.articleDao = articleDao;
	}
	
	@Override
	@Transactional
	public Article get(int id) throws Exception {
		return this.articleDao.get(id);
	}

	@Override
	@Transactional
	public Article getEager(int id) throws Exception {
		Article article = this.articleDao.get(id);
		for (ArticleComment articleComment : article.getArticleComments())
			articleComment.getId();
		return article;
	}

	@Override
	@Transactional
	public List<Article> getBySet(Integer sportId, Integer articleTypeId)
			throws Exception {
		return this.articleDao.getBySet(sportId, articleTypeId);
	}

}
