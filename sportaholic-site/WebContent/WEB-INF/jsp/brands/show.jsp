<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
    
<div class="row">
  <div class="col-sm-3 col-xs-12 brand-logo">
    <a href="${uriService.getFriendlyUri(brand.getUri())}">
      <img src="<%=com.sportaholic.EnvironmentConstants.IMAGES_URL%>${brand.logo}">
    </a>
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