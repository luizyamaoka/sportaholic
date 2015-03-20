<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<div class="row">
  <c:if test="${empty products}">
    <div class="jumbotron">
      <p>Nenhum produto encontrado</p>
    </div>
  </c:if>
  <c:forEach var="product" items="${products}">
    <div class="col-sm-6 col-md-4 col-lg-3">
      <div class="product-carousel">
        <img class="product-image" src="<%=com.sportaholic.EnvironmentConstants.IMAGES_URL%>${product.image}" data-holder-rendered="true">
        <div class="product-info">
          <h4><span class="brand">${product.brand.name}</span><br />${product.name}</h4>
          <p class="product-price">
            R$ <fmt:formatNumber value="${product.price}" type="number" pattern="#,##0.00" minFractionDigits="2" maxFractionDigits="2"/>
          </p>
        </div>
        <p style="text-align: center;"><a href="${uriService.getFriendlyUri(product.uri)}" class="btn-medium" role="button">Comprar</a></p>
      </div>
    </div>
  </c:forEach>
</div>

