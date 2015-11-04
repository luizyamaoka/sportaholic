<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<div class="row">
  <c:if test="${empty articles}">
    <div class="jumbotron">
      <p>Ainda n�o existem artigos nesta se��o</p>
    </div>
  </c:if>
  <c:forEach var="article" items="${articles}">
    <c:set var="article" value="${article}" scope="request" />
    <c:import url="/WEB-INF/jsp/articles/_article.jsp" />
  </c:forEach>
</div>