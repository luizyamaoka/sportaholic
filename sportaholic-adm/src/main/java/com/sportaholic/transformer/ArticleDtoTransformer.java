package com.sportaholic.transformer;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.TimeZone;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.sportaholic.dao.ArticleDao;
import com.sportaholic.dao.ArticleTypeDao;
import com.sportaholic.dao.AuthorDao;
import com.sportaholic.dao.SportDao;
import com.sportaholic.dao.UriDao;
import com.sportaholic.dto.ArticleDto;
import com.sportaholic.helper.Helper;
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
	private ArticleDao articleDao;
	
	@Autowired
	public ArticleDtoTransformer(UriDao uriDao, AuthorDao authorDao,
			ArticleTypeDao articleTypeDao, SportDao sportDao, ArticleDao articleDao) {
		this.uriDao = uriDao;
		this.authorDao = authorDao;
		this.articleTypeDao = articleTypeDao;
		this.sportDao = sportDao;
		this.articleDao = articleDao;
	}
	
	@Transactional
	public Article articleDtoToArticle(ArticleDto articleDto) throws Exception {
		Article article = articleDto.getId() == null ? new Article() : this.articleDao.get(articleDto.getId());
		
		article.setId(articleDto.getId());
		article.setTitle(articleDto.getTitle());
		article.setSubtitle(articleDto.getSubtitle());
		article.setContent(articleDto.getContent());
		article.setAuthor(this.authorDao.get(articleDto.getAuthorId()));
		article.setPublishedAt(Helper.buildDate(articleDto.getPublishedAtYear(), 
				articleDto.getPublishedAtMonth(), articleDto.getPublishedAtDay(), 
				articleDto.getPublishedAtHour(), articleDto.getPublishedAtMinute()));
		
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
	public Uri articleDtoToUri(ArticleDto articleDto) throws Exception {
		Uri uri = articleDto.getUriId() == null ? new Uri() : this.uriDao.get(articleDto.getUriId());
		
		uri.setFriendlyUri(articleDto.getFriendlyUri());
		uri.setName(articleDto.getName());
		uri.setMetaDescription(articleDto.getMetaDescription());
		uri.setParent(this.uriDao.get(articleDto.getParentId()));
		
		return uri;
	}
	
	@Transactional
	public ArticleDto articleToArticleDto(Article article) throws Exception {
		ArticleDto articleDto = new ArticleDto();
		
		articleDto.setId(article.getId());
		articleDto.setTitle(article.getTitle());
		articleDto.setSubtitle(article.getSubtitle());
		articleDto.setContent(article.getContent());
		articleDto.setAuthorId(article.getAuthor().getId());
		
		if (article.getPublishedAt() != null) {
			Calendar cal = Calendar.getInstance();
		    cal.setTime(article.getPublishedAt());
		    cal.setTimeZone(TimeZone.getTimeZone("Brazil/East"));
		    articleDto.setPublishedAtDay(cal.get(Calendar.DAY_OF_MONTH));
		    articleDto.setPublishedAtMonth(cal.get(Calendar.MONTH) + 1);
		    articleDto.setPublishedAtYear(cal.get(Calendar.YEAR));
		    articleDto.setPublishedAtHour(cal.get(Calendar.HOUR_OF_DAY));
		    articleDto.setPublishedAtMinute(cal.get(Calendar.MINUTE));
		}
		
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