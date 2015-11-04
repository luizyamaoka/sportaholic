<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<c:if test="${empty articles}">
  <div class="jumbotron">
    <p>Ainda não existem artigos nesta seção</p>
  </div>
</c:if>

<c:if test="${not empty articles}">
<fmt:formatNumber var="carousels" value="${articles.size() / 3 - 0.499999}" maxFractionDigits="0" />

<div id="article-carousel" class="carousel slide" data-ride="carousel">
  <div class="carousel-inner" role="listbox">
    <c:forEach var="i" begin="0" end="${carousels-1}">
      <c:choose>
        <c:when test="${i == 0}">
          <div class="row item active">
            <c:forEach var="j" begin="0" end="2">
              <c:set var="article" value="${articles.get(i+j)}" scope="request" />
              <c:choose>
                <c:when test="${j == 0}">
                  <div>
                    <c:import url="/WEB-INF/jsp/articles/_article.jsp" />
                  </div>
                </c:when>
                <c:when test="${j == 1}">
                  <div class="hidden-xs">
                    <c:import url="/WEB-INF/jsp/articles/_article.jsp" />
                  </div>
                </c:when>
                <c:when test="${j == 2}">
                  <div class="hidden-xs hidden-sm">
                    <c:import url="/WEB-INF/jsp/articles/_article.jsp" />
                  </div>
                </c:when>
              </c:choose>
            </c:forEach>
          </div>
        </c:when>
        <c:otherwise>
          <div class="row item">
            <c:forEach var="j" begin="0" end="2">
              <c:set var="article" value="${articles.get(i*3+j)}" scope="request" />
              <c:choose>
                <c:when test="${j == 0}">
                  <div>
                    <c:import url="/WEB-INF/jsp/articles/_article.jsp" />
                  </div>
                </c:when>
                <c:when test="${j == 1}">
                  <div class="hidden-xs">
                    <c:import url="/WEB-INF/jsp/articles/_article.jsp" />
                  </div>
                </c:when>
                <c:when test="${j == 2}">
                  <div class="hidden-xs hidden-sm">
                    <c:import url="/WEB-INF/jsp/articles/_article.jsp" />
                  </div>
                </c:when>
              </c:choose>
            </c:forEach>
          </div>
        </c:otherwise>
      </c:choose>
    </c:forEach>
  </div>
  <a class="product-carousel-control product-carousel-control-prev" href="#article-carousel" role="button" data-slide="prev">
    <span class="product-carousel-arrow glyphicon glyphicon-chevron-left" aria-hidden="true"></span>
    <span class="sr-only">Previous</span>
  </a>
  <a class="product-carousel-control product-carousel-control-next" href="#article-carousel" role="button" data-slide="next">
    <span class="product-carousel-arrow glyphicon glyphicon-chevron-right" aria-hidden="true"></span>
    <span class="sr-only">Next</span>
  </a>
</div>
</c:if>