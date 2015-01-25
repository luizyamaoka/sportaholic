package com.sportaholic.service.impl;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

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
	@Transactional
	public Author get(int id) throws Exception {
		return this.authorDao.get(id);
	}

	@Override
	@Transactional
	public List<Author> getAll() throws Exception {
		return this.authorDao.getAll();
	}

	@Override
	@Transactional
	public Author getEager(int id) throws Exception {
		Author author = this.authorDao.get(id);
		for(Article article : author.getArticles()) 
			article.getId();
		return author;
	}

}
