package com.sportaholic.service;

import java.util.List;

import com.sportaholic.model.ProductCategory;

public interface ProductCategoryService {

	public List<ProductCategory> getAll() throws Exception;
	
	public ProductCategory get(Integer id) throws Exception;
	
	public List<String> create(ProductCategory productCategory) throws Exception;
	
	public List<String> update(ProductCategory productCategory) throws Exception;
	
}
