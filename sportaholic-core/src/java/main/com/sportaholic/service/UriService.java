package com.sportaholic.service;

import java.util.List;

import com.sportaholic.model.Uri;

public interface UriService {

	public Uri get(Integer id) throws Exception;
	
	public Uri getByUri(String uri) throws Exception;
	
	public Uri getByFriendlyUri(String friendlyUri) throws Exception;
	
	public String getFriendlyUri(String uri) throws Exception;

	public List<Uri> getAll() throws Exception;
	
}
