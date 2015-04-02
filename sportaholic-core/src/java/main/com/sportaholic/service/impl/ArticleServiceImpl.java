package com.sportaholic.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

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
	@Transactional(rollbackFor = Exception.class)
	public Article get(int id) throws Exception {
		return this.articleDao.get(id);
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public Article getEager(int id) throws Exception {
		Article article = this.articleDao.get(id);
		for (ArticleComment articleComment : article.getArticleComments())
			articleComment.getId();
		return article;
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public List<Article> getPublishedBySetPaginated(Integer sportId, Integer articleTypeId, int pageNumber, int pageSize)
			throws Exception {
		return this.articleDao.getPublishedBySetPaginated(sportId, articleTypeId, pageNumber, pageSize);
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public List<Article> getAll() throws Exception {
		return this.articleDao.getAll();
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public List<Article> getPublishedPaginated(int pageNumber, int pageSize)
			throws Exception {
		return this.articleDao.getPublishedPaginated(pageNumber, pageSize);
	}

}
