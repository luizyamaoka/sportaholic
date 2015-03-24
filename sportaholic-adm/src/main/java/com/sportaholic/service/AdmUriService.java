package com.sportaholic.service;

import java.util.List;

import com.sportaholic.dto.UriDto;
import com.sportaholic.model.Uri;

public interface AdmUriService {

	public Uri get(Integer id) throws Exception;
	
	public List<Uri> getAll() throws Exception;
	
	public List<String> create(UriDto uriDto) throws Exception;
	
	public List<String> update(UriDto uriDto) throws Exception;
	
}
