package com.sportaholic.service.impl;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.sportaholic.dao.ArticleDao;
import com.sportaholic.dao.ArticleIsSportDao;
import com.sportaholic.dao.ArticleIsTypeDao;
import com.sportaholic.dao.UriDao;
import com.sportaholic.dto.ArticleDto;
import com.sportaholic.model.Article;
import com.sportaholic.model.ArticleIsSport;
import com.sportaholic.model.ArticleIsType;
import com.sportaholic.model.Uri;
import com.sportaholic.model.UrlConstants;
import com.sportaholic.service.AdmArticleService;
import com.sportaholic.transformer.ArticleDtoTransformer;

@Component
public class AdmArticleServiceImpl implements AdmArticleService {

	private ArticleDao articleDao;
	private ArticleDtoTransformer articleDtoTransformer;
	private UriDao uriDao;
	private ArticleIsSportDao articleIsSportDao;
	private ArticleIsTypeDao articleIsTypeDao;
	
	@Autowired
	public AdmArticleServiceImpl(ArticleDao articleDao, ArticleDtoTransformer articleDtoTransformer,
			UriDao uriDao, ArticleIsSportDao articleIsSportDao, ArticleIsTypeDao articleIsTypeDao) {
		this.articleDao = articleDao;
		this.articleDtoTransformer = articleDtoTransformer;
		this.uriDao = uriDao;
		this.articleIsSportDao = articleIsSportDao;
		this.articleIsTypeDao = articleIsTypeDao;
	}
	
	@Override
	@Transactional
	public List<String> create(ArticleDto articleDto) throws Exception {
		List<String> status = this.testArticleDto(articleDto);
		
		if (status.get(0).equals("success")) {
			Article article = this.articleDtoTransformer.articleDtoToArticle(articleDto);
			article.setCreatedAt(Calendar.getInstance().getTime());
			article.setUpdatedAt(Calendar.getInstance().getTime());
			Integer articleId = this.articleDao.create(article);
			
			for (ArticleIsSport articleIsSport : article.getArticleIsSports()) {
				articleIsSport.setCreatedAt(Calendar.getInstance().getTime());
				this.articleIsSportDao.create(articleIsSport);
			}
			
			for (ArticleIsType articleIsType : article.getArticleIsTypes()) {
				articleIsType.setCreatedAt(Calendar.getInstance().getTime());
				this.articleIsTypeDao.create(articleIsType);
			}
			
			Uri uri = this.articleDtoTransformer.articleDtoToUri(articleDto);
			uri.setUri(UrlConstants.URL_ARTICLE + "/" + articleId.toString());
			uri.setCreatedAt(Calendar.getInstance().getTime());
			uri.setUpdatedAt(Calendar.getInstance().getTime());
			this.uriDao.create(uri);
			
			status.add(articleId.toString());
		}
		
		return status;
	}

	@Override
	@Transactional
	public List<String> update(ArticleDto articleDto) throws Exception {
		List<String> status = this.testArticleDto(articleDto);
		
		if (articleDto.getId() == null) {
			status.set(0, "error");
			status.add("articleId.required");
		}
		
		if (status.get(0).equals("success")) {
			
			// Delete all sports and articles types from article
			Article oldArticle  = this.articleDao.get(articleDto.getId());
			for (ArticleIsSport articleIsSport : oldArticle.getArticleIsSports()) {
				this.articleIsSportDao.delete(articleIsSport);
			}
			
			for (ArticleIsType articleIsType : oldArticle.getArticleIsTypes()) {
				this.articleIsTypeDao.delete(articleIsType);
			}
			
			// Save article info
			Article article = this.articleDtoTransformer.articleDtoToArticle(articleDto);
			article.setUpdatedAt(Calendar.getInstance().getTime());
			this.articleDao.update(article);
			status.add(articleDto.getId().toString());
			
			// Save article sports and article types
			for (ArticleIsSport articleIsSport : article.getArticleIsSports()) {
				articleIsSport.setCreatedAt(Calendar.getInstance().getTime());
				this.articleIsSportDao.create(articleIsSport);
			}
			
			for (ArticleIsType articleIsType : article.getArticleIsTypes()) {
				articleIsType.setCreatedAt(Calendar.getInstance().getTime());
				this.articleIsTypeDao.create(articleIsType);
			}
			
			// Save uri
			Uri uri = this.articleDtoTransformer.articleDtoToUri(articleDto);
			uri.setUpdatedAt(Calendar.getInstance().getTime());
			this.uriDao.update(uri);
		}
		
		return status;
	}
	
	private List<String> testArticleDto(ArticleDto articleDto) throws Exception {
		List<String> status = new ArrayList<String>();
		status.add("success");
		
		if (articleDto.getTitle() == null || articleDto.getTitle().length() == 0) {
			status.set(0, "error");
			status.add("title.required");
		}
		if (articleDto.getSubtitle() == null || articleDto.getSubtitle().length() == 0) {
			status.set(0, "error");
			status.add("subtitle.required");
		}
		if (articleDto.getContent() == null || articleDto.getContent().length() == 0) {
			status.set(0, "error");
			status.add("content.required");
		}
		if (articleDto.getAuthorId() == null || articleDto.getAuthorId() == 0) {
			status.set(0, "error");
			status.add("author.required");
		}
		if (articleDto.getSportIds() == null || articleDto.getSportIds().size() == 0) {
			status.set(0, "error");
			status.add("sports.required");
		}
		if (articleDto.getArticleTypeIds() == null || articleDto.getArticleTypeIds().size() == 0) {
			status.set(0, "error");
			status.add("articleTypes.required");
		}
		if (articleDto.getFriendlyUri() == null || articleDto.getFriendlyUri().length() == 0) {
			status.set(0, "error");
			status.add("friendlyUri.required");
		}
		if (articleDto.getFriendlyUri() != null && !articleDto.getFriendlyUri().startsWith("/")) {
			status.set(0, "error");
			status.add("friendlyUri.startsWithSlash");
		}
		if (articleDto.getFriendlyUri() != null && articleDto.getFriendlyUri().contains(" ")) {
			status.set(0, "error");
			status.add("friendlyUri.containsSpaces");
		}
		if (articleDto.getFriendlyUri() != null) {
			Uri uri = this.uriDao.getByFriendlyUri(articleDto.getFriendlyUri());
			if (uri != null && uri.getId() != articleDto.getUriId()) {
				status.set(0, "error");
				status.add("friendlyUri.existant");
			}
		}
		if (articleDto.getName() == null || articleDto.getName().length() == 0) {
			status.set(0, "error");
			status.add("name.required");
		}
		if (articleDto.getParentId() == null || articleDto.getParentId() == 0) {
			status.set(0, "error");
			status.add("parentId.required");
		}
		if (articleDto.getMetaDescription() == null || articleDto.getMetaDescription().length() == 0) {
			status.set(0, "error");
			status.add("metaDescription.required");
		}
		
		return status;
	}

	@Override
	@Transactional
	public Article getEager(int id) throws Exception {
		Article article = this.articleDao.get(id);
		
		article.getArticleIsSports().size();
		article.getArticleIsTypes().size();
		
		return article;
	}

}
