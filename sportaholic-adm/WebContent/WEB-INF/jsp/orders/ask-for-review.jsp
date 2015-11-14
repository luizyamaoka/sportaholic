<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<div class="page-header">
  <h1>Solicitar review</h1>
</div>

<div class="center-form">
  <form class="form-horizontal" method="post">
    
    <div class="form-group">
      <label for="nameInput" class="control-label col-sm-4 col-xs-12">Nome * </label>
      <div class="col-sm-8 col-xs-12">
        <input name="clientName" class="form-control" id="nameInput" placeholder="Nome" required autofocus />
      </div>
    </div>
    
    <div class="form-group">
      <label for="emailInput" class="control-label col-sm-4 col-xs-12">E-mail * </label>
      <div class="col-sm-8 col-xs-12">
        <input name="clientEmail" class="form-control" id="emailInput" placeholder="E-mail" required/>
      </div>
    </div>
  
    <div class="form-group">
      <label for="brandIdOptions" class="control-label col-sm-4 col-xs-12">Produto * </label>
      <div class="col-sm-8 col-xs-12">
	    <select name="productId" class="form-control" id="productIdOptions" required>
	      <option value="">Selecione um produto</option>
	      <c:forEach var="product" items="${products}">
	        <option value="${product.id}">${product.brand.name} - ${product.name}</option>
	      </c:forEach>
        </select>
	  </div>
    </div>

    <div class="form-group">
      <div class="col-sm-offset-4 col-sm-8 col-xs-12">
        <input class="form-control btn btn-primary" type="submit" value="Enviar email" />
      </div>
    </div>
		
  </form> 
</div>