package com.sportaholic.transformer;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.sportaholic.dao.ProductCategoryDao;
import com.sportaholic.dao.ProductTypeDao;
import com.sportaholic.dto.ProductTypeDto;
import com.sportaholic.model.ProductType;

@Component
public class ProductTypeDtoTransformer {

	private ProductTypeDao productTypeDao;
	private ProductCategoryDao productCategoryDao;
	
	@Autowired
	public ProductTypeDtoTransformer(ProductTypeDao productTypeDao,
			ProductCategoryDao productCategoryDao) {
		this.productTypeDao = productTypeDao;
		this.productCategoryDao = productCategoryDao;
	}
	
	@Transactional
	public ProductTypeDto productTypeToProductTypeDto(ProductType productType) throws Exception {
		ProductTypeDto productTypeDto = new ProductTypeDto();
		productTypeDto.setId(productType.getId());
		productTypeDto.setName(productType.getName());
		productTypeDto.setProductCategoryId(productType.getProductCategory().getId());
		return productTypeDto;
	}
	
	@Transactional
	public ProductType ProductTypeDtoToProductType(ProductTypeDto productTypeDto) throws Exception {
		ProductType productType = productTypeDto.getId() == null ? new ProductType() : 
			this.productTypeDao.get(productTypeDto.getId());
		productType.setName(productTypeDto.getName());
		productType.setProductCategory(this.productCategoryDao.get(productTypeDto.getProductCategoryId()));
		return productType;
	}
	
}
