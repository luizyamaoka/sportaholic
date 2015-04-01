package com.sportaholic.service.impl;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.sportaholic.dao.BrandDao;
import com.sportaholic.dao.UriDao;
import com.sportaholic.dto.BrandDto;
import com.sportaholic.model.Brand;
import com.sportaholic.model.Uri;
import com.sportaholic.model.UrlConstants;
import com.sportaholic.service.AdmBrandService;
import com.sportaholic.transformer.BrandDtoTransformer;

@Component
public class AdmBrandServiceImpl implements AdmBrandService {

	public BrandDao brandDao;
	public UriDao uriDao;
	public BrandDtoTransformer brandDtoTransformer;
	
	@Autowired
	public AdmBrandServiceImpl(BrandDao brandDao, UriDao uriDao, 
			BrandDtoTransformer brandDtoTransformer) {
		this.brandDao = brandDao;
		this.uriDao = uriDao;
		this.brandDtoTransformer = brandDtoTransformer;
	}
	
	@Override
	@Transactional(rollbackFor = Exception.class)
	public List<String> create(BrandDto brandDto) throws Exception {
		List<String> status = this.testBrandDto(brandDto);
		
		if (status.get(0).equals("success")) {
			Brand brand = this.brandDtoTransformer.brandDtoToBrand(brandDto);
			brand.setCreatedAt(Calendar.getInstance().getTime());
			brand.setUpdatedAt(Calendar.getInstance().getTime());
			Integer brandId = this.brandDao.create(brand);
			
			Uri uri = this.brandDtoTransformer.brandDtoToUri(brandDto);
			uri.setUri(UrlConstants.URL_BRAND + "/" + brandId.toString());
			uri.setCreatedAt(Calendar.getInstance().getTime());
			uri.setUpdatedAt(Calendar.getInstance().getTime());
			this.uriDao.create(uri);
			
			status.add(brandId.toString());
		}
		
		return status;
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public List<String> update(BrandDto brandDto) throws Exception {
		List<String> status = this.testBrandDto(brandDto);
		
		if (status.get(0).equals("success")) {
			Brand brand = this.brandDtoTransformer.brandDtoToBrand(brandDto);
			brand.setUpdatedAt(Calendar.getInstance().getTime());
			this.brandDao.update(brand);
			
			Uri uri = this.brandDtoTransformer.brandDtoToUri(brandDto);
			uri.setUpdatedAt(Calendar.getInstance().getTime());
			this.uriDao.update(uri);
			
			status.add(brandDto.getId().toString());
		}
		
		return status;
	}
	
	private List<String> testBrandDto(BrandDto brandDto) throws Exception {
		List<String> status = new ArrayList<String>();
		status.add("success");
		
		if (brandDto.getName() == null || brandDto.getName().length() == 0) {
			status.set(0, "error");
			status.add("name.required");
		}
		if (brandDto.getDescription() == null || brandDto.getDescription().length() == 0) {
			status.set(0, "error");
			status.add("description.required");
		}
		if (brandDto.getLogo() == null || brandDto.getLogo().length() == 0) {
			status.set(0, "error");
			status.add("logo.required");
		}
		if (brandDto.getFriendlyUri() == null || brandDto.getFriendlyUri().length() == 0) {
			status.set(0, "error");
			status.add("friendlyUri.required");
		}
		if (brandDto.getFriendlyUri() != null && !brandDto.getFriendlyUri().startsWith("/")) {
			status.set(0, "error");
			status.add("friendlyUri.startsWithSlash");
		}
		if (brandDto.getFriendlyUri() != null && brandDto.getFriendlyUri().contains(" ")) {
			status.set(0, "error");
			status.add("friendlyUri.containsSpaces");
		}
		if (brandDto.getFriendlyUri() != null) {
			Uri uri = this.uriDao.getByFriendlyUri(brandDto.getFriendlyUri());
			if (uri != null && uri.getId() != brandDto.getUriId()) {
				status.set(0, "error");
				status.add("friendlyUri.existant");
			}
		}
		if (brandDto.getUriName() == null || brandDto.getUriName().length() == 0) {
			status.set(0, "error");
			status.add("uriName.required");
		}
		if (brandDto.getParentId() == null || brandDto.getParentId() == 0) {
			status.set(0, "error");
			status.add("parentId.required");
		}
		if (brandDto.getMetaDescription() == null || brandDto.getMetaDescription().length() == 0) {
			status.set(0, "error");
			status.add("metaDescription.required");
		}
		if (brandDto.getMetaDescription() != null && brandDto.getMetaDescription().length() > 160) {
			status.set(0, "error");
			status.add("metaDescription.length");
		}
		
		return status;
	}

}
