<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<div class="page-header">
  <h1>Produtos</h1>
</div>

<p>
  <a href="/products/new" class="btn btn-success btn-sm">Novo produto</a>
</p>

<c:forEach var="product" items="${products}">
  <p>
    <a href="/products/${product.id}" class="btn btn-warning btn-xs">Editar</a>
    ${product.brand.name} - ${product.name}
  </p>
</c:forEach>