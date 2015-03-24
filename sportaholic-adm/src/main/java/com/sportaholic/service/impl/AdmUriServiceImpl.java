package com.sportaholic.service.impl;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.sportaholic.dao.UriDao;
import com.sportaholic.dto.UriDto;
import com.sportaholic.model.Uri;
import com.sportaholic.service.AdmUriService;
import com.sportaholic.transformer.UriDtoTransformer;

@Component
public class AdmUriServiceImpl implements AdmUriService {

	private UriDao uriDao;
	private UriDtoTransformer uriDtoTransformer;
	
	@Autowired
	public AdmUriServiceImpl(UriDao uriDao, UriDtoTransformer uriDtoTransformer) {
		super();
		this.uriDao = uriDao;
		this.uriDtoTransformer = uriDtoTransformer;
	}
	
	@Override
	@Transactional
	public Uri get(Integer id) throws Exception {
		return this.uriDao.get(id);
	}

	@Override
	@Transactional
	public List<Uri> getAll() throws Exception {
		return this.uriDao.getAll();
	}
	
	@Override
	@Transactional
	public List<String> create(UriDto uriDto) throws Exception {
		List<String> status = this.testUriDto(uriDto);
		
		if (status.get(0).equals("success")) {
			Uri uri = this.uriDtoTransformer.uriDtoToUri(uriDto);
			uri.setCreatedAt(Calendar.getInstance().getTime());
			uri.setUpdatedAt(Calendar.getInstance().getTime());
			this.uriDao.create(uri);
			
			status.add(uri.getId().toString());
		}
		
		return status;
	}

	@Override
	@Transactional
	public List<String> update(UriDto uriDto) throws Exception {
		List<String> status = this.testUriDto(uriDto);
		
		if (status.get(0).equals("success")) {
			Uri uri = this.uriDtoTransformer.uriDtoToUri(uriDto);
			uri.setUpdatedAt(Calendar.getInstance().getTime());
			this.uriDao.update(uri);
			
			status.add(uriDto.getUriId().toString());
		}
		
		return status;
	}
	
	private List<String> testUriDto(UriDto uriDto) throws Exception {
		List<String> status = new ArrayList<String>();
		status.add("success");
		
		if (uriDto.getUri() == null || uriDto.getUri().length() == 0) {
			status.set(0, "error");
			status.add("uri.required");
		}
		if (uriDto.getUri() != null && !uriDto.getUri().startsWith("/")) {
			status.set(0, "error");
			status.add("uri.startsWithSlash");
		}
		if (uriDto.getUri() != null && uriDto.getUri().contains(" ")) {
			status.set(0, "error");
			status.add("uri.containsSpaces");
		}
		if (uriDto.getUri() != null) {
			Uri uri = this.uriDao.getByUri(uriDto.getUri());
			if (uri != null && uri.getId() != uriDto.getUriId()) {
				status.set(0, "error");
				status.add("uri.existant");
			}
		}
		if (uriDto.getFriendlyUri() == null || uriDto.getFriendlyUri().length() == 0) {
			status.set(0, "error");
			status.add("friendlyUri.required");
		}
		if (uriDto.getFriendlyUri() != null && !uriDto.getFriendlyUri().startsWith("/")) {
			status.set(0, "error");
			status.add("friendlyUri.startsWithSlash");
		}
		if (uriDto.getFriendlyUri() != null && uriDto.getFriendlyUri().contains(" ")) {
			status.set(0, "error");
			status.add("friendlyUri.containsSpaces");
		}
		if (uriDto.getFriendlyUri() != null) {
			Uri uri = this.uriDao.getByFriendlyUri(uriDto.getFriendlyUri());
			if (uri != null && uri.getId() != uriDto.getUriId()) {
				status.set(0, "error");
				status.add("friendlyUri.existant");
			}
		}
		if (uriDto.getUriName() == null || uriDto.getUriName().length() == 0) {
			status.set(0, "error");
			status.add("uriName.required");
		}
		if (uriDto.getParentId() == null || uriDto.getParentId() == 0) {
			if (!uriDto.getUri().equals("/")) {
				status.set(0, "error");
				status.add("parentId.required");
			}
		}
		if (uriDto.getMetaDescription() == null || uriDto.getMetaDescription().length() == 0) {
			status.set(0, "error");
			status.add("metaDescription.required");
		}
		if (uriDto.getMetaDescription() != null && uriDto.getMetaDescription().length() > 160) {
			status.set(0, "error");
			status.add("metaDescription.length");
		}
		
		return status;
	}

}
