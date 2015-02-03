package com.sportaholic.dto;

import java.util.List;

public class ArticleDto {

	private Integer id;
	private String title;
	private String subtitle;
	private String content;
	private Integer authorId;
	private List<Integer> sportIds;
	private List<Integer> articleTypeIds;
	
	private Integer uriId;
	private String friendlyUri;
	private String name;
	private Integer parentId;
	private String metaDescription;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getSubtitle() {
		return subtitle;
	}
	public void setSubtitle(String subtitle) {
		this.subtitle = subtitle;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public Integer getAuthorId() {
		return authorId;
	}
	public void setAuthorId(Integer authorId) {
		this.authorId = authorId;
	}
	public List<Integer> getSportIds() {
		return sportIds;
	}
	public void setSportIds(List<Integer> sportIds) {
		this.sportIds = sportIds;
	}
	public List<Integer> getArticleTypeIds() {
		return articleTypeIds;
	}
	public void setArticleTypeIds(List<Integer> articleTypeIds) {
		this.articleTypeIds = articleTypeIds;
	}
	public String getFriendlyUri() {
		return friendlyUri;
	}
	public void setFriendlyUri(String friendlyUri) {
		this.friendlyUri = friendlyUri;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Integer getParentId() {
		return parentId;
	}
	public void setParentId(Integer parentId) {
		this.parentId = parentId;
	}
	public String getMetaDescription() {
		return metaDescription;
	}
	public void setMetaDescription(String metaDescription) {
		this.metaDescription = metaDescription;
	}
	public Integer getUriId() {
		return uriId;
	}
	public void setUriId(Integer uriId) {
		this.uriId = uriId;
	}
	
	
	
}
