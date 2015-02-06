package com.sportaholic.dao;

import java.util.Calendar;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.criterion.Subqueries;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.sportaholic.model.Article;

@Repository
public class ArticleDao extends GenericDao<Article, Integer> {

	@Autowired
	public ArticleDao(SessionFactory sessionFactory) {
		super(sessionFactory, Article.class);
	}

	public List<Article> getPublishedBySet(Integer sportId, Integer articleTypeId) {
		Session session = null;
		session = this.sessionFactory.getCurrentSession();
		Criteria criteria = session.createCriteria(Article.class);
		
		if (sportId != null) {
			criteria.createAlias("articleIsSports", "ais");
			criteria.createAlias("ais.sport", "s");
			criteria.add(Restrictions.eq("s.id", sportId));
		}
		
		if (articleTypeId != null) {
			if (sportId == null) {
				criteria.createAlias("articleIsTypes", "ait");
				criteria.createAlias("ait.articleType", "at");
				criteria.add(Restrictions.eq("at.id", articleTypeId));
			} else {
				DetachedCriteria detachedCriteria = DetachedCriteria.forClass(Article.class);
				detachedCriteria.createAlias("articleIsTypes", "ait");
				detachedCriteria.createAlias("ait.articleType", "at");
				detachedCriteria.add(Restrictions.eq("at.id", articleTypeId));
				detachedCriteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
				detachedCriteria.setProjection(Projections.property("id"));
				
				criteria.add(Subqueries.propertyIn("id", detachedCriteria));
			}
		}
		
		criteria.add(Restrictions.le("publishedAt", Calendar.getInstance().getTime()));
		criteria.addOrder(Order.desc("publishedAt"));
		
		criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
		@SuppressWarnings("unchecked")
		List<Article> articles = criteria.list();
		return articles;
	}
	
}
