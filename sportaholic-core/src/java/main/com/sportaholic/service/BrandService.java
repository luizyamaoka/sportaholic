package com.sportaholic.service;

import java.util.List;

import com.sportaholic.model.Brand;

public interface BrandService {

	public Brand get(int id) throws Exception;
	
	public List<Brand> getAll() throws Exception;
	
	public List<String> create(Brand brand) throws Exception;
	
	public List<String> update (Brand brand) throws Exception;
}
