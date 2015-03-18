<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="security" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<html>
  <head>
    <meta charset="utf-8">
    <meta name="description" content="${activeUri.metaDescription}">
    <link rel='shortcut icon' type='image/x-icon' href='/favicon.ico'/>
    <title>Todas as marcas</title>
    <c:import url="/WEB-INF/jsp/shared/css.jsp" />
    <c:import url="/WEB-INF/jsp/shared/js.jsp" />
  </head>
  <body>
    <c:import url="/WEB-INF/jsp/shared/tags.jsp" />
  
    <c:import url="/WEB-INF/jsp/shared/header.jsp" />
    
    <div class="container theme-showcase" role="main">
    
      <c:import url="/WEB-INF/jsp/shared/alert.jsp" />
      <c:import url="/WEB-INF/jsp/shared/breadcrumb.jsp" />

	  <h1 class="page-header">Todas as marcas</h1>
	  
	  <div class="row brands-list">
      <c:forEach var="brand" items="${brands}">
        <div class="col-md-3 col-sm-4 col-xs-12 brand-logo">
          <a href="${uriService.getFriendlyUri(brand.getUri())}">
            <img src="<%=com.sportaholic.EnvironmentConstants.IMAGES_URL%>${brand.logo}" alt="${brand.name}"/>
          </a>
        </div>
      </c:forEach>
      </div>
 
    </div>
    
    <c:import url="/WEB-INF/jsp/shared/footer.jsp" />
    
  </body>
</html>