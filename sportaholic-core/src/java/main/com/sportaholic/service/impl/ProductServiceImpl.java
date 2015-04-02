package com.sportaholic.service.impl;

import java.util.List;

import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

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
	@Transactional(rollbackFor = Exception.class)
	public Product get(Integer id) throws Exception {
		return this.productDao.get(id);
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public List<Product> getAll() throws Exception {
		return this.productDao.getAll();
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public List<String> create(Product product) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public List<String> update(Product product) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public List<Product> getBySet(Integer brandId, Integer sportId,
			Integer productCategoryId, Integer productTypeId) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public Product getEager(Integer id) throws Exception {
		Product product = this.productDao.get(id);
		Hibernate.initialize(product.getProductIsSports());
		Hibernate.initialize(product.getProductIsTypes());
		Hibernate.initialize(product.getProductComments());
		Hibernate.initialize(product.getBrand().getProducts());
		return product;
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public List<Product> getActiveBySetPaginated(Integer sportId, Integer productTypeId, 
			int pageNumber, int pageSize) throws Exception {
		return this.productDao.getActiveBySetPaginated(sportId, productTypeId, pageNumber, pageSize);
	}

}
