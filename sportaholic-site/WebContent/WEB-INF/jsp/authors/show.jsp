<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<div class="row">
  <div class="col-sm-9 col-sm-push-3">
    <div class="author-page">
	  <h1>${author.name}</h1>
	  <section class="article">
	    ${author.description}
	  </section>
    </div>
	    
	<h4 class="page-header">�ltimas publica��es</h4>
	<c:import url="/WEB-INF/jsp/articles/_articles.jsp" />
  </div>
	  
  <div class="col-sm-3 col-sm-pull-9">
    <h4 class="page-header">Autores</h4>
	<c:import url="/WEB-INF/jsp/authors/_menu.jsp" />
  </div>
</div>