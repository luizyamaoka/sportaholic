<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<div class="page-header">
  <h1>Esportes</h1>
</div>

<p>
  <a href="/sports/new" class="btn btn-success btn-sm">Novo esporte</a>
</p>

<c:forEach var="sport" items="${sports}">
  <p>
    <a href="/sports/${sport.id}" class="btn btn-warning btn-xs">Editar</a>
    ${sport.name}
  </p>
</c:forEach>