<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<c:if test="${not empty successes}">
  <div class="alert alert-success" role="alert">
    <ul>
   	<c:forEach var="success" items="${successes}">
   	  <li>${success}</li>
   	</c:forEach>
   	</ul>
  </div>
</c:if>

<c:if test="${not empty errors}">
  <div class="alert alert-danger" role="alert">
    <ul>
   	<c:forEach var="error" items="${errors}">
   	  <li>${error}</li>
   	</c:forEach>
   	</ul>
  </div>
</c:if>

<c:if test="${not empty infos}">
  <div class="alert alert-info" role="alert">
    <ul>
   	<c:forEach var="info" items="${infos}">
   	  <li>${info}</li>
   	</c:forEach>
   	</ul>
  </div>
</c:if>