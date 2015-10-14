<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

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