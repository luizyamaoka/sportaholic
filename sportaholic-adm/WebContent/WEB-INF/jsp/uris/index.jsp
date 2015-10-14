<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<div class="page-header">
  <h1>Uris</h1>
</div>

<p>
  <a href="/uris/new" class="btn btn-success btn-sm">Nova Uri</a>
</p>

<c:forEach var="uri" items="${uris}">
  <p>
    <a href="/uris/${uri.id}" class="btn btn-warning btn-xs">Editar</a>
    ${uri.name}
  </p>
</c:forEach>