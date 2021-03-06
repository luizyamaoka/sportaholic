<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<div class="center-form">
  <form:form class="form-horizontal" modelAttribute="uriDto">
    
    <div class="form-group">
      <label for="uriInput" class="control-label col-sm-4 col-xs-12">Url * </label>
      <div class="col-sm-8 col-xs-12">
		<form:input path="uri" class="form-control" id="uriInput" placeholder="Url" required="true" />
      </div>
	</div>
    <div class="form-group">
      <label for="friendlyUriInput" class="control-label col-sm-4 col-xs-12">Url amig�vel * </label>
      <div class="col-sm-8 col-xs-12">
		<form:input path="friendlyUri" class="form-control" id="friendlyUriInput" placeholder="Url amig�vel" required="true" />
      </div>
	</div>
	<div class="form-group">
      <label for="uriNameInput" class="control-label col-sm-4 col-xs-12">Nome da p�gina * </label>
      <div class="col-sm-8 col-xs-12">
	    <form:input path="uriName" class="form-control" id="uriNameInput" placeholder="Nome da p�gina (breadcrumbs)" required="true" />
	  </div>
	</div>
	<div class="form-group">
      <label for="parentIdOptions" class="control-label col-sm-4 col-xs-12">Url pai </label>
      <div class="col-sm-8 col-xs-12">
	    <form:select path="parentId" class="form-control" id="parentIdOptions">
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