package com.sportaholic.dto;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlRootElement(name="url")
@XmlType(propOrder = {"loc", "priority", "changefreq"})
public class UrlXml {

	public UrlXml() { };
	
	private String loc;
	private String changefreq;
	private Double priority;
	
	public String getLoc() {
		return loc;
	}
	
	@XmlElement
	public void setLoc(String loc) {
		this.loc = loc;
	}
	public String getChangefreq() {
		return changefreq;
	}
	
	@XmlElement
	public void setChangefreq(String changefreq) {
		this.changefreq = changefreq;
	}
	public Double getPriority() {
		return priority;
	}
	
	@XmlElement
	public void setPriority(Double priority) {
		this.priority = priority;
	}
	
}
