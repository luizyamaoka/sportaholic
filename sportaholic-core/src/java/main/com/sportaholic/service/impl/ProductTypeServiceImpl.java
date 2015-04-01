package com.sportaholic.service.impl;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.sportaholic.dao.ProductTypeDao;
import com.sportaholic.model.ProductType;
import com.sportaholic.service.ProductTypeService;

@Component
public class ProductTypeServiceImpl implements ProductTypeService {

	private ProductTypeDao productTypeDao;
	
	@Autowired
	public ProductTypeServiceImpl(ProductTypeDao productTypeDao) {
		this.productTypeDao = productTypeDao;
	}
	
	@Override
	@Transactional(rollbackFor = Exception.class)
	public ProductType get(Integer id) throws Exception {
		return this.productTypeDao.get(id);
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public List<ProductType> getAll() throws Exception {
		return this.productTypeDao.getAll();
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public List<String> create(ProductType productType) throws Exception {
		List<String> status = this.testProductType(productType);
		
		if (status.get(0).equals("success")) {
			productType.setCreatedAt(Calendar.getInstance().getTime());
			productType.setUpdatedAt(Calendar.getInstance().getTime());
			Integer productCategoryId = this.productTypeDao.create(productType);
			status.add(productCategoryId.toString());
		}
		return status;
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public List<String> update(ProductType productType) throws Exception {
		List<String> status = this.testProductType(productType);
		
		if (status.get(0).equals("success")) {
			productType.setUpdatedAt(Calendar.getInstance().getTime());
			this.productTypeDao.merge(productType);
		}
		return status;
	}
	
	private List<String> testProductType(ProductType productType) throws Exception {
		List<String> status = new ArrayList<String>();
		status.add("success");
		
		if (productType.getName() == null || productType.getName().length() == 0) {
			status.set(0, "error");
			status.add("name.required");
		} else {
			ProductType existantProductType = this.productTypeDao.getByName(productType.getName());
			if (existantProductType != null && productType.getId() != existantProductType.getId()
					&& productType.getProductCategory() == existantProductType.getProductCategory()) {
				status.set(0, "error");
				status.add("name.existant");
			}
		}
		
		return status;
	}

}
