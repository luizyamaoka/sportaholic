package com.sportaholic.service;

import com.sportaholic.model.Uri;

public interface UriService {

	public Uri getByUri(String uri) throws Exception;
	
	public Uri getByFriendlyUri(String friendlyUri) throws Exception;
	
	public String getFriendlyUri(String uri) throws Exception;
	
}
