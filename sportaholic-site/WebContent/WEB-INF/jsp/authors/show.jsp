<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="security" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<html>
  <head>
    <meta charset="utf-8">
    <meta name="description" content="${activeUri.metaDescription}">
    <link rel='shortcut icon' type='image/x-icon' href='/favicon.ico'/>
    <title>${author.name}</title>
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

      <div class="col-sm-9 col-sm-push-3">
        <div class="author-page">
	      <h1>${author.name}</h1>
	      <section class="article">
	        ${author.description}
	      </section>
        </div>
	    
	    <h4 class="page-header">Últimas publicações</h4>
	    <c:import url="/WEB-INF/jsp/articles/_articles.jsp" />
	  </div>
	  
	  <div class="col-sm-3 col-sm-pull-9">
        <h4 class="page-header">Autores</h4>
	    <c:import url="/WEB-INF/jsp/authors/_menu.jsp" />
	  </div>
	  
    </div>
    </div>
    
    <c:import url="/WEB-INF/jsp/shared/footer.jsp" />
    
  </body>
</html>