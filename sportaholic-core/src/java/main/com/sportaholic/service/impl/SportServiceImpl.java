package com.sportaholic.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.sportaholic.dao.SportDao;
import com.sportaholic.model.ProductCategory;
import com.sportaholic.model.Sport;
import com.sportaholic.service.SportService;

@Component
public class SportServiceImpl implements SportService {

	private SportDao sportDao;
	
	@Autowired
	public SportServiceImpl(SportDao sportDao) {
		this.sportDao = sportDao;
	}
	
	@Override
	@Transactional(rollbackFor = Exception.class)
	public Sport get(int id) throws Exception {
		return this.sportDao.get(id);
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public List<Sport> getAll() throws Exception {
		return this.sportDao.getAll();
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public List<Sport> getEagerBySet(Integer sportId) throws Exception {
		List<Sport> sports = new ArrayList<Sport>();
		if (sportId == null) {
			sports.addAll(this.sportDao.getAll());
		} else {
			sports.add(this.sportDao.get(sportId));
		}
		for (Sport sport : sports) {
			for (ProductCategory productCategory : sport.getProductCategories()) {
				Hibernate.initialize(productCategory.getProductTypes());
			}
		}
		return sports;
	}

}
