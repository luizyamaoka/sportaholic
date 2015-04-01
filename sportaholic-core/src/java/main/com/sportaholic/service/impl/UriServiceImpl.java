package com.sportaholic.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

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
	@Transactional(rollbackFor = Exception.class)
	public Uri get(Integer id) throws Exception {
		return this.uriDao.get(id);
	}
	
	@Override
	@Transactional(rollbackFor = Exception.class)
	public Uri getByUri(String uri) throws Exception {
		return this.uriDao.getByUri(uri);
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public Uri getByFriendlyUri(String friendlyUri) throws Exception {
		return this.uriDao.getByFriendlyUri(friendlyUri);
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public String getFriendlyUri(String uri) throws Exception {
		Uri foundUri = this.uriDao.getByUri(uri);
		return foundUri == null ? uri : foundUri.getFriendlyUri();
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public List<Uri> getAll() throws Exception {
		return this.uriDao.getAll();
	}

}
