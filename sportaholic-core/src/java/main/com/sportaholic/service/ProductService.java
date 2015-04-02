package com.sportaholic.service;

import java.util.List;

import com.sportaholic.model.Product;

public interface ProductService {


	public Product get(Integer id) throws Exception;
	
	public List<Product> getAll() throws Exception;
	
	public List<Product> getActivePaginated(int pageNumber, int pageSize) throws Exception;
	
	public List<Product> getActiveBySetPaginated(int sportId, int pageNumber, int pageSize) throws Exception;
	
	public List<String> create(Product product) throws Exception;
	
	public List<String> update(Product product) throws Exception;
	
	public List<Product> getBySet(Integer brandId, Integer sportId, 
			Integer productCategoryId, Integer productTypeId) throws Exception;
	
	public Product getEager(Integer id) throws Exception;
	
}
