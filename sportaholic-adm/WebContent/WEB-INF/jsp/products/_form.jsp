<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<div class="center-form">
        <form:form class="form-horizontal" modelAttribute="productDto">
          <div class="form-group">
            <label for="nameInput" class="control-label col-sm-4 col-xs-12">Nome * </label>
            <div class="col-sm-8 col-xs-12">
			  <form:input path="name" class="form-control" id="nameInput" placeholder="Nome" required="true" autofocus="true" />
			</div>
		  </div>
		  <div class="form-group">
            <label for="descriptionInput" class="control-label col-sm-4 col-xs-12">Descrição * </label>
            <div class="col-sm-8 col-xs-12">
			  <form:textarea rows="5" path="description" class="form-control" id="descriptionInput" placeholder="Descrição em HTML, usar tags p, strong, a, ul, li" required="true" />
			</div>
		  </div>
		  <div class="form-group">
            <label for="imageInput" class="control-label col-sm-4 col-xs-12">Imagem * </label>
            <div class="col-sm-8 col-xs-12">
			  <form:input path="image" class="form-control" id="imageInput" placeholder="Imagem" required="true" />
			</div>
		  </div>
		  <div class="form-group">
            <label for="priceInput" class="control-label col-sm-4 col-xs-12">Preço * </label>
            <div class="col-sm-8 col-xs-12">
			  <form:input path="price" class="form-control" id="priceInput" placeholder="Preço" required="true" />
			</div>
		  </div>
		  <div class="form-group">
            <label for="isActiveInput" class="control-label col-sm-4 col-xs-12">Ativo * </label>
            <div class="col-sm-8 col-xs-12">
			  <form:select path="isActive" class="form-control" id="isActiveOptions" required="true">
			    <form:option value="">Status do produto</form:option>
			    <form:option value="0">Inativo</form:option>
			    <form:option value="1">Ativo</form:option>
		      </form:select>
			</div>
		  </div>
		  <div class="form-group">
            <label for="inStockInput" class="control-label col-sm-4 col-xs-12">Itens em estoque * </label>
            <div class="col-sm-8 col-xs-12">
			  <form:input path="inStock" class="form-control" id="inStockInput" placeholder="Quantidade de itens em estoque" required="true" />
			</div>
		  </div>
		  <div class="form-group">
            <label for="brandIdOptions" class="control-label col-sm-4 col-xs-12">Marca * </label>
            <div class="col-sm-8 col-xs-12">
			  <form:select path="brandId" class="form-control" id="authorIdOptions" required="true">
			    <form:option value="">Selecione uma marca</form:option>
			    <c:forEach var="brand" items="${brands}">
			      <form:option value="${brand.id}">${brand.name}</form:option>
			    </c:forEach>
		      </form:select>
			</div>
		  </div>
		  <div class="form-group">
            <label for="productSportIdsCheckbox" class="control-label col-sm-4 col-xs-12">Esportes * </label>
            <div class="col-sm-8 col-xs-12">
			  <c:forEach var="sport" items="${sports}">
			    <form:checkbox path="productSportIds" value="${sport.id}" />${sport.name}
			  </c:forEach>
			</div>
		  </div>
		  <div class="form-group">
            <label for="productTypeIdsCheckbox" class="control-label col-sm-4 col-xs-12">Tipos de produtos * </label>
            <div class="col-sm-8 col-xs-12">
			  <c:forEach var="productType" items="${productTypes}">
			    <form:checkbox path="productTypeIds" value="${productType.id}" />${productType.productCategory.sport.name} - ${productType.productCategory.name} - ${productType.name}
			  </c:forEach>
			</div>
		  </div>
		  <div class="form-group">
            <label for="meliUrlInput" class="control-label col-sm-4 col-xs-12">Url Mercado Livre * </label>
            <div class="col-sm-8 col-xs-12">
			  <form:input path="meliUrl" class="form-control" id="meliUrlInput" placeholder="Url Mercado Livre" />
			</div>
		  </div>
	      <form:hidden path="uriId" class="form-control" id="uriIdInput"  />
		  <div class="form-group">
            <label for="friendlyUriInput" class="control-label col-sm-4 col-xs-12">Url amigável * </label>
            <div class="col-sm-8 col-xs-12">
			  <form:input path="friendlyUri" class="form-control" id="friendlyUriInput" placeholder="Url amigável" required="true" />
			</div>
		  </div>
		  <div class="form-group">
            <label for="uriNameInput" class="control-label col-sm-4 col-xs-12">Nome da página * </label>
            <div class="col-sm-8 col-xs-12">
			  <form:input path="uriName" class="form-control" id="uriNameInput" placeholder="Nome da página (breadcrumbs)" required="true" />
			</div>
		  </div>
		  <div class="form-group">
            <label for="parentIdOptions" class="control-label col-sm-4 col-xs-12">Url pai * </label>
            <div class="col-sm-8 col-xs-12">
			  <form:select path="parentId" class="form-control" id="parentIdOptions" required="true">
			    <form:option value="">Selecione uma Url pai</form:option>
			    <c:forEach var="uri" items="${uris}">
			      <form:option value="${uri.id}">${uri.friendlyUri}</form:option>
			    </c:forEach>
		      </form:select>
			</div>
		  </div>
		  <div class="form-group">
            <label for="metaDescriptionInput" class="control-label col-sm-4 col-xs-12">Meta Description * </label>
            <div class="col-sm-8 col-xs-12">
			  <form:textarea rows="3" path="metaDescription" class="form-control" id="metaDescriptionInput" placeholder="Meta Description" required="true" />
			</div>
		  </div>
		  <div class="form-group">
		    <div class="col-sm-offset-4 col-sm-8 col-xs-12">
              <input class="form-control btn btn-primary" type="submit" value="Atualizar" />
            </div>
		  </div>
		
      </form:form>
      </div>