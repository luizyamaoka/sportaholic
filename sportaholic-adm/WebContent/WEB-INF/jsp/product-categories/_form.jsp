<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<div class="center-form">
  <form:form class="form-horizontal" modelAttribute="productCategoryDto">
    <div class="form-group">
      <label for="nameInput" class="control-label col-sm-4 col-xs-12">Nome * </label>
      <div class="col-sm-8 col-xs-12">
        <form:input path="name" class="form-control" id="nameInput" placeholder="Nome" required="true" autofocus="true" />
      </div>
    </div>
    <div class="form-group">
            <label for="sportIdOptions" class="control-label col-sm-4 col-xs-12">Esporte * </label>
            <div class="col-sm-8 col-xs-12">
			  <form:select path="sportId" class="form-control" id="sportIdOptions" required="true">
			    <form:option value="">Selecione um esporte</form:option>
			    <c:forEach var="sport" items="${sports}">
			      <form:option value="${sport.id}">${sport.name}</form:option>
			    </c:forEach>
		      </form:select>
			</div>
		  </div>
    <div class="form-group">
      <div class="col-sm-offset-4 col-sm-8 col-xs-12">
        <input class="form-control btn btn-primary" type="submit" value="Atualizar" />
      </div>
    </div>
  </form:form>
</div>