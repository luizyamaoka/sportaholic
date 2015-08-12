package com.sportaholic.service.impl;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.sportaholic.dao.SportDao;
import com.sportaholic.dao.UriDao;
import com.sportaholic.dto.SportDto;
import com.sportaholic.model.Sport;
import com.sportaholic.model.Uri;
import com.sportaholic.model.UrlConstants;
import com.sportaholic.service.AdmSportService;
import com.sportaholic.service.AmazonS3Service;
import com.sportaholic.transformer.SportDtoTransformer;

@Component
public class AdmSportServiceImpl implements AdmSportService {

	private SportDao sportDao;
	private UriDao uriDao;
	private SportDtoTransformer sportDtoTransformer;
	private AmazonS3Service amazonS3Service;
	
	@Autowired
	public AdmSportServiceImpl(SportDao sportDao, UriDao uriDao, SportDtoTransformer sportDtoTransformer,
			AmazonS3Service amazonS3Service) {
		this.sportDao = sportDao;
		this.uriDao = uriDao;
		this.sportDtoTransformer = sportDtoTransformer;
		this.amazonS3Service = amazonS3Service;
	}
	
	@Override
	@Transactional(rollbackFor = Exception.class)
	public List<String> create(SportDto sportDto) throws Exception {
		List<String> status = this.testSportDto(sportDto);
		
		if (status.get(0).equals("success")) {
			Sport sport = this.sportDtoTransformer.sportDtoToSport(sportDto);
			sport.setCreatedAt(Calendar.getInstance().getTime());
			sport.setUpdatedAt(Calendar.getInstance().getTime());
			Integer sportId = this.sportDao.create(sport);
			
			Uri uri = this.sportDtoTransformer.sportDtoToUri(sportDto);
			uri.setUri(UrlConstants.URL_SPORT + "/" + sportId.toString());
			uri.setCreatedAt(Calendar.getInstance().getTime());
			uri.setUpdatedAt(Calendar.getInstance().getTime());
			this.uriDao.create(uri);
			
			if (sportDto.getBannerImage() != null && !sportDto.getBannerImage().isEmpty()) {
				this.amazonS3Service.upload(sport.getBanner(), sportDto.getBannerImage().getInputStream());
			}
			
			status.add(sportId.toString());
		}
		
		return status;
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public List<String> update(SportDto sportDto) throws Exception {
		List<String> status = this.testSportDto(sportDto);
		
		if (status.get(0).equals("success")) {
			Sport sport = this.sportDtoTransformer.sportDtoToSport(sportDto);
			sport.setUpdatedAt(Calendar.getInstance().getTime());
			this.sportDao.update(sport);
			
			Uri uri = this.sportDtoTransformer.sportDtoToUri(sportDto);
			uri.setUpdatedAt(Calendar.getInstance().getTime());
			this.uriDao.update(uri);
			
			if (sportDto.getBannerImage() != null && !sportDto.getBannerImage().isEmpty()) {
				this.amazonS3Service.upload(sport.getBanner(), sportDto.getBannerImage().getInputStream());
			}
			
			status.add(sportDto.getId().toString());
		}
		
		return status;
	}

	private List<String> testSportDto(SportDto sportDto) throws Exception {
		List<String> status = new ArrayList<String>();
		status.add("success");
		
		if (sportDto.getName() == null || sportDto.getName().length() == 0) {
			status.set(0, "error");
			status.add("name.required");
		}
		if (sportDto.getDescription() == null || sportDto.getDescription().length() == 0) {
			status.set(0, "error");
			status.add("description.required");
		}
		if (sportDto.getBanner() == null || sportDto.getBanner().length() == 0) {
			status.set(0, "error");
			status.add("banner.required");
		}
		if (sportDto.getFriendlyUri() == null || sportDto.getFriendlyUri().length() == 0) {
			status.set(0, "error");
			status.add("friendlyUri.required");
		}
		if (sportDto.getFriendlyUri() != null && !sportDto.getFriendlyUri().startsWith("/")) {
			status.set(0, "error");
			status.add("friendlyUri.startsWithSlash");
		}
		if (sportDto.getFriendlyUri() != null && sportDto.getFriendlyUri().contains(" ")) {
			status.set(0, "error");
			status.add("friendlyUri.containsSpaces");
		}
		if (sportDto.getFriendlyUri() != null) {
			Uri uri = this.uriDao.getByFriendlyUri(sportDto.getFriendlyUri());
			if (uri != null && !uri.getId().equals(sportDto.getUriId())) {
				status.set(0, "error");
				status.add("friendlyUri.existant");
			}
		}
		if (sportDto.getUriName() == null || sportDto.getUriName().length() == 0) {
			status.set(0, "error");
			status.add("uriName.required");
		}
		if (sportDto.getParentId() == null || sportDto.getParentId() == 0) {
			status.set(0, "error");
			status.add("parentId.required");
		}
		if (sportDto.getMetaDescription() == null || sportDto.getMetaDescription().length() == 0) {
			status.set(0, "error");
			status.add("metaDescription.required");
		}
		if (sportDto.getMetaDescription() != null && sportDto.getMetaDescription().length() > 160) {
			status.set(0, "error");
			status.add("metaDescription.length");
		}
		
		return status;
	}
	
}
