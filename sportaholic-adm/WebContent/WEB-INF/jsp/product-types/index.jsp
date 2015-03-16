<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
  <head>
    <meta charset="utf-8">
    <link rel='shortcut icon' type='image/x-icon' href='/favicon.ico'/>
    <title>Tipos de categorias de produtos</title>
    <c:import url="/WEB-INF/jsp/shared/js.jsp" />
    <c:import url="/WEB-INF/jsp/shared/css.jsp" />
  </head>
  <body>    
    <c:import url="/WEB-INF/jsp/shared/header.jsp" />
    
    <div class="container theme-showcase" role="main">
    
    <c:import url="/WEB-INF/jsp/shared/alert.jsp" />

	<div class="page-header">
      <h1>Tipos de categorias de produtos</h1>
    </div>

    <p>
      <a href="/product-types/new" class="btn btn-success btn-sm">Novo tipo de categoria de produtos</a>
    </p>

      <c:forEach var="productType" items="${productTypes}">
        <p>
          <a href="/product-types/${productType.id}" class="btn btn-warning btn-xs">Editar</a>
          ${productType.productCategory.sport.name} - ${productType.productCategory.name} - ${productType.name}
        </p>
      </c:forEach>
            
    </div>
    
    <c:import url="/WEB-INF/jsp/shared/footer.jsp" />
    
  </body>
</html>