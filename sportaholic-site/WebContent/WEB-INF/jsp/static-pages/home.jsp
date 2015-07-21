<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<div class="jumbotron" style="text-align: center">
  <h1 style="margin-bottom: 40px;">
    <img src="<%=com.sportaholic.EnvironmentConstants.IMAGES_URL%>logo.png" style="max-height: 70px; max-width: 80%;" alt="Sportaholic">
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
    