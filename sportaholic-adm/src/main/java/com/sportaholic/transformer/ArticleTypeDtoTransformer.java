package com.sportaholic.transformer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.sportaholic.dto.ArticleTypeDto;
import com.sportaholic.model.ArticleType;
import com.sportaholic.service.ArticleTypeService;

@Component
public class ArticleTypeDtoTransformer {

	private ArticleTypeService articleTypeService;
	
	@Autowired
	public ArticleTypeDtoTransformer(ArticleTypeService articleTypeService) {
		this.articleTypeService = articleTypeService;
	}
	
	public ArticleTypeDto articleTypeToArticleTypeDto(ArticleType articleType) throws Exception {
		ArticleTypeDto articleTypeDto = new ArticleTypeDto();
		articleTypeDto.setId(articleType.getId());
		articleTypeDto.setName(articleType.getName());
		return articleTypeDto;
	}
	
	public ArticleType articleTypeDtoToArticleType(ArticleTypeDto articleTypeDto) throws Exception {
		ArticleType articleType = articleTypeDto.getId() == null ? new ArticleType() : 
			this.articleTypeService.get(articleTypeDto.getId());
		
		articleType.setName(articleTypeDto.getName());
		
		return articleType;
	}
	
}
