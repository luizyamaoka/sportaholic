package com.sportaholic.transformer;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.sportaholic.dao.SportDao;
import com.sportaholic.dao.UriDao;
import com.sportaholic.dto.SportDto;
import com.sportaholic.model.Sport;
import com.sportaholic.model.Uri;
import com.sportaholic.model.UrlConstants;

@Component
public class SportDtoTransformer {

	private SportDao sportDao;
	private UriDao uriDao;
	
	@Autowired
	public SportDtoTransformer(SportDao sportDao, UriDao uriDao) {
		this.sportDao = sportDao;
		this.uriDao = uriDao;
	}
	
	@Transactional
	public Sport sportDtoToSport(SportDto sportDto) throws Exception {
		Sport sport = sportDto.getId() == null ? new Sport() : this.sportDao.get(sportDto.getId());
		
		sport.setId(sportDto.getId());
		sport.setName(sportDto.getName());
		sport.setDescription(sportDto.getDescription());
		sport.setBanner(sportDto.getBanner());
		
		return sport;
	}
	
	@Transactional
	public Uri sportDtoToUri(SportDto sportDto) throws Exception {
		Uri uri = sportDto.getUriId() == null ? new Uri() : this.uriDao.get(sportDto.getUriId());
		
		uri.setFriendlyUri(sportDto.getFriendlyUri());
		uri.setName(sportDto.getUriName());
		uri.setMetaDescription(sportDto.getMetaDescription());
		uri.setParent(this.uriDao.get(sportDto.getParentId()));
		
		return uri;
	}
	
	@Transactional
	public SportDto sportToSportDto(Sport sport) throws Exception {
		SportDto sportDto = new SportDto();
		
		sportDto.setId(sport.getId());
		sportDto.setName(sport.getName());
		sportDto.setDescription(sport.getDescription());
		sportDto.setBanner(sport.getBanner());
		
		Uri uri = this.uriDao.getByUri(UrlConstants.URL_SPORT + "/" + sport.getId());
		
		if (uri != null) {
			sportDto.setUriId(uri.getId());
			sportDto.setFriendlyUri(uri.getFriendlyUri());
			sportDto.setUriName(uri.getName());
			sportDto.setParentId(uri.getParent().getId());
			sportDto.setMetaDescription(uri.getMetaDescription());
		}
		
		return sportDto;
	}
	
}
