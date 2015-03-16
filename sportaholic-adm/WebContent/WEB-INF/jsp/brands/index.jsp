<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
  <head>
    <meta charset="utf-8">
    <link rel='shortcut icon' type='image/x-icon' href='/favicon.ico'/>
    <title>Marcas</title>
    <c:import url="/WEB-INF/jsp/shared/js.jsp" />
    <c:import url="/WEB-INF/jsp/shared/css.jsp" />
  </head>
  <body>    
    <c:import url="/WEB-INF/jsp/shared/header.jsp" />
    
    <div class="container theme-showcase" role="main">
    
    <c:import url="/WEB-INF/jsp/shared/alert.jsp" />

	<div class="page-header">
      <h1>Marcas</h1>
    </div>

    <p>
      <a href="/brands/new" class="btn btn-success btn-sm">Nova marca</a>
    </p>

      <c:forEach var="brand" items="${brands}">
        <p>
          <a href="/brands/${brand.id}" class="btn btn-warning btn-xs">Editar</a>
          ${brand.name}
        </p>
      </c:forEach>
            
    </div>
    
    <c:import url="/WEB-INF/jsp/shared/footer.jsp" />
    
  </body>
</html>