package com.sportaholic.service.impl;

import java.util.List;

import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.sportaholic.dao.BrandDao;
import com.sportaholic.model.Brand;
import com.sportaholic.model.Sport;
import com.sportaholic.service.BrandService;

@Component
public class BrandServiceImpl implements BrandService {

	private BrandDao brandDao;
	
	@Autowired
	public BrandServiceImpl(BrandDao brandDao) {
		this.brandDao = brandDao;
	}
	
	@Override
	@Transactional(rollbackFor = Exception.class)
	public Brand get(int id) throws Exception {
		return this.brandDao.get(id);
	}
	
	@Override
	@Transactional(rollbackFor = Exception.class)
	public Brand getEager(int id) throws Exception {
		Brand brand = this.brandDao.get(id);
		Hibernate.initialize(brand.getProducts());
		return brand;
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public List<Brand> getAll() throws Exception {
		return this.brandDao.getAll();
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public List<String> create(Brand brand) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public List<String> update(Brand brand) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public List<Sport> getPossibleSports(int brandId) throws Exception {
		return this.brandDao.getPossibleSports(brandId);
	}

}
