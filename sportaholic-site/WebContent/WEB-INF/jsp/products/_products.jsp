<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<div class="row">
  <c:if test="${empty products}">
    <div class="jumbotron">
      <p>Nenhum produto encontrado</p>
    </div>
  </c:if>
  <c:forEach var="product" items="${products}">
    <c:set var="product" value="${product}" scope="request" />
    <c:import url="/WEB-INF/jsp/products/_product.jsp" />
  </c:forEach>
</div>

