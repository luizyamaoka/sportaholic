<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<div class="col-sm-3 col-sm-pull-9">
  <h4 class="page-header">Autores</h4>
  <div class="list-group">
    <c:forEach var="menuAuthor" items="${authors}">
      <a href="${uriService.getFriendlyUri(menuAuthor.uri)}" class="list-group-item" >
        ${menuAuthor.name}
      </a>
    </c:forEach>
  </div>
</div>