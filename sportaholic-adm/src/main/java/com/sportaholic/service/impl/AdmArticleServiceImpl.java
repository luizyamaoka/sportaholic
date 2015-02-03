package com.sportaholic.service.impl;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.sportaholic.dao.ArticleDao;
import com.sportaholic.dto.ArticleDto;
import com.sportaholic.model.Article;
import com.sportaholic.model.Uri;
import com.sportaholic.service.AdmArticleService;
import com.sportaholic.service.UriService;
import com.sportaholic.transformer.ArticleDtoTransformer;

@Component
public class AdmArticleServiceImpl implements AdmArticleService {

	private ArticleDao articleDao;
	private ArticleDtoTransformer articleDtoTransformer;
	private UriService uriService;
	
	@Autowired
	public AdmArticleServiceImpl(ArticleDao articleDao, ArticleDtoTransformer articleDtoTransformer,
			UriService uriService) {
		this.articleDao = articleDao;
		this.articleDtoTransformer = articleDtoTransformer;
		this.uriService = uriService;
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
			Article article = this.articleDtoTransformer.articleDtoToArticle(articleDto);
			article.setUpdatedAt(Calendar.getInstance().getTime());
			this.articleDao.update(article);
			status.add(articleDto.getId().toString());
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
			Uri uri = this.uriService.getByFriendlyUri(articleDto.getFriendlyUri());
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
