<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ page import="com.sportaholic.EnvironmentConstants" %>

<html>
  <head>
    <meta charset="utf-8">
    <link rel='shortcut icon' type='image/x-icon' href='/favicon.ico'/>
    <meta name="description" content="${activeUri.metaDescription}">
    <title>${brand.name} | Sportaholic</title>
    <c:import url="/WEB-INF/jsp/shared/css.jsp" />
    <c:import url="/WEB-INF/jsp/shared/js.jsp" />
  </head>
  <body>
    <c:import url="/WEB-INF/jsp/shared/tags.jsp" />
    
    <c:import url="/WEB-INF/jsp/shared/header.jsp" />
    
    <div class="container theme-showcase" role="main">
    
      <c:import url="/WEB-INF/jsp/shared/alert.jsp" />
      <c:import url="/WEB-INF/jsp/shared/breadcrumb.jsp" />
    
      <div class="row">
        <div class="col-sm-3 col-xs-12">
          <img id="brand-logo" src="http://placehold.it/200x100">
        </div>
        <div id="brand-info" class="col-sm-9 col-xs-12">
          <h1>${brand.name}</h1>
          <p>${brand.description}</p>
        </div>
      </div>

      <div class="row">
      
        <div class="col-sm-9 col-sm-push-3">
          <h3 class="page-header">Mais vendidos</h3>
    
          <c:import url="/WEB-INF/jsp/products/_products.jsp" />
        </div>
      
        <div class="col-sm-3 col-sm-pull-9">
  		  <h4 class="page-header">Esportes da marca</h4>
          <div class="list-group">
            <c:forEach var="sport" items="${sports}">
              <a href="${uriService.getFriendlyUri(sport.getUri())}" class="list-group-item">
                ${sport.name}
              </a>
    		</c:forEach>
          </div>
        </div>
      </div>

</div>

    
    <c:import url="/WEB-INF/jsp/shared/footer.jsp" />
    
  </body>
</html>