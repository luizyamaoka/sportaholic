package com.sportaholic.dto;

import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="urlset")
public class Sitemap {

	public Sitemap() { }
	
	private List<UrlXml> urlXmls;
	
	public List<UrlXml> getUrlXmls() {
		return urlXmls;
	}

	@XmlElement(name="url")
	public void setUrlXmls(List<UrlXml> urlXmls) {
		this.urlXmls = urlXmls;
	}
	
}
