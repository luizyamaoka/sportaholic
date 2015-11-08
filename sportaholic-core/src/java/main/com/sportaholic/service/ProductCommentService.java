package com.sportaholic.service;

import java.util.List;

import com.sportaholic.model.ProductComment;

public interface ProductCommentService {

	public List<String> create(ProductComment productComment) throws Exception;
	
}
