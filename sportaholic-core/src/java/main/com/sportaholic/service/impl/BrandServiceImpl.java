package com.sportaholic.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.sportaholic.dao.BrandDao;
import com.sportaholic.model.Brand;
import com.sportaholic.service.BrandService;

@Component
public class BrandServiceImpl implements BrandService {

	private BrandDao brandDao;
	
	@Autowired
	public BrandServiceImpl(BrandDao brandDao) {
		this.brandDao = brandDao;
	}
	
	@Override
	public Brand get(int id) throws Exception {
		return this.brandDao.get(id);
	}

	@Override
	public List<Brand> getAll() throws Exception {
		return this.brandDao.getAll();
	}

	@Override
	public List<String> create(Brand brand) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<String> update(Brand brand) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

}
