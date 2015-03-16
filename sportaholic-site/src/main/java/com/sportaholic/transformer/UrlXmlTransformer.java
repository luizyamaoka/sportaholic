package com.sportaholic.transformer;

import org.springframework.stereotype.Component;

import com.sportaholic.EnvironmentConstants;
import com.sportaholic.dto.UrlXml;
import com.sportaholic.model.Uri;

@Component
public class UrlXmlTransformer {

	public UrlXml urlToUrlXml(Uri uri) throws Exception {
		UrlXml urlXml = new UrlXml();
		urlXml.setLoc(EnvironmentConstants.DOMAIN + uri.getFriendlyUri());
		urlXml.setPriority(1.0);
		urlXml.setChangefreq("weekly");
		return urlXml;
	}
	
}
