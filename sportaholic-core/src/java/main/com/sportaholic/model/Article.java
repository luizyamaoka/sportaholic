package com.sportaholic.model;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name=DomainConstants.TB_ARTICLE)
public class Article {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
	private Integer id;
	
	@Column(name="created_at")
	private Date createdAt;
	
	@Column(name="updated_at")
	private Date updatedAt;
	
	@Column(name="title")
	private String title;
	
	@Column(name="subtitle")
	private String subtitle;
	
	@Column(name="content")
	private String content;
	
	@Column(name="published_at")
	private Date publishedAt;
	
	@ManyToOne
	@JoinColumn(name="author_id")
	private Author author;
	
	@OneToMany(mappedBy="article")
	private List<Reading> readers;
	
	@OneToMany(mappedBy="article")
	private List<ArticleIsType> articleIsTypes;
	
	@OneToMany(mappedBy="article")
	private List<ArticleIsSport> articleIsSports;

	@OneToMany(mappedBy="article")
	private List<ArticleComment> articleComments;
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Date getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

	public Date getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(Date updatedAt) {
		this.updatedAt = updatedAt;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Author getAuthor() {
		return author;
	}

	public void setAuthor(Author author) {
		this.author = author;
	}

	public List<Reading> getReaders() {
		return readers;
	}

	public void setReaders(List<Reading> readers) {
		this.readers = readers;
	}

	public List<ArticleIsType> getArticleIsTypes() {
		return articleIsTypes;
	}

	public void setArticleIsTypes(List<ArticleIsType> articleIsTypes) {
		this.articleIsTypes = articleIsTypes;
	}

	public List<ArticleIsSport> getArticleIsSports() {
		return articleIsSports;
	}

	public void setArticleIsSports(List<ArticleIsSport> articleIsSports) {
		this.articleIsSports = articleIsSports;
	}

	public List<ArticleComment> getArticleComments() {
		return articleComments;
	}

	public void setArticleComments(List<ArticleComment> articleComments) {
		this.articleComments = articleComments;
	}

	public String getSubtitle() {
		return subtitle;
	}

	public void setSubtitle(String subtitle) {
		this.subtitle = subtitle;
	}

	@Transient
	public String getUri() {
		return UrlConstants.URL_ARTICLE + "/" + this.id;
	}

	public Date getPublishedAt() {
		return publishedAt;
	}

	public void setPublishedAt(Date publishedAt) {
		this.publishedAt = publishedAt;
	}
	
}
