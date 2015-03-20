<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ page import="com.sportaholic.EnvironmentConstants" %>

<html>
  <head>
    <meta charset="utf-8">
    <link rel='shortcut icon' type='image/x-icon' href='/favicon.ico'/>
    <meta name="description" content="${activeUri.metaDescription}">
    <title>${product.name} | Sportaholic</title>
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
        <div class="col-sm-6 col-xs-12 product-main-image">
          <img src="<%=com.sportaholic.EnvironmentConstants.IMAGES_URL%>${product.image}" alt="${product.name}" style="max-width: 100%; max-height: 100%;" />
        </div>
        <div class="col-sm-6 col-xs-12 product-description">
          <h2>${product.brand.name}</h2>
          <h1>${product.name}</h1>
          <p>${product.description}</p>
          <p>
            R$ <fmt:formatNumber value="${product.price}" type="number" pattern="#,##0.00" minFractionDigits="2" maxFractionDigits="2"/>
          </p>
          <p style="margin-top: 50px;"><a href="${uriService.getFriendlyUri(product.uri)}" class="btn-big" role="button">Comprar</a></p>
        </div>
      </div>
    
      <c:if test="${not empty products}">
      <h3 class="page-header">Você também pode gostar</h3>
      <div class="row">
        <c:import url="/WEB-INF/jsp/products/_products.jsp" />
      </div>
      </c:if>

</div>

    
    <c:import url="/WEB-INF/jsp/shared/footer.jsp" />
    
  </body>
</html>