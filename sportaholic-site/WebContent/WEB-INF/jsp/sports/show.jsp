<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ page import="com.sportaholic.EnvironmentConstants" %>

<html>
  <head>
    <meta charset="utf-8">
    <link rel='shortcut icon' type='image/x-icon' href='/favicon.ico'/>
    <meta name="description" content="${activeUri.metaDescription}">
    <title>${sport.name} | Sportaholic</title>
    <c:import url="/WEB-INF/jsp/shared/css.jsp" />
    <c:import url="/WEB-INF/jsp/shared/js.jsp" />
  </head>
  <body>
    <c:import url="/WEB-INF/jsp/shared/tags.jsp" />
    
    <c:import url="/WEB-INF/jsp/shared/header.jsp" />
    
    <div class="container theme-showcase" role="main">
    
      <c:import url="/WEB-INF/jsp/shared/alert.jsp" />
      <c:import url="/WEB-INF/jsp/shared/breadcrumb.jsp" />
    

      <!-- Wrapper for slides -->
      <div class="carousel-inner" role="listbox" style="margin-bottom: 20px;">
        <div class="item active">
          <img src="<%= EnvironmentConstants.IMAGES_URL %>${sport.banner}" alt="${sport.name}">
          <div class="carousel-caption hidden-xs">
            <h1>${sport.name}</h1>
            <p>${sport.description}</p>
          </div>
        </div>
      </div>
      
      <div class="sport-description visible-xs">
        <h1>${sport.name}</h1>
        <p>${sport.description}</p>
      </div>
    
    <div class="row">

      <div class="col-sm-9 col-sm-push-3">
        <h3 class="page-header">Últimos artigos</h3>
    
          <c:import url="/WEB-INF/jsp/articles/_articles.jsp" />
      
          <nav>
            <ul class="pager">
              <li class="next"><a href="${uriService.getFriendlyUri(sport.articlesUri)}">Ver mais <span aria-hidden="true">&rarr;</span></a></li>
            </ul>
          </nav>
      
          <h3 class="page-header">Produtos mais vendidos</h3>
    
          <c:import url="/WEB-INF/jsp/products/_products.jsp" />
      
          <nav>
            <ul class="pager">
              <li class="next"><a href="${uriService.getFriendlyUri(sport.productsUri)}">Ver mais <span aria-hidden="true">&rarr;</span></a></li>
            </ul>
          </nav>
	  </div>
	  
	  <c:import url="/WEB-INF/jsp/articles/_menu.jsp" />
	  
    </div>

</div>

    
    <c:import url="/WEB-INF/jsp/shared/footer.jsp" />
    
  </body>
</html>