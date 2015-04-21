package com.sportaholic.dto;

import org.springframework.web.multipart.MultipartFile;

public class BrandDto {

	private Integer id;
	private String name;
	private String description;
	private String logo;
	private MultipartFile logoFile;
	
	private Integer uriId;
	private String friendlyUri;
	private String uriName;
	private Integer parentId;
	private String metaDescription;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
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
	public String getLogo() {
		return logo;
	}
	public void setLogo(String logo) {
		this.logo = logo;
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
	public MultipartFile getLogoFile() {
		return logoFile;
	}
	public void setLogoFile(MultipartFile logoFile) {
		this.logoFile = logoFile;
	}
	
	
	
}
