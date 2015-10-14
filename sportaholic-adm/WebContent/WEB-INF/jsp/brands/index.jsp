<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

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