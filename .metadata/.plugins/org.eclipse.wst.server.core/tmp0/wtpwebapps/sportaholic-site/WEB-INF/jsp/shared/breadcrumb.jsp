<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<c:if test="${uris != null}">
  <ol class="breadcrumb">
    <c:forEach var="uri" items="${uris}">
      <c:choose>
        <c:when test="${uri.id == activeUri.id}">
          <li class="active">${uri.name}</li>
        </c:when>
        <c:otherwise>
          <li><a href="${uri.friendlyUri}">${uri.name}</a></li>
        </c:otherwise>
      </c:choose>
    
    </c:forEach>
  </ol>
</c:if>