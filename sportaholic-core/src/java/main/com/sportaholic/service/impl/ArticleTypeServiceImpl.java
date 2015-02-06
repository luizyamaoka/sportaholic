package com.sportaholic.service.impl;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.sportaholic.dao.ArticleTypeDao;
import com.sportaholic.model.ArticleType;
import com.sportaholic.service.ArticleTypeService;

@Component
public class ArticleTypeServiceImpl implements ArticleTypeService {

	private ArticleTypeDao articleTypeDao;
	
	@Autowired
	public ArticleTypeServiceImpl(ArticleTypeDao articleTypeDao) {
		this.articleTypeDao = articleTypeDao;
	}
	
	@Override
	@Transactional
	public List<ArticleType> getAll() throws Exception {
		return this.articleTypeDao.getAll();
	}

	@Override
	@Transactional
	public ArticleType get(Integer id) throws Exception {
		return this.articleTypeDao.get(id);
	}

	@Override
	@Transactional
	public List<String> update(ArticleType articleType) throws Exception {
		List<String> status = this.testArticleType(articleType);
		
		if (status.get(0).equals("success")) {
			articleType.setUpdatedAt(Calendar.getInstance().getTime());
			this.articleTypeDao.update(articleType);
			status.add(articleType.getId().toString());
		}
		return status;
	}

	@Override
	@Transactional
	public List<String> create(ArticleType articleType) throws Exception {
		List<String> status = this.testArticleType(articleType);
		
		if (status.get(0).equals("success")) {
			articleType.setCreatedAt(Calendar.getInstance().getTime());
			articleType.setUpdatedAt(Calendar.getInstance().getTime());
			Integer articleTypeId = this.articleTypeDao.create(articleType);
			status.add(articleTypeId.toString());
		}
		return status;
	}
	
	private List<String> testArticleType(ArticleType articleType) throws Exception {
		List<String> status = new ArrayList<String>();
		status.add("success");
		
		if (articleType.getName() == null || articleType.getName().length() == 0) {
			status.set(0, "error");
			status.add("name.required");
		} else {
			ArticleType existantArticleType = this.articleTypeDao.getByName(articleType.getName());
			if (existantArticleType != null && articleType.getId() != existantArticleType.getId()) {
				status.set(0, "error");
				status.add("name.existant");
			}
		}
		
		return status;
	}

}
