package com.sportaholic.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.sportaholic.dao.AuthorDao;
import com.sportaholic.model.Article;
import com.sportaholic.model.Author;
import com.sportaholic.service.AuthorService;

@Component
public class AuthorServiceImpl implements AuthorService {

	private AuthorDao authorDao;
	
	@Autowired
	public AuthorServiceImpl(AuthorDao authorDao) {
		this.authorDao = authorDao;
	}
	
	@Override
	@Transactional(rollbackFor = Exception.class)
	public Author get(int id) throws Exception {
		return this.authorDao.get(id);
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public List<Author> getAll() throws Exception {
		return this.authorDao.getAll();
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public Author getEager(int id) throws Exception {
		Author author = this.authorDao.get(id);
		for(Article article : author.getArticles()) 
			article.getId();
		return author;
	}

}
