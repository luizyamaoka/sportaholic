<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<div class="center-form">
  <form:form class="form-horizontal" modelAttribute="brandDto">
    <div class="form-group">
      <label for="nameInput" class="control-label col-sm-4 col-xs-12">Nome * </label>
      <div class="col-sm-8 col-xs-12">
        <form:input path="name" class="form-control" id="nameInput" placeholder="Nome" required="true" autofocus="true" />
      </div>
    </div>
    <div class="form-group">
      <label for="logoInput" class="control-label col-sm-4 col-xs-12">Logo * </label>
      <div class="col-sm-8 col-xs-12">
        <form:input path="logo" class="form-control" id="logoInput" placeholder="Logo" required="true" />
      </div>
    </div>
    <div class="form-group">
      <label for="descriptionInput" class="control-label col-sm-4 col-xs-12">Descrição * </label>
      <div class="col-sm-8 col-xs-12">
        <form:textarea rows="10" path="description" class="form-control" id="descriptionInput" placeholder="Descrição em HTML, usar tags p, strong, a, ul, li" required="true" />
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