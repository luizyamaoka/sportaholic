package com.sportaholic.service.impl;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.sportaholic.dao.ProductCategoryDao;
import com.sportaholic.model.ProductCategory;
import com.sportaholic.service.ProductCategoryService;

@Component
public class ProductCategoryServiceImpl implements ProductCategoryService {

	private ProductCategoryDao productCategoryDao;
	
	@Autowired
	public ProductCategoryServiceImpl(ProductCategoryDao productCategoryDao) {
		this.productCategoryDao = productCategoryDao;
	}
	
	@Override
	@Transactional(rollbackFor = Exception.class)
	public List<ProductCategory> getAll() throws Exception {
		return this.productCategoryDao.getAll();
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public ProductCategory get(Integer id) throws Exception {
		return this.productCategoryDao.get(id);
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public List<String> create(ProductCategory productCategory)
			throws Exception {
		List<String> status = this.testProductCategory(productCategory);
		
		if (status.get(0).equals("success")) {
			productCategory.setCreatedAt(Calendar.getInstance().getTime());
			productCategory.setUpdatedAt(Calendar.getInstance().getTime());
			Integer productCategoryId = this.productCategoryDao.create(productCategory);
			status.add(productCategoryId.toString());
		}
		return status;
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public List<String> update(ProductCategory productCategory)
			throws Exception {
		List<String> status = this.testProductCategory(productCategory);
		
		if (status.get(0).equals("success")) {
			productCategory.setUpdatedAt(Calendar.getInstance().getTime());
			this.productCategoryDao.merge(productCategory);
		}
		return status;
	}
	
	private List<String> testProductCategory(ProductCategory productCategory) throws Exception {
		List<String> status = new ArrayList<String>();
		status.add("success");
		
		if (productCategory.getName() == null || productCategory.getName().length() == 0) {
			status.set(0, "error");
			status.add("name.required");
		} else {
			ProductCategory existantArticleType = this.productCategoryDao.getByName(productCategory.getName(), productCategory.getSport().getId());
			if (existantArticleType != null && productCategory.getId() != existantArticleType.getId()) {
				status.set(0, "error");
				status.add("name.existant");
			}
		}
		
		return status;
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public List<ProductCategory> getEagerBySet(Integer sportId) throws Exception {
		List<ProductCategory> productCategories = this.productCategoryDao.getBySet(sportId);
		for(ProductCategory productCategory : productCategories) {
			Hibernate.initialize(productCategory.getProductTypes());
		}
		return productCategories;
	}

}
