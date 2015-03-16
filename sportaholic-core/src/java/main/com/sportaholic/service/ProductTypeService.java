package com.sportaholic.service;

import java.util.List;

import com.sportaholic.model.ProductType;

public interface ProductTypeService {

	public ProductType get(Integer id) throws Exception;
	
	public List<ProductType> getAll() throws Exception;
	
	public List<String> create(ProductType productType) throws Exception;
	
	public List<String> update(ProductType productType) throws Exception;
	
}
