<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<div class="row">
  <c:if test="${empty articles}">
    <div class="jumbotron">
      <p>Ainda não existem artigos nesta seção</p>
    </div>
  </c:if>
  <c:forEach var="article" items="${articles}">
    <div class="col-sm-6 col-md-4">
      <div class="thumbnail">
        <div class="caption">
          <h4>${article.title} <small>${article.subtitle}</small></h4>
          <div class="article-beginning">${article.content}</div>
        </div>

        <a href="${uriService.getFriendlyUri(article.uri)}" class="btn form-control btn-medium" role="button">
       		Continuar lendo
       		<span class="pull-right"><span class="glyphicon glyphicon-play"></span></span>
       	</a>
      </div>
    </div>
  </c:forEach>
</div>