package com.sportaholic.service.impl;

import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.sportaholic.dao.ProductDao;
import com.sportaholic.model.Product;
import com.sportaholic.service.ProductService;

@Component
public class ProductServiceImpl implements ProductService {

	private ProductDao productDao;
	
	@Autowired
	public ProductServiceImpl(ProductDao productDao) {
		this.productDao = productDao;
	}
	
	@Override
	@Transactional
	public List<Product> getAll() throws Exception {
		return this.productDao.getAll();
	}

	@Override
	@Transactional
	public Product get(Integer id) throws Exception {
		return this.productDao.get(id);
	}

	@Override
	@Transactional
	public Product getEager(Integer id) throws Exception {
		Product product = this.productDao.get(id);
		Hibernate.initialize(product.getProductIsSports());
		Hibernate.initialize(product.getProductIsTypes());
		return product;
	}

}
