package com.sportaholic.model;

import java.math.BigDecimal;
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
@Table(name=DomainConstants.TB_PRODUCT)
public class Product {

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
	
	@Column(name="description")
	private String description;
	
	@Column(name="image")
	private String image;
	
	@Column(name="price")
	private BigDecimal price;
	
	@Column(name="is_active")
	private Boolean isActive;
	
	@Column(name="in_stock")
	private Integer inStock;
	
	@Column(name="meli_url")
	private String meliUrl;
	
	@Column(name="pagseguro_id")
	private String pagseguroId;
	
	@ManyToOne
	@JoinColumn(name="brand_id")
	private Brand brand;
	
	@OneToMany(mappedBy="product")
	private List<ProductView> productViews;
	
	@OneToMany(mappedBy="product")
	private List<ProductIsType> productIsTypes;

	@OneToMany(mappedBy="product")
	private List<ProductIsSport> productIsSports;
	
	@OneToMany(mappedBy="product")
	private List<PurchaseLine> purchaseLines;
	
	@OneToMany(mappedBy="product")
	private List<ProductComment> productComments;
	
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

	public Brand getBrand() {
		return brand;
	}

	public void setBrand(Brand brand) {
		this.brand = brand;
	}

	public List<ProductView> getProductViews() {
		return productViews;
	}

	public void setProductViews(List<ProductView> productViews) {
		this.productViews = productViews;
	}

	public List<ProductIsType> getProductIsTypes() {
		return productIsTypes;
	}

	public void setProductIsTypes(List<ProductIsType> productIsTypes) {
		this.productIsTypes = productIsTypes;
	}

	public List<ProductIsSport> getProductIsSports() {
		return productIsSports;
	}

	public void setProductIsSports(List<ProductIsSport> productIsSports) {
		this.productIsSports = productIsSports;
	}

	public List<PurchaseLine> getPurchaseLines() {
		return purchaseLines;
	}

	public void setPurchaseLines(List<PurchaseLine> purchaseLines) {
		this.purchaseLines = purchaseLines;
	}

	public List<ProductComment> getProductComments() {
		return productComments;
	}

	public void setProductComments(List<ProductComment> productComments) {
		this.productComments = productComments;
	}
	
	@Transient
	public String getUri() {
		return UrlConstants.URL_PRODUCT + "/" + this.id;
	}

	public String getMeliUrl() {
		return meliUrl;
	}

	public void setMeliUrl(String meliUrl) {
		this.meliUrl = meliUrl;
	}

	public String getPagseguroId() {
		return pagseguroId;
	}

	public void setPagseguroId(String pagseguroId) {
		this.pagseguroId = pagseguroId;
	}
	
}
