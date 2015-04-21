package com.sportaholic.dto;

import org.springframework.web.multipart.MultipartFile;

public class SportDto {

	private Integer id;
	private String name;
	private String banner;
	private String description;
	private MultipartFile bannerImage;
	
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
	public MultipartFile getBannerImage() {
		return bannerImage;
	}
	public void setBannerImage(MultipartFile bannerImage) {
		this.bannerImage = bannerImage;
	}

	
}
