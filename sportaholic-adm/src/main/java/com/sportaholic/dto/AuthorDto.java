package com.sportaholic.dto;

public class AuthorDto {

	private Integer id;
	private String name;
	private String description;
	
	private Integer uriId;
	private String friendlyUri;
	private String uriName;
	private Integer parentId;
	private String metaDescription;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Integer getUriId() {
		return uriId;
	}
	public void setUriId(Integer uriId) {
		this.uriId = uriId;
	}
	public String getFriendlyUri() {
		return friendlyUri;
	}
	public void setFriendlyUri(String friendlyUri) {
		this.friendlyUri = friendlyUri;
	}
	public String getUriName() {
		return uriName;
	}
	public void setUriName(String uriName) {
		this.uriName = uriName;
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
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	
	
	
}
