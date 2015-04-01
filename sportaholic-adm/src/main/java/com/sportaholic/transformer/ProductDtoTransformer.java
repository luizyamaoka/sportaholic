package com.sportaholic.transformer;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.sportaholic.dao.BrandDao;
import com.sportaholic.dao.ProductDao;
import com.sportaholic.dao.ProductTypeDao;
import com.sportaholic.dao.SportDao;
import com.sportaholic.dao.UriDao;
import com.sportaholic.dto.ProductDto;
import com.sportaholic.model.Product;
import com.sportaholic.model.ProductIsSport;
import com.sportaholic.model.ProductIsType;
import com.sportaholic.model.Uri;
import com.sportaholic.model.UrlConstants;

@Component
public class ProductDtoTransformer {

	private ProductDao productDao;
	private UriDao uriDao;
	private BrandDao brandDao;
	private SportDao sportDao;
	private ProductTypeDao productTypeDao;
	
	@Autowired
	public ProductDtoTransformer(ProductDao productDao, UriDao uriDao,
			BrandDao brandDao, SportDao sportDao, ProductTypeDao productTypeDao) {
		this.productDao = productDao;
		this.uriDao = uriDao;
		this.brandDao = brandDao;
		this.sportDao = sportDao;
		this.productTypeDao = productTypeDao;
	}
	
	@Transactional(rollbackFor = Exception.class)
	public Product productDtoToProduct(ProductDto productDto) throws Exception {
		Product product = productDto.getId() == null ? new Product() : this.productDao.get(productDto.getId());
		
		product.setName(productDto.getName());
		product.setDescription(productDto.getDescription());
		product.setImage(productDto.getImage());
		product.setPrice(productDto.getPrice());
		product.setIsActive(productDto.getIsActive());
		product.setInStock(productDto.getInStock());
		product.setBrand(this.brandDao.get(productDto.getBrandId()));
		product.setMeliUrl(productDto.getMeliUrl());
		
		List<ProductIsType> productIsTypes = new ArrayList<ProductIsType>();
		for (Integer productTypeId : productDto.getProductTypeIds()) {
			ProductIsType productIsType = new ProductIsType();
			productIsType.setProduct(product);
			productIsType.setProductType(this.productTypeDao.get(productTypeId));
			productIsTypes.add(productIsType);
		}
		product.setProductIsTypes(productIsTypes);
		
		List<ProductIsSport> productIsSports = new ArrayList<ProductIsSport>();
		for (Integer sportId : productDto.getProductSportIds()) {
			ProductIsSport productIsSport = new ProductIsSport();
			productIsSport.setProduct(product);
			productIsSport.setSport(this.sportDao.get(sportId));
			productIsSports.add(productIsSport);
		}
		product.setProductIsSports(productIsSports);
		
		return product;
	}
	
	@Transactional(rollbackFor = Exception.class)
	public Uri productDtoToUri(ProductDto productDto) throws Exception {
		Uri uri = productDto.getUriId() == null ? new Uri() : this.uriDao.get(productDto.getUriId());
		
		uri.setFriendlyUri(productDto.getFriendlyUri());
		uri.setName(productDto.getUriName());
		uri.setMetaDescription(productDto.getMetaDescription());
		uri.setParent(this.uriDao.get(productDto.getParentId()));
		
		return uri;
	}
	
	@Transactional(rollbackFor = Exception.class)
	public ProductDto productToProductDto(Product product) throws Exception {
		
		ProductDto productDto = new ProductDto();
		
		productDto.setId(product.getId());
		productDto.setName(product.getName());
		productDto.setDescription(product.getDescription());
		productDto.setImage(product.getImage());
		productDto.setPrice(product.getPrice());
		productDto.setIsActive(product.getIsActive());
		productDto.setInStock(product.getInStock());
		productDto.setBrandId(product.getBrand().getId());
		productDto.setMeliUrl(product.getMeliUrl());
		
		List<Integer> productTypeIds = new ArrayList<Integer>();
		for (ProductIsType productIsType : product.getProductIsTypes()) {
			productTypeIds.add(productIsType.getProductType().getId());
		}
		productDto.setProductTypeIds(productTypeIds);
		
		List<Integer> productSportIds = new ArrayList<Integer>();
		for (ProductIsSport productIsSport : product.getProductIsSports()) {
			productSportIds.add(productIsSport.getSport().getId());
		}
		productDto.setProductSportIds(productSportIds);
		
		Uri uri = this.uriDao.getByUri(UrlConstants.URL_PRODUCT + "/" + product.getId());
		
		if (uri != null) {
			productDto.setUriId(uri.getId());
			productDto.setFriendlyUri(uri.getFriendlyUri());
			productDto.setUriName(uri.getName());
			productDto.setParentId(uri.getParent().getId());
			productDto.setMetaDescription(uri.getMetaDescription());
		}
		
		return productDto;
	}
	
	
}
