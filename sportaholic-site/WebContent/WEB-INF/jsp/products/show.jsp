<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="security" %>
      
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
					<button class="btn btn-purchase form-control" style="white-space: inherit ! important;">
				      Adicionar à sacola
				      <span class="pull-right">
                        <span class="glyphicon glyphicon-play"></span>
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
      
      <c:if test="${not empty product.productComments}">
        <h3 class="page-header">Avaliações</h3>
      </c:if>
	  
	  <c:forEach var="comment" items="${product.productComments}">
	    <div class="comment">
	      <p>
	        <c:set var="yellowStars" value="${comment.grade/20}" />
	        <c:forEach var="i" begin="1" end="${yellowStars}">
	          <span style="color: gold;">&#x2605;</span>
	        </c:forEach>
			<c:forEach var="i" begin="1" end="${5-yellowStars}">
			  <span>&#9734;</span>
			</c:forEach>
	        
	      </p>
	      <p>${comment.content}</p>
	      <p class="client">Por <a href="${uriService.getFriendlyUri(comment.client.uri)}">${comment.client.firstName} ${comment.client.lastName}</a> em <fmt:formatDate value="${comment.createdAt}" pattern="dd/MM/yyyy HH:mm" /></p>
	    </div>
	  </c:forEach>
	  
	  <h3 class="page-header">Avalie este produto</h3>
	  
	  <security:authorize access="isAuthenticated()">
	    <div class="center-form">
	      <form action="/product-comments/new" method="post" class="form-horizontal">
	        <input type="hidden" name="productId" value="${product.id}">
	        <div class="form-group">
	          <label for="gradeInput" class="control-label col-sm-4 col-xs-12">Nota * </label>
	          <div class="col-sm-8 col-xs-12">
	            <fieldset class="rating">
    			  <input type="radio" id="star5" name="grade" value="100" /><label for="star5" title="Perfeito!">5 stars</label>
				  <input type="radio" id="star4" name="grade" value="80" /><label for="star4" title="Muito bom">4 stars</label>
				  <input type="radio" id="star3" name="grade" value="60" /><label for="star3" title="Bom">3 stars</label>
				  <input type="radio" id="star2" name="grade" value="40" /><label for="star2" title="Ruim">2 stars</label>
				  <input type="radio" id="star1" name="grade" value="20" /><label for="star1" title="Péssimo">1 star</label>
				</fieldset>
			  </div>
	        </div>
	        <div class="form-group">
	          <label for="contentInput" class="control-label col-sm-4 col-xs-12">Avaliação * </label>
	          <div class="col-sm-8 col-xs-12">
	            <textarea rows="3" class="form-control" name="content" placeholder="O que achou deste produto?" style="height:100px;" required></textarea>
			  </div>
	        </div>
	        <div class="form-group">
              <div class="col-sm-offset-4 col-sm-8 col-xs-12">
                <input type="submit" class="form-control btn btn-primary" value="Avaliar">
              </div>
            </div>
	      </form>
	    </div>
	  </security:authorize>
	  <security:authorize access="!isAuthenticated()">
	    <div class="article">
	      <div class="secondary-text">
	        <p>Quer deixar seu comentário? Efetue seu <a href="${uriService.getFriendlyUri('/login')}">login</a> ou, caso ainda não possua cadastro, <a href="${uriService.getFriendlyUri('/clients/new')}">crie uma conta</a>.</p>
	      </div>
	    </div>
	  </security:authorize>
    
      <c:if test="${not empty products}">
      <h3 class="page-header">Você também pode gostar</h3>
      <div class="row">
        <c:import url="/WEB-INF/jsp/products/_products.jsp" />
      </div>
      </c:if>