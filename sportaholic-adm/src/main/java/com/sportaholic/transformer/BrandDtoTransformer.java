package com.sportaholic.transformer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.sportaholic.dao.BrandDao;
import com.sportaholic.dao.UriDao;
import com.sportaholic.dto.BrandDto;
import com.sportaholic.model.Brand;
import com.sportaholic.model.Uri;
import com.sportaholic.model.UrlConstants;

@Component
public class BrandDtoTransformer {

	private BrandDao brandDao;
	private UriDao uriDao;
	
	@Autowired
	public BrandDtoTransformer(BrandDao brandDao, UriDao uriDao) {
		this.brandDao = brandDao;
		this.uriDao = uriDao;
	}
	
	@Transactional(rollbackFor = Exception.class)
	public Brand brandDtoToBrand(BrandDto brandDto) throws Exception {
		Brand brand = brandDto.getId() == null ? new Brand() : this.brandDao.get(brandDto.getId());
		
		brand.setName(brandDto.getName());
		brand.setDescription(brandDto.getDescription());
		brand.setLogo(brandDto.getLogo());
		
		return brand;
	}
	
	@Transactional(rollbackFor = Exception.class)
	public Uri brandDtoToUri(BrandDto brandDto) throws Exception {
		Uri uri = brandDto.getUriId() == null ? new Uri() : this.uriDao.get(brandDto.getUriId());
		
		uri.setFriendlyUri(brandDto.getFriendlyUri());
		uri.setName(brandDto.getUriName());
		uri.setMetaDescription(brandDto.getMetaDescription());
		uri.setParent(this.uriDao.get(brandDto.getParentId()));
		
		return uri;
	}
	
	@Transactional(rollbackFor = Exception.class)
	public BrandDto brandToBrandDto(Brand brand) throws Exception {
		BrandDto brandDto = new BrandDto();
		
		brandDto.setId(brand.getId());
		brandDto.setName(brand.getName());
		brandDto.setDescription(brand.getDescription());
		brandDto.setLogo(brand.getLogo());
		
		Uri uri = this.uriDao.getByUri(UrlConstants.URL_BRAND + "/" + brand.getId());
		
		if (uri != null) {
			brandDto.setUriId(uri.getId());
			brandDto.setFriendlyUri(uri.getFriendlyUri());
			brandDto.setUriName(uri.getName());
			brandDto.setParentId(uri.getParent().getId());
			brandDto.setMetaDescription(uri.getMetaDescription());
		}
		
		return brandDto;
	}
	
}
