<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
  <head>
    <meta charset="utf-8">
    <link rel='shortcut icon' type='image/x-icon' href='/favicon.ico'/>
    <title>Tipos de artigo</title>
    <c:import url="/WEB-INF/jsp/shared/js.jsp" />
    <c:import url="/WEB-INF/jsp/shared/css.jsp" />
  </head>
  <body>    
    <c:import url="/WEB-INF/jsp/shared/header.jsp" />
    
    <div class="container theme-showcase" role="main">
    
    <c:import url="/WEB-INF/jsp/shared/alert.jsp" />

	<div class="page-header">
      <h1>Tipos de artigo</h1>
    </div>

    <p>
      <a href="/article-types/new" class="btn btn-success btn-sm">Novo tipo de artigo</a>
    </p>

      <c:forEach var="articleType" items="${articleTypes}">
        <p>
          <a href="/article-types/${articleType.id}" class="btn btn-warning btn-xs">Editar</a>
          ${articleType.name}
        </p>
      </c:forEach>
            
    </div>
    
    <c:import url="/WEB-INF/jsp/shared/footer.jsp" />
    
  </body>
</html>