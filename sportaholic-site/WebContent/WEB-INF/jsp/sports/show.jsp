<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

      <!-- Wrapper for slides -->
      <div class="carousel-inner" role="listbox" style="margin-bottom: 20px;">
        <div class="item active">
          <img src="<%= com.sportaholic.EnvironmentConstants.IMAGES_URL %>${sport.banner}" alt="${sport.name}">
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
	  
	  <div class="col-sm-3 col-sm-pull-9">
        <h4 class="page-header">Leia mais</h4>
	    <c:import url="/WEB-INF/jsp/articles/_menu.jsp" />
	  </div>
	  
    </div>