<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

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