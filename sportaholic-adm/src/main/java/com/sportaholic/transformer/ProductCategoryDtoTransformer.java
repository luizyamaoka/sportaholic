package com.sportaholic.transformer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.sportaholic.dao.ProductCategoryDao;
import com.sportaholic.dao.SportDao;
import com.sportaholic.dto.ProductCategoryDto;
import com.sportaholic.model.ProductCategory;

@Component
public class ProductCategoryDtoTransformer {

	private ProductCategoryDao productCategoryDao;
	private SportDao sportDao;
	
	@Autowired
	public ProductCategoryDtoTransformer(ProductCategoryDao productCategoryDao,
			SportDao sportDao) {
		this.productCategoryDao = productCategoryDao;
		this.sportDao = sportDao;
	}
	
	@Transactional(rollbackFor = Exception.class)
	public ProductCategoryDto productCategoryToProductCategoryDto(ProductCategory productCategory) throws Exception {
		ProductCategoryDto productCategoryDto = new ProductCategoryDto();
		productCategoryDto.setId(productCategory.getId());
		productCategoryDto.setName(productCategory.getName());
		productCategoryDto.setSportId(productCategory.getSport().getId());
		return productCategoryDto;
	}
	
	@Transactional(rollbackFor = Exception.class)
	public ProductCategory ProductCategoryDtoToProductCategory(ProductCategoryDto productCategoryDto) throws Exception {
		ProductCategory productCategory = productCategoryDto.getId() == null ? new ProductCategory() : 
			this.productCategoryDao.get(productCategoryDto.getId());
		productCategory.setName(productCategoryDto.getName());
		productCategory.setSport(this.sportDao.get(productCategoryDto.getSportId()));
		return productCategory;
	}
	
}
