<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
  <head>
    <meta charset="utf-8">
    <link rel='shortcut icon' type='image/x-icon' href='/favicon.ico'/>
    <title>Categorias de produtos</title>
    <c:import url="/WEB-INF/jsp/shared/js.jsp" />
    <c:import url="/WEB-INF/jsp/shared/css.jsp" />
  </head>
  <body>    
    <c:import url="/WEB-INF/jsp/shared/header.jsp" />
    
    <div class="container theme-showcase" role="main">
    
    <c:import url="/WEB-INF/jsp/shared/alert.jsp" />

	<div class="page-header">
      <h1>Categorias de produtos</h1>
    </div>

    <p>
      <a href="/product-categories/new" class="btn btn-success btn-sm">Nova categoria de produtos</a>
    </p>

      <c:forEach var="productCategory" items="${productCategories}">
        <p>
          <a href="/product-categories/${productCategory.id}" class="btn btn-warning btn-xs">Editar</a>
          ${productCategory.sport.name} - ${productCategory.name}
        </p>
      </c:forEach>
            
    </div>
    
    <c:import url="/WEB-INF/jsp/shared/footer.jsp" />
    
  </body>
</html>