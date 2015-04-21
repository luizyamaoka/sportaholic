package com.sportaholic.service.impl;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.sportaholic.dao.ProductDao;
import com.sportaholic.dao.ProductIsSportDao;
import com.sportaholic.dao.ProductIsTypeDao;
import com.sportaholic.dao.UriDao;
import com.sportaholic.dto.ProductDto;
import com.sportaholic.model.Product;
import com.sportaholic.model.ProductIsSport;
import com.sportaholic.model.ProductIsType;
import com.sportaholic.model.Uri;
import com.sportaholic.model.UrlConstants;
import com.sportaholic.service.AdmProductService;
import com.sportaholic.service.AmazonS3Service;
import com.sportaholic.transformer.ProductDtoTransformer;

@Component
public class AdmProductServiceImpl extends ProductServiceImpl implements
		AdmProductService {

	private ProductDao productDao;
	private UriDao uriDao;
	private ProductDtoTransformer productDtoTransformer;
	private ProductIsSportDao productIsSportDao;
	private ProductIsTypeDao productIsTypeDao;
	private AmazonS3Service amazonS3Service;
	
	@Autowired
	public AdmProductServiceImpl(ProductDao productDao, UriDao uriDao,
			ProductDtoTransformer productDtoTransformer,
			ProductIsSportDao productIsSportDao, ProductIsTypeDao productIsTypeDao,
			AmazonS3Service amazonS3Service) {
		super(productDao);
		this.productDao = productDao;
		this.uriDao = uriDao;
		this.productDtoTransformer = productDtoTransformer;
		this.productIsSportDao = productIsSportDao;
		this.productIsTypeDao = productIsTypeDao;
		this.amazonS3Service = amazonS3Service;
	}
	
	@Override
	@Transactional(rollbackFor = Exception.class)
	public List<String> create(ProductDto productDto) throws Exception {
		List<String> status = this.testProductDto(productDto);
		
		if (status.get(0).equals("success")) {
			Product product = this.productDtoTransformer.productDtoToProduct(productDto);
			product.setCreatedAt(Calendar.getInstance().getTime());
			product.setUpdatedAt(Calendar.getInstance().getTime());
			Integer productId = this.productDao.create(product);
			
			for (ProductIsSport productIsSport : product.getProductIsSports()) {
				productIsSport.setCreatedAt(Calendar.getInstance().getTime());
				this.productIsSportDao.create(productIsSport);
			}
			
			for (ProductIsType productIsType : product.getProductIsTypes()) {
				productIsType.setCreatedAt(Calendar.getInstance().getTime());
				this.productIsTypeDao.create(productIsType);
			}
			
			Uri uri = this.productDtoTransformer.productDtoToUri(productDto);
			uri.setUri(UrlConstants.URL_PRODUCT + "/" + productId.toString());
			uri.setCreatedAt(Calendar.getInstance().getTime());
			uri.setUpdatedAt(Calendar.getInstance().getTime());
			this.uriDao.create(uri);
			
			if (productDto.getImageFile() != null && !productDto.getImageFile().isEmpty()) {
				this.amazonS3Service.upload(product.getImage(), productDto.getImageFile().getInputStream());
			}
			
			status.add(productId.toString());
		}
		
		return status;
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public List<String> update(ProductDto productDto) throws Exception {
		List<String> status = this.testProductDto(productDto);
		
		if (productDto.getId() == null) {
			status.set(0, "error");
			status.add("productId.required");
		}
		
		if (status.get(0).equals("success")) {
			
			// Delete all sports and product types from product
			Product oldProduct = this.productDao.get(productDto.getId());
			for (ProductIsSport productIsSport : oldProduct.getProductIsSports()) {
				this.productIsSportDao.delete(productIsSport);
			}
			
			for (ProductIsType productIsType : oldProduct.getProductIsTypes()) {
				this.productIsTypeDao.delete(productIsType);
			}
		
			Product product = this.productDtoTransformer.productDtoToProduct(productDto);
			product.setUpdatedAt(Calendar.getInstance().getTime());
			this.productDao.update(product);
			
			for (ProductIsSport productIsSport : product.getProductIsSports()) {
				productIsSport.setCreatedAt(Calendar.getInstance().getTime());
				this.productIsSportDao.create(productIsSport);
			}
			
			for (ProductIsType productIsType : product.getProductIsTypes()) {
				productIsType.setCreatedAt(Calendar.getInstance().getTime());
				this.productIsTypeDao.create(productIsType);
			}
			
			Uri uri = this.productDtoTransformer.productDtoToUri(productDto);
			uri.setUpdatedAt(Calendar.getInstance().getTime());
			this.uriDao.update(uri);
			
			if (productDto.getImageFile() != null && !productDto.getImageFile().isEmpty()) {
				this.amazonS3Service.upload(product.getImage(), productDto.getImageFile().getInputStream());
			}
			
		}
		
		return status;
	}
	
	private List<String> testProductDto(ProductDto productDto) throws Exception {
		List<String> status = new ArrayList<String>();
		status.add("success");
		
		if (productDto.getName() == null || productDto.getName().length() == 0) {
			status.set(0, "error");
			status.add("name.required");
		}
		if (productDto.getDescription() == null || productDto.getDescription().length() == 0) {
			status.set(0, "error");
			status.add("description.required");
		}
		if (productDto.getImage() == null || productDto.getImage().length() == 0) {
			status.set(0, "error");
			status.add("image.required");
		}
		if (productDto.getPrice() == null) {
			status.set(0, "error");
			status.add("price.required");
		}
		if (productDto.getPrice().signum() <= 0) {
			status.set(0, "error");
			status.add("price.negative");
		}
		if (productDto.getIsActive() == null) {
			status.set(0, "error");
			status.add("isActive.required");
		}
		if (productDto.getInStock() == null) {
			status.set(0, "error");
			status.add("inStock.required");
		}
		if (productDto.getBrandId() == null) {
			status.set(0, "error");
			status.add("brandId.required");
		}
		if (productDto.getProductTypeIds() == null || productDto.getProductTypeIds().size() == 0) {
			status.set(0, "error");
			status.add("productIsTypes.required");
		}
		if (productDto.getProductSportIds() == null || productDto.getProductSportIds().size() == 0) {
			status.set(0, "error");
			status.add("productIsSports.required");
		}
		
		if (productDto.getFriendlyUri() == null || productDto.getFriendlyUri().length() == 0) {
			status.set(0, "error");
			status.add("friendlyUri.required");
		}
		if (productDto.getFriendlyUri() != null && !productDto.getFriendlyUri().startsWith("/")) {
			status.set(0, "error");
			status.add("friendlyUri.startsWithSlash");
		}
		if (productDto.getFriendlyUri() != null && productDto.getFriendlyUri().contains(" ")) {
			status.set(0, "error");
			status.add("friendlyUri.containsSpaces");
		}
		if (productDto.getFriendlyUri() != null) {
			Uri uri = this.uriDao.getByFriendlyUri(productDto.getFriendlyUri());
			if (uri != null && uri.getId() != productDto.getUriId()) {
				status.set(0, "error");
				status.add("friendlyUri.existant");
			}
		}
		if (productDto.getUriName() == null || productDto.getUriName().length() == 0) {
			status.set(0, "error");
			status.add("uriName.required");
		}
		if (productDto.getParentId() == null || productDto.getParentId() == 0) {
			status.set(0, "error");
			status.add("parentId.required");
		}
		if (productDto.getMetaDescription() == null || productDto.getMetaDescription().length() == 0) {
			status.set(0, "error");
			status.add("metaDescription.required");
		}
		if (productDto.getMetaDescription() != null && productDto.getMetaDescription().length() > 160) {
			status.set(0, "error");
			status.add("metaDescription.length");
		}
		
		return status;
	}

}
