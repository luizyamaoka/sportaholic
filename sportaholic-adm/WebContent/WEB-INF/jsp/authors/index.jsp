<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<div class="page-header">
  <h1>Autores</h1>
</div>

<p>
  <a href="/authors/new" class="btn btn-success btn-sm">Novo autor</a>
</p>

<c:forEach var="author" items="${authors}">
  <p>
    <a href="/authors/${author.id}" class="btn btn-warning btn-xs">Editar</a>
    ${author.name}
  </p>
</c:forEach>