package com.sportaholic.service.impl;

import java.util.List;

import javax.transaction.Transactional;

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
	@Transactional
	public Brand get(int id) throws Exception {
		return this.brandDao.get(id);
	}

	@Override
	@Transactional
	public List<Brand> getAll() throws Exception {
		return this.brandDao.getAll();
	}

	@Override
	@Transactional
	public List<String> create(Brand brand) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	@Transactional
	public List<String> update(Brand brand) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

}
