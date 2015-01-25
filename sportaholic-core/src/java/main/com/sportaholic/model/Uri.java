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

@Entity
@Table(name=DomainConstants.TB_URI)
public class Uri {	
	
	public Uri() { };
	
	public Uri(Integer id, String name, String uri, String friendlyUri) {
		this.id = id;
		this.name = name;
		this.uri = uri;
		this.friendlyUri = friendlyUri;
	}
	
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
	
	@Column(name="uri")
	private String uri;
	
	@Column(name="friendly_uri")
	private String friendlyUri;
	
	@Column(name="meta_description")
	private String metaDescription;
	
	@ManyToOne
	@JoinColumn(name="parent_id")
	private Uri parent;
	
	@OneToMany(mappedBy="parent")
	private List<Uri> children;

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

	public String getUri() {
		return uri;
	}

	public void setUri(String uri) {
		this.uri = uri;
	}

	public String getFriendlyUri() {
		return friendlyUri;
	}

	public void setFriendlyUri(String friendlyUri) {
		this.friendlyUri = friendlyUri;
	}

	public Uri getParent() {
		return parent;
	}

	public void setParent(Uri parent) {
		this.parent = parent;
	}

	public List<Uri> getChildren() {
		return children;
	}

	public void setChildren(List<Uri> children) {
		this.children = children;
	}

	public String getMetaDescription() {
		return metaDescription;
	}

	public void setMetaDescription(String metaDescription) {
		this.metaDescription = metaDescription;
	}
	
	
	
}
