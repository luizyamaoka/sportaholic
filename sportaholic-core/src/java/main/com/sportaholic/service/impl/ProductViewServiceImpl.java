package com.sportaholic.service.impl;

import java.util.Calendar;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.sportaholic.dao.ProductViewDao;
import com.sportaholic.model.ProductView;
import com.sportaholic.service.ProductViewService;

@Component
public class ProductViewServiceImpl implements ProductViewService {

	private ProductViewDao productViewDao;
	
	@Autowired
	public ProductViewServiceImpl(ProductViewDao productViewDao) {
		this.productViewDao = productViewDao;
	}
	
	@Override
	@Transactional(rollbackFor = Exception.class)
	public void create(ProductView productView) {
		try {
			productView.setCreatedAt(Calendar.getInstance().getTime());
			this.productViewDao.create(productView);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
