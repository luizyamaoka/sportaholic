package com.sportaholic.transformer;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.sportaholic.dao.ArticleTypeDao;
import com.sportaholic.dao.AuthorDao;
import com.sportaholic.dao.SportDao;
import com.sportaholic.dao.UriDao;
import com.sportaholic.dto.ArticleDto;
import com.sportaholic.model.Article;
import com.sportaholic.model.ArticleIsSport;
import com.sportaholic.model.ArticleIsType;
import com.sportaholic.model.Uri;
import com.sportaholic.model.UrlConstants;

@Component
public class ArticleDtoTransformer {

	private UriDao uriDao;
	private AuthorDao authorDao;
	private ArticleTypeDao articleTypeDao;
	private SportDao sportDao;
	
	@Autowired
	public ArticleDtoTransformer(UriDao uriDao, AuthorDao authorDao,
			ArticleTypeDao articleTypeDao, SportDao sportDao) {
		this.uriDao = uriDao;
		this.authorDao = authorDao;
		this.articleTypeDao = articleTypeDao;
		this.sportDao = sportDao;
	}
	
	@Transactional
	public Article articleDtoToArticle(ArticleDto articleDto) throws Exception {
		Article article = new Article();
		article.setId(articleDto.getId());
		article.setTitle(articleDto.getTitle());
		article.setSubtitle(articleDto.getSubtitle());
		article.setContent(articleDto.getContent());
		article.setAuthor(this.authorDao.get(articleDto.getAuthorId()));
		
		List<ArticleIsSport> articleIsSports = new ArrayList<ArticleIsSport>();
		for (Integer sportId : articleDto.getSportIds()) {
			ArticleIsSport articleIsSport = new ArticleIsSport();
			articleIsSport.setArticle(article);
			articleIsSport.setSport(this.sportDao.get(sportId));
			articleIsSports.add(articleIsSport); 
		}
		article.setArticleIsSports(articleIsSports);
		
		List<ArticleIsType> articleIsTypes = new ArrayList<ArticleIsType>();
		for (Integer articleTypeId : articleDto.getArticleTypeIds()) {
			ArticleIsType articleIsType = new ArticleIsType();
			articleIsType.setArticle(article);
			articleIsType.setArticleType(this.articleTypeDao.get(articleTypeId));
			articleIsTypes.add(articleIsType);
		}
		article.setArticleIsTypes(articleIsTypes);
		
		return article;
	}
	
	@Transactional
	public ArticleDto articleToArticleDto(Article article) throws Exception {
		ArticleDto articleDto = new ArticleDto();
		
		articleDto.setId(article.getId());
		articleDto.setTitle(article.getTitle());
		articleDto.setSubtitle(article.getSubtitle());
		articleDto.setContent(article.getContent());
		articleDto.setAuthorId(article.getAuthor().getId());
		
		List<Integer> sportIds = new ArrayList<Integer>();
		for(ArticleIsSport articleIsSport : article.getArticleIsSports())
			sportIds.add(articleIsSport.getSport().getId());
		articleDto.setSportIds(sportIds);
		
		List<Integer> articleTypeIds = new ArrayList<Integer>();
		for(ArticleIsType articleIsType : article.getArticleIsTypes())
			articleTypeIds.add(articleIsType.getArticleType().getId());
		articleDto.setArticleTypeIds(articleTypeIds);
		
		Uri uri = this.uriDao.getByUri(UrlConstants.URL_ARTICLE + "/" + article.getId());
		
		if (uri != null) {
			articleDto.setUriId(uri.getId());
			articleDto.setFriendlyUri(uri.getFriendlyUri());
			articleDto.setName(uri.getName());
			articleDto.setParentId(uri.getParent().getId());
			articleDto.setMetaDescription(uri.getMetaDescription());
		}
		
		return articleDto;
	}
	
}