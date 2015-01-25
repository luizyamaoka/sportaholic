package com.sportaholic.service.impl;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.sportaholic.dao.UriDao;
import com.sportaholic.model.Uri;
import com.sportaholic.service.UriService;

@Component
public class UriServiceImpl implements UriService {

	private UriDao uriDao;
	
	@Autowired
	public void setUriDao(UriDao uriDao) {
		this.uriDao = uriDao;
	}
	
	@Override
	@Transactional
	public Uri getByUri(String uri) throws Exception {
		return this.uriDao.getByUri(uri);
	}

	@Override
	@Transactional
	public Uri getByFriendlyUri(String friendlyUri) throws Exception {
		return this.uriDao.getByFrindlyUri(friendlyUri);
	}

	@Override
	@Transactional
	public String getFriendlyUri(String uri) throws Exception {
		Uri foundUri = this.uriDao.getByUri(uri);
		return foundUri == null ? uri : foundUri.getFriendlyUri();
	}

}
