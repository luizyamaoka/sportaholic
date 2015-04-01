package com.sportaholic.transformer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.sportaholic.dao.AuthorDao;
import com.sportaholic.dao.UriDao;
import com.sportaholic.dto.AuthorDto;
import com.sportaholic.model.Author;
import com.sportaholic.model.Uri;
import com.sportaholic.model.UrlConstants;

@Component
public class AuthorDtoTransformer {

	private AuthorDao authorDao;
	private UriDao uriDao;
	
	@Autowired
	public AuthorDtoTransformer(AuthorDao authorDao, UriDao uriDao) {
		this.authorDao = authorDao;
		this.uriDao = uriDao;
	}
	
	@Transactional(rollbackFor = Exception.class)
	public Author authorDtoToAuthor(AuthorDto authorDto) throws Exception {
		Author author = authorDto.getId() == null ? new Author() : this.authorDao.get(authorDto.getId()); 
		
		author.setId(authorDto.getId());
		author.setName(authorDto.getName());
		author.setDescription(authorDto.getDescription());
		
		return author;
	}
	
	@Transactional(rollbackFor = Exception.class)
	public Uri authorDtoToUri(AuthorDto authorDto) throws Exception {
		Uri uri = authorDto.getUriId() == null ? new Uri() : this.uriDao.get(authorDto.getUriId());
		
		uri.setFriendlyUri(authorDto.getFriendlyUri());
		uri.setName(authorDto.getUriName());
		uri.setMetaDescription(authorDto.getMetaDescription());
		uri.setParent(this.uriDao.get(authorDto.getParentId()));
		
		return uri;
	}
	
	@Transactional(rollbackFor = Exception.class)
	public AuthorDto authorToAuthorDto(Author author) throws Exception {
		AuthorDto authorDto = new AuthorDto();
		
		authorDto.setId(author.getId());
		authorDto.setName(author.getName());
		authorDto.setDescription(author.getDescription());
		
		Uri uri = this.uriDao.getByUri(UrlConstants.URL_AUTHOR + "/" + author.getId());
		
		if (uri != null) {
			authorDto.setUriId(uri.getId());
			authorDto.setFriendlyUri(uri.getFriendlyUri());
			authorDto.setUriName(uri.getName());
			authorDto.setParentId(uri.getParent().getId());
			authorDto.setMetaDescription(uri.getMetaDescription());
		}
		
		return authorDto;
	}
	
}
