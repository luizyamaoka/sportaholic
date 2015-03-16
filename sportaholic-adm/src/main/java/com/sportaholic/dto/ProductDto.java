package com.sportaholic.dto;

import java.math.BigDecimal;
import java.util.List;

public class ProductDto {

	private Integer id;
	private String name;
	private String description;
	private String image;
	private BigDecimal price;
	private Boolean isActive;
	private Integer inStock;
	private Integer brandId;
	private List<Integer> productTypeIds;
	private List<Integer> productSportIds;
	
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
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	public BigDecimal getPrice() {
		return price;
	}
	public void setPrice(BigDecimal price) {
		this.price = price;
	}
	public Boolean getIsActive() {
		return isActive;
	}
	public void setIsActive(Boolean isActive) {
		this.isActive = isActive;
	}
	public Integer getInStock() {
		return inStock;
	}
	public void setInStock(Integer inStock) {
		this.inStock = inStock;
	}
	public Integer getBrandId() {
		return brandId;
	}
	public void setBrandId(Integer brandId) {
		this.brandId = brandId;
	}
	public List<Integer> getProductTypeIds() {
		return productTypeIds;
	}
	public void setProductTypeIds(List<Integer> productTypeIds) {
		this.productTypeIds = productTypeIds;
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
	public List<Integer> getProductSportIds() {
		return productSportIds;
	}
	public void setProductSportIds(List<Integer> productSportIds) {
		this.productSportIds = productSportIds;
	}
	
}
