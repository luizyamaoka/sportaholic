<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
  <head>
    <meta charset="utf-8">
    <meta name="description" content="${activeUri.metaDescription}">
    <link rel='shortcut icon' type='image/x-icon' href='/favicon.ico'/>
    <title>Sportaholic | Viciados em esportes</title>
    <c:import url="/WEB-INF/jsp/shared/js.jsp" />
    <c:import url="/WEB-INF/jsp/shared/css.jsp" />
  </head>
  <body>
    <c:import url="/WEB-INF/jsp/shared/tags.jsp" />
    
    <c:import url="/WEB-INF/jsp/shared/header.jsp" />
    
    <div class="container theme-showcase" role="main">
    
    <c:import url="/WEB-INF/jsp/shared/alert.jsp" />
    <c:import url="/WEB-INF/jsp/shared/breadcrumb.jsp" />

      <!-- Main jumbotron for a primary marketing message or call to action -->
      <div class="jumbotron" style="text-align: center">
        <h1 style="margin-bottom: 40px;">
          <img src="https://s3-sa-east-1.amazonaws.com/sportaholic/images/logo.png" style="max-height: 70px; max-width: 80%;">
        </h1>
        
        <p>Onde se encontram os atletas, praticantes, entusiastas, aficionados, amantes do esporte.</p>
        <p>Os melhores artigos, as últimas novidades e curiosidades do mundo do esporte.</p>
      </div>
    
      <h3 class="page-header">Últimos artigos</h3>
    
      <c:import url="/WEB-INF/jsp/articles/_articles.jsp" />
      
      <nav>
        <ul class="pager">
          <li class="next"><a href="${uriService.getFriendlyUri('/articles')}">Ver mais <span aria-hidden="true">&rarr;</span></a></li>
        </ul>
      </nav>
      
      <h3 class="page-header">Produtos mais vendidos</h3>
    
      <c:import url="/WEB-INF/jsp/products/_products.jsp" />
      
      <nav>
        <ul class="pager">
          <li class="next"><a href="${uriService.getFriendlyUri('/products')}">Ver mais <span aria-hidden="true">&rarr;</span></a></li>
        </ul>
      </nav>
    
    </div>
    
    <c:import url="/WEB-INF/jsp/shared/footer.jsp" />
    
  </body>
</html>