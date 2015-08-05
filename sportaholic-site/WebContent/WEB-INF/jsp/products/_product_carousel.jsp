<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<c:if test="${empty products}">
  <div class="jumbotron">
    <p>Nenhum produto encontrado</p>
  </div>
</c:if>

<c:if test="${not empty products}">
<fmt:formatNumber var="carousels" value="${products.size() / 4 - 0.499999}" maxFractionDigits="0" />

<div id="carousel-example-generic" class="carousel slide" data-ride="carousel">
  <div class="carousel-inner" role="listbox">
    <c:forEach var="i" begin="0" end="${carousels-1}">
      <c:choose>
        <c:when test="${i == 0}">
          <div class="row item active">
            <c:forEach var="j" begin="0" end="3">
              <c:set var="product" value="${products.get(i+j)}" scope="request" />
              <c:choose>
                <c:when test="${j == 0}">
                  <div>
                    <c:import url="/WEB-INF/jsp/products/_product.jsp" />
                  </div>
                </c:when>
                <c:when test="${j == 1}">
                  <div class="hidden-xs">
                    <c:import url="/WEB-INF/jsp/products/_product.jsp" />
                  </div>
                </c:when>
                <c:when test="${j == 2}">
                  <div class="hidden-xs hidden-sm">
                    <c:import url="/WEB-INF/jsp/products/_product.jsp" />
                  </div>
                </c:when>
                <c:when test="${j == 3}">
                  <div class="hidden-xs hidden-sm hidden-md">
                    <c:import url="/WEB-INF/jsp/products/_product.jsp" />
                  </div>
                </c:when>
              </c:choose>
            </c:forEach>
          </div>
        </c:when>
        <c:otherwise>
          <div class="row item">
            <c:forEach var="j" begin="0" end="3">
              <c:set var="product" value="${products.get(i*4+j)}" scope="request" />
              <c:choose>
                <c:when test="${j == 0}">
                  <div>
                    <c:import url="/WEB-INF/jsp/products/_product.jsp" />
                  </div>
                </c:when>
                <c:when test="${j == 1}">
                  <div class="hidden-xs">
                    <c:import url="/WEB-INF/jsp/products/_product.jsp" />
                  </div>
                </c:when>
                <c:when test="${j == 2}">
                  <div class="hidden-xs hidden-sm">
                    <c:import url="/WEB-INF/jsp/products/_product.jsp" />
                  </div>
                </c:when>
                <c:when test="${j == 3}">
                  <div class="hidden-xs hidden-sm hidden-md">
                    <c:import url="/WEB-INF/jsp/products/_product.jsp" />
                  </div>
                </c:when>
              </c:choose>
            </c:forEach>
          </div>
        </c:otherwise>
      </c:choose>
    </c:forEach>
  </div>
  <a class="product-carousel-control product-carousel-control-prev" href="#carousel-example-generic" role="button" data-slide="prev">
    <span class="product-carousel-arrow glyphicon glyphicon-chevron-left" aria-hidden="true"></span>
    <span class="sr-only">Previous</span>
  </a>
  <a class="product-carousel-control product-carousel-control-next" href="#carousel-example-generic" role="button" data-slide="next">
    <span class="product-carousel-arrow glyphicon glyphicon-chevron-right" aria-hidden="true"></span>
    <span class="sr-only">Next</span>
  </a>
</div>
</c:if>