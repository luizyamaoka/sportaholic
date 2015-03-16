package com.sportaholic.service;

import java.util.List;

import com.sportaholic.dto.ProductDto;
import com.sportaholic.model.Product;

public interface AdmProductService {

	public Product get(Integer id) throws Exception;
	
	public Product getEager(Integer id) throws Exception;
	
	public List<Product> getAll() throws Exception;
	
	public List<String> create(ProductDto productDto) throws Exception;
	
	public List<String> update(ProductDto productDto) throws Exception;
	
}
