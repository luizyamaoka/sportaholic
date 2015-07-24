<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
      
      <div itemscope itemtype="http://schema.org/Product" class="row">
        <div class="col-sm-6 col-xs-12 product-main-image">
          <img src="<%=com.sportaholic.EnvironmentConstants.IMAGES_URL%>${product.image}" alt="${product.name}" style="max-width: 100%; max-height: 100%;" />
        </div>
        <div class="col-sm-6 col-xs-12 product-description">
          <span itemprop="brand" itemscope itemtype="http://schema.org/Brand">
            <h2 itemprop="name">${product.brand.name}</h2>
          </span>
          <h1 itemprop="name">${product.name}</h1>
          <p itemprop="description">${product.description}</p>
          <span itemprop="offers" itemscope itemtype="http://schema.org/Offer">
            <p itemprop="price">
              R$ <fmt:formatNumber value="${product.price}" type="number" pattern="#,##0.00" minFractionDigits="2" maxFractionDigits="2"/>
            </p>
          </span>
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
              <c:when test="${empty product.pagseguroId}">
                <div style="background-color: #eeeeee; padding: 30px 30px; font-size: 14px;">
                  Produto não disponível para compra
                </div>
              </c:when>
              <c:otherwise>
                <!-- a href="${product.meliUrl}" class="btn-big" role="button" target="blank">Comprar pelo Mercado Livre</a-->
				
				<!-- INICIO FORMULARIO BOTAO PAGSEGURO -->
				<form action="https://pagseguro.uol.com.br/checkout/v2/cart.html?action=add" method="post">
					<!-- NÃO EDITE OS COMANDOS DAS LINHAS ABAIXO -->
					<input type="hidden" name="itemCode" value="${product.pagseguroId}" />
					
					<div class="row">
					<div class="col-lg-9">
					<button class="btn btn-purchase form-control">
				      Adicionar à sacola
				      <span class="pull-right">
				        <span class="glyphicon glyphicon-play">
				        </span>
				      </span>
				    </button>  
				    </div>
				    </div>
				</form>
				<!-- FINAL FORMULARIO BOTAO PAGSEGURO -->
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