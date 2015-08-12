package com.sportaholic.service.impl;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.sportaholic.dao.AuthorDao;
import com.sportaholic.dao.UriDao;
import com.sportaholic.dto.AuthorDto;
import com.sportaholic.model.Author;
import com.sportaholic.model.Uri;
import com.sportaholic.model.UrlConstants;
import com.sportaholic.service.AdmAuthorService;
import com.sportaholic.transformer.AuthorDtoTransformer;

@Component
public class AdmAuthorServiceImpl implements AdmAuthorService {

	private AuthorDao authorDao;
	private UriDao uriDao;
	private AuthorDtoTransformer authorDtoTransformer;
	
	@Autowired
	public AdmAuthorServiceImpl(AuthorDao authorDao, UriDao uriDao,
			AuthorDtoTransformer authorDtoTransformer) {
		this.authorDao = authorDao;
		this.uriDao = uriDao;
		this.authorDtoTransformer = authorDtoTransformer;
	}
	
	@Override
	@Transactional(rollbackFor = Exception.class)
	public List<String> create(AuthorDto authorDto) throws Exception {
		List<String> status = this.testAuthorDto(authorDto);
		
		if (status.get(0).equals("success")) {
			Author author = this.authorDtoTransformer.authorDtoToAuthor(authorDto);
			author.setCreatedAt(Calendar.getInstance().getTime());
			author.setUpdatedAt(Calendar.getInstance().getTime());
			Integer authorId = this.authorDao.create(author);
			
			Uri uri = this.authorDtoTransformer.authorDtoToUri(authorDto);
			uri.setUri(UrlConstants.URL_AUTHOR + "/" + authorId.toString());
			uri.setCreatedAt(Calendar.getInstance().getTime());
			uri.setUpdatedAt(Calendar.getInstance().getTime());
			this.uriDao.create(uri);
			
			status.add(authorId.toString());
		}
		
		return status;
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public List<String> update(AuthorDto authorDto) throws Exception {
		List<String> status = this.testAuthorDto(authorDto);
		
		if (status.get(0).equals("success")) {
			Author author = this.authorDtoTransformer.authorDtoToAuthor(authorDto);
			author.setUpdatedAt(Calendar.getInstance().getTime());
			this.authorDao.update(author);
			
			Uri uri = this.authorDtoTransformer.authorDtoToUri(authorDto);
			uri.setUpdatedAt(Calendar.getInstance().getTime());
			this.uriDao.update(uri);
			
			status.add(authorDto.getId().toString());
		}
		
		return status;
	}

	private List<String> testAuthorDto(AuthorDto authorDto) throws Exception {
		List<String> status = new ArrayList<String>();
		status.add("success");
		
		if (authorDto.getName() == null || authorDto.getName().length() == 0) {
			status.set(0, "error");
			status.add("name.required");
		}
		if (authorDto.getDescription() == null || authorDto.getDescription().length() == 0) {
			status.set(0, "error");
			status.add("description.required");
		}
		if (authorDto.getFriendlyUri() == null || authorDto.getFriendlyUri().length() == 0) {
			status.set(0, "error");
			status.add("friendlyUri.required");
		}
		if (authorDto.getFriendlyUri() != null && !authorDto.getFriendlyUri().startsWith("/")) {
			status.set(0, "error");
			status.add("friendlyUri.startsWithSlash");
		}
		if (authorDto.getFriendlyUri() != null && authorDto.getFriendlyUri().contains(" ")) {
			status.set(0, "error");
			status.add("friendlyUri.containsSpaces");
		}
		if (authorDto.getFriendlyUri() != null) {
			Uri uri = this.uriDao.getByFriendlyUri(authorDto.getFriendlyUri());
			if (uri != null && !uri.getId().equals(authorDto.getUriId())) {
				status.set(0, "error");
				status.add("friendlyUri.existant");
			}
		}
		if (authorDto.getUriName() == null || authorDto.getUriName().length() == 0) {
			status.set(0, "error");
			status.add("uriName.required");
		}
		if (authorDto.getParentId() == null || authorDto.getParentId() == 0) {
			status.set(0, "error");
			status.add("parentId.required");
		}
		if (authorDto.getMetaDescription() == null || authorDto.getMetaDescription().length() == 0) {
			status.set(0, "error");
			status.add("metaDescription.required");
		}
		if (authorDto.getMetaDescription() != null && authorDto.getMetaDescription().length() > 160) {
			status.set(0, "error");
			status.add("metaDescription.length");
		}
		
		return status;
	}
	
}
