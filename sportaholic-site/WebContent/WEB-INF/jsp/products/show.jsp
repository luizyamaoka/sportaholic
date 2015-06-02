<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
      
      <div class="row">
        <div class="col-sm-6 col-xs-12 product-main-image">
          <img src="<%=com.sportaholic.EnvironmentConstants.IMAGES_URL%>${product.image}" alt="${product.name}" style="max-width: 100%; max-height: 100%;" />
        </div>
        <div class="col-sm-6 col-xs-12 product-description">
          <h2>${product.brand.name}</h2>
          <h1>${product.name}</h1>
          <p>${product.description}</p>
          <p>
            R$ <fmt:formatNumber value="${product.price}" type="number" pattern="#,##0.00" minFractionDigits="2" maxFractionDigits="2"/>
          </p>
          <p style="margin-top: 50px;">
            <c:choose>
              <c:when test="${not product.isActive}">
                <div style="background-color: #eeeeee; padding: 30px 30px; font-size: 14px;">
                  Produto não disponível para compra
                </div>
              </c:when>
              <c:when test="${product.inStock <= 0}">
                <div style="background-color: #eeeeee; padding: 30px 30px; font-size: 14px;">
                  Produto sem estoque
                </div>
              </c:when>
              <c:otherwise>
                <a href="${product.meliUrl}" class="btn-big" role="button" target="blank">Comprar pelo Mercado Livre</a>
              </c:otherwise>
            </c:choose>
            
          </p>
        </div>
      </div>
    
      <c:if test="${not empty products}">
      <h3 class="page-header">Você também pode gostar</h3>
      <div class="row">
        <c:import url="/WEB-INF/jsp/products/_products.jsp" />
      </div>
      </c:if>