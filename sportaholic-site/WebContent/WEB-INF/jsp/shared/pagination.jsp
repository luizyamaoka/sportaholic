<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<nav style="text-align: center;">
  <ul class="pagination">
  
    <c:choose>
      <c:when test="${currentPage == firstPage}">
        <li class="disabled"><a href="#" aria-label="Previous"><span aria-hidden="true">«</span></a></li>
      </c:when>
      <c:otherwise>
        <li><a href="?page=${currentPage-1}" aria-label="Previous"><span aria-hidden="true">«</span></a></li>
      </c:otherwise>
    </c:choose>
    
    <c:forEach var="i" begin="${firstPage}" end="${lastPage}">
      <c:choose>
        <c:when test="${i == currentPage}">
          <li class="active"><a href="#">${i}<span class="sr-only">(current)</span></a></li>
        </c:when>
        <c:otherwise>
          <li><a href="?page=${i}">${i}</a></li>
        </c:otherwise>
      </c:choose>
    </c:forEach>
      
    <c:choose>
      <c:when test="${currentPage == lastPage}">
        <li class="disabled"><a href="#" aria-label="Next"><span aria-hidden="true">»</span></a></li>
      </c:when>
      <c:otherwise>
        <li><a href="?page=${currentPage+1}" aria-label="Next"><span aria-hidden="true">»</span></a></li>
      </c:otherwise>
    </c:choose>
    
  </ul>
</nav>