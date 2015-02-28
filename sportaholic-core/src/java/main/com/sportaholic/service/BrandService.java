package com.sportaholic.service;

import java.util.List;

import com.sportaholic.model.Brand;
import com.sportaholic.model.Sport;

public interface BrandService {

	public Brand get(int id) throws Exception;
	
	public Brand getEager(int id) throws Exception;
	
	public List<Brand> getAll() throws Exception;
	
	public List<String> create(Brand brand) throws Exception;
	
	public List<String> update (Brand brand) throws Exception;
	
	public List<Sport> getPossibleSports(int brandId) throws Exception;
}
