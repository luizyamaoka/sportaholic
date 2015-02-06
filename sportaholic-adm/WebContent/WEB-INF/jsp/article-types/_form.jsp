<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<div class="center-form">
  <form:form class="form-horizontal" modelAttribute="articleTypeDto">
    <div class="form-group">
      <label for="nameInput" class="control-label col-sm-4 col-xs-12">Nome * </label>
      <div class="col-sm-8 col-xs-12">
        <form:input path="name" class="form-control" id="nameInput" placeholder="Nome" required="true" autofocus="true" />
      </div>
    </div>
    <div class="form-group">
      <div class="col-sm-offset-4 col-sm-8 col-xs-12">
        <input class="form-control btn btn-primary" type="submit" value="Atualizar" />
      </div>
    </div>
  </form:form>
</div>