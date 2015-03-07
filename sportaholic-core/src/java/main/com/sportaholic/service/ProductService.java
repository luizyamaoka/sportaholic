package com.sportaholic.service;

import java.util.List;

import com.sportaholic.model.Product;

public interface ProductService {

	public List<Product> getAll() throws Exception;
	
	public Product get(Integer id) throws Exception;
	
	public Product getEager(Integer id) throws Exception;
	
}
