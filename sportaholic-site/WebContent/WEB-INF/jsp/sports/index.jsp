<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ page import="com.sportaholic.EnvironmentConstants" %>

<html>
  <head>
    <meta charset="utf-8">
    <link rel='shortcut icon' type='image/x-icon' href='/favicon.ico'/>
    <meta name="description" content="${activeUri.metaDescription}">
    <title>Todos os esportes | Sportaholic</title>
    <c:import url="/WEB-INF/jsp/shared/css.jsp" />
    <c:import url="/WEB-INF/jsp/shared/js.jsp" />
  </head>
  <body>
    <c:import url="/WEB-INF/jsp/shared/tags.jsp" />
    
    <c:import url="/WEB-INF/jsp/shared/header.jsp" />
    
    <div class="container theme-showcase" role="main">
    
      <c:import url="/WEB-INF/jsp/shared/alert.jsp" />
      <c:import url="/WEB-INF/jsp/shared/breadcrumb.jsp" />
    

	  <h1 class="page-header">Todos os esportes</h1>
	  
	  <p class="lead">Veja nesta seção todos os esportes que a Sportaholic trata no momento. Estamos trabalhando para trazer mais artigos e produtos de outros esportes especialmente para você. </p>
	  
	  <div class="list-group">
	  <c:forEach var="sport" items="${sports}">
	    <a href="${uriService.getFriendlyUri(sport.getUri())}" class="list-group-item">
          <h4 class="list-group-item-heading">${sport.name}</h4>
          <p class="list-group-item-text">${sport.description}</p>
        </a>
	  </c:forEach>
	  </div>

      <p>Não encontrou o esporte que estava procurando? Mande um e-mail para <a href="mailto:sportaholicoficial@gmail.com">sportaholicoficial@gmail.com</a> com a sua sugestão de esporte.</p>

</div>

    
    <c:import url="/WEB-INF/jsp/shared/footer.jsp" />
    
  </body>
</html>