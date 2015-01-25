<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<html>
  <head>
    <meta charset="utf-8">
    <link rel='shortcut icon' type='image/x-icon' href='/favicon.ico'/>
    <title>${client.completeName}</title>
    <c:import url="/WEB-INF/jsp/shared/css.jsp" />
    <c:import url="/WEB-INF/jsp/shared/js.jsp" />
  </head>
  <body>
    <c:import url="/WEB-INF/jsp/shared/tags.jsp" />
    
    <c:import url="/WEB-INF/jsp/shared/header.jsp" />
    
    <div class="container theme-showcase" role="main">
    
      <c:import url="/WEB-INF/jsp/shared/alert.jsp" />
      <c:import url="/WEB-INF/jsp/shared/breadcrumb.jsp" />

      <div class="center-form">
        <form class="form-horizontal">
        <div class="form-group row">
          <label for="clientName" class="client-field-name col-sm-6 col-xs-12">Nome</label>
          <div class="col-sm-6 col-xs-12">
		    ${client.completeName}
		  </div>
	    </div>
	    <div class="form-group row">
          <label for="clientBirthDate" class="client-field-name col-sm-6 col-xs-12">Data de nascimento</label>
          <div class="col-sm-6 col-xs-12">
		    <fmt:formatDate value="${client.birthDate}" pattern="dd/MM/yyyy" />
		  </div>
	    </div>
	    <c:if test="${client.gender != null}">
	      <div class="form-group row">
            <label for="clientGender" class="client-field-name col-sm-6 col-xs-12">Sexo</label>
            <div class="col-sm-6 col-xs-12">
		      <c:choose>
		        <c:when test="${client.gender == 'm'}">Masculino</c:when>
		        <c:otherwise>Feminino</c:otherwise>
		      </c:choose>
		    </div>
	      </div>
	    </c:if>
	    <div class="form-group row">
          <label for="clientEmail" class="client-field-name col-sm-6 col-xs-12">E-mail</label>
          <div class="col-sm-6 col-xs-12">
		    ${client.email}
		  </div>
	    </div>
	    <c:if test="${client.email == username}">
	      <div class="form-group row">
	        <div class="col-sm-offset-4 col-sm-8 col-xs-12">
		      <a class="form-control btn btn-warning" href="/clients/${client.id}/edit">Editar</a>
		    </div>
	      </div>
	    </c:if>
		
      </form>
      </div>

    </div>
    
    <c:import url="/WEB-INF/jsp/shared/footer.jsp" />
    
  </body>
</html>