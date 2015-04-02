package com.sportaholic.model;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name=DomainConstants.TB_SPORT)
public class Sport {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
	private Integer id;
	
	@Column(name="created_at")
	private Date createdAt;
	
	@Column(name="updated_at")
	private Date updatedAt;
	
	@Column(name="name")
	private String name;
	
	@Column(name="banner")
	private String banner;

	@Column(name="description")
	private String description;
	
	@OneToMany(mappedBy="sport")
	private List<Interest> interestedClients;
	
	@OneToMany(mappedBy="sport")
	private List<ArticleIsSport> articleIsSports;
	
	@OneToMany(mappedBy="sport")
	private List<ProductCategory> productCategories;
	
	@OneToMany(mappedBy="sport")
	private List<ProductIsSport> productIsSports;
	
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

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<Interest> getInterestedClients() {
		return interestedClients;
	}

	public void setInterestedClients(List<Interest> interestedClients) {
		this.interestedClients = interestedClients;
	}

	public List<ArticleIsSport> getArticleIsSports() {
		return articleIsSports;
	}

	public void setArticleIsSports(List<ArticleIsSport> articleIsSports) {
		this.articleIsSports = articleIsSports;
	}
	
	@Transient
	public String getUri() {
		return UrlConstants.URL_SPORT + "/" + this.id;
	}
	
	@Transient
	public String getArticlesUri() {
		return UrlConstants.URL_SPORT + "/" + this.id + "/articles";
	}
	
	@Transient
	public String getArticleTypesUri(Integer articleTypeId) {
		return UrlConstants.URL_SPORT + "/" + this.id + "/articles/" + articleTypeId;
	}
	
	@Transient
	public String getProductsUri() {
		return UrlConstants.URL_SPORT + "/" + this.id + "/products";
	}

	public String getBanner() {
		return banner;
	}

	public void setBanner(String banner) {
		this.banner = banner;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public List<ProductCategory> getProductCategories() {
		return productCategories;
	}

	public void setProductCategories(List<ProductCategory> productCategories) {
		this.productCategories = productCategories;
	}

	public List<ProductIsSport> getProductIsSports() {
		return productIsSports;
	}

	public void setProductIsSports(List<ProductIsSport> productIsSports) {
		this.productIsSports = productIsSports;
	}
	
}
