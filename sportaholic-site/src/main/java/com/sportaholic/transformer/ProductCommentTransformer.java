package com.sportaholic.transformer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.sportaholic.dao.ClientDao;
import com.sportaholic.dao.ProductCommentDao;
import com.sportaholic.dao.ProductDao;
import com.sportaholic.dto.ProductCommentDto;
import com.sportaholic.model.ProductComment;

@Component
public class ProductCommentTransformer {

	private ProductCommentDao productCommentDao;
	private ProductDao productDao;
	private ClientDao clientDao;
	
	@Autowired
	public ProductCommentTransformer(ProductCommentDao productCommentDao,
			ProductDao productDao, ClientDao clientDao) {
		this.productCommentDao = productCommentDao;
		this.productDao = productDao;
		this.clientDao = clientDao;
	}
	
	@Transactional(rollbackFor = Exception.class)
	public ProductComment productCommentDtoToProductComment(ProductCommentDto productCommentDto) throws Exception {
		
		ProductComment productComment = productCommentDto.getId() == null ? new ProductComment() : this.productCommentDao.get(productCommentDto.getId());
		
		productComment.setGrade(productCommentDto.getGrade());
		productComment.setContent(productCommentDto.getContent());
		productComment.setProduct(productCommentDto.getProductId() == null ? null : this.productDao.get(productCommentDto.getProductId()));
		productComment.setClient(productCommentDto.getClientId() == null ? null : this.clientDao.get(productCommentDto.getClientId()));
		
		return productComment;
		
	}
	
}
