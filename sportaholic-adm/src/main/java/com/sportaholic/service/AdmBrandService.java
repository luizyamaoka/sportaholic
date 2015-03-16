package com.sportaholic.service;

import java.util.List;

import com.sportaholic.dto.BrandDto;

public interface AdmBrandService {

	public List<String> create(BrandDto brandDto) throws Exception;
	
	public List<String> update(BrandDto brandDto) throws Exception;
	
}
