package com.sportaholic.transformer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.sportaholic.dao.UriDao;
import com.sportaholic.dto.UriDto;
import com.sportaholic.model.Uri;

@Component
public class UriDtoTransformer {

	private UriDao uriDao;
	
	@Autowired
	public UriDtoTransformer(UriDao uriDao) {
		this.uriDao = uriDao;
	}
	
	public Uri uriDtoToUri(UriDto uriDto) throws Exception {
		Uri uri = uriDto.getUriId() == null ? new Uri() : this.uriDao.get(uriDto.getUriId());
		
		uri.setUri(uriDto.getUri());
		uri.setFriendlyUri(uriDto.getFriendlyUri());
		uri.setName(uriDto.getUriName());
		uri.setMetaDescription(uriDto.getMetaDescription());
		uri.setParent(this.uriDao.get(uriDto.getParentId()));
		
		return uri;
	}
	
	public UriDto uriToUriDto(Uri uri) throws Exception {
		UriDto uriDto = new UriDto();
		
		uriDto.setUriId(uri.getId());
		uriDto.setUri(uri.getUri());
		uriDto.setFriendlyUri(uri.getFriendlyUri());
		uriDto.setUriName(uri.getName());
		uriDto.setMetaDescription(uri.getMetaDescription());
		uriDto.setParentId(uri.getParent().getId());
		
		return uriDto;
	}
	
}
