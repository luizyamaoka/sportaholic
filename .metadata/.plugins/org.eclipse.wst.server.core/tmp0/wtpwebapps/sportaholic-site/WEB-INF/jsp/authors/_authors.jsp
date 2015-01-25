<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<div class="row">
  <c:forEach var="author" items="${authors}">
    <div class="col-sm-6 col-md-4">
      <div class="thumbnail">
        <div class="caption">
          <h4 class="author-name">${author.name}</h4>
          <div class="article-beginning">${author.description}</div>
        </div>
        <p style="text-align: center;"><a href="${uriService.getFriendlyUri(author.uri)}" class="btn-medium" role="button">Saiba mais</a></p>
      </div>
    </div>
  </c:forEach>
</div>