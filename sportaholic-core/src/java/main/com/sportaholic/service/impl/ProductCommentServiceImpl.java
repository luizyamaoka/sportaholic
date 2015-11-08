package com.sportaholic.service.impl;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.sportaholic.dao.ProductCommentDao;
import com.sportaholic.model.ProductComment;
import com.sportaholic.service.ProductCommentService;

@Component
public class ProductCommentServiceImpl implements ProductCommentService {

	private ProductCommentDao productCommentDao;
	
	@Autowired
	public ProductCommentServiceImpl(ProductCommentDao productCommentDao) {
		this.productCommentDao = productCommentDao;
	}
	
	@Override
	@Transactional(rollbackFor = Exception.class)
	public List<String> create(ProductComment productComment) throws Exception {
		List<String> status = this.testProductComment(productComment);
		
		if (status.get(0).equals("success")) {
			productComment.setCreatedAt(Calendar.getInstance().getTime());
			productComment.setUpdatedAt(Calendar.getInstance().getTime());
			Integer id = this.productCommentDao.create(productComment);
			status.add(id.toString());
		}
		
		return status;
	}

	public List<String> testProductComment(ProductComment productComment) throws Exception {
		List<String> status = new ArrayList<String>();
		status.add("success");
		
		if (productComment.getClient() == null) {
			status.set(0, "error");
			status.add("client.required");
		}
		if (productComment.getProduct() == null) {
			status.set(0, "error");
			status.add("product.required");
		}
		if (productComment.getGrade() == null) {
			status.set(0, "error");
			status.add("grade.required");
		}
		else {
			if (productComment.getGrade() < 0 || productComment.getGrade() > 100) {
				status.set(0, "error");
				status.add("grade.invalid");
			}
		}
		if (productComment.getContent() == null || productComment.getContent().equals("")) {
			status.set(0, "error");
			status.add("content.required");
		}
		
		return status;
	}
	
}
