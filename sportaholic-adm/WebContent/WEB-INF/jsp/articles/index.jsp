<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<jsp:useBean id="now" class="java.util.Date"/>

<div class="page-header">
  <h1>Artigos</h1>
</div>

<p>
  <a href="/articles/new" class="btn btn-success btn-sm">Novo artigo</a>
</p>

<c:forEach var="article" items="${articles}">
  <p>
    <a href="/articles/${article.id}" class="btn btn-warning btn-xs">Editar</a>
    <c:choose>
      <c:when	test="${article.publishedAt le now}">
        <span class="label label-success">Publicado</span>
      </c:when>
      <c:otherwise>
        <span class="label label-danger">Não publicado</span>
      </c:otherwise>
    </c:choose>
    ${article.title} - ${article.author.name}
  </p>
</c:forEach>