<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

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