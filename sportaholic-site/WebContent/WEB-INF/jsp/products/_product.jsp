<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<div class="col-sm-6 col-md-4 col-lg-3">
  <div class="product-carousel">
    <img class="product-image" src="<%=com.sportaholic.EnvironmentConstants.IMAGES_URL%>${product.image}" data-holder-rendered="true" alt="${product.name}">
    <div class="product-info">
      <h4 class="product-brand">${product.brand.name}</h4>
      <h4 class="product-name">${product.name}</h4>
      <p class="product-price">
        R$ <fmt:formatNumber value="${product.price}" type="number" pattern="#,##0.00" minFractionDigits="2" maxFractionDigits="2"/>
      </p>
    </div>

   	<a href="${uriService.getFriendlyUri(product.uri)}" class="btn form-control btn-medium" role="button">
   	  <span class="col-xs-10">
   		&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Confira
   	  </span>
   	  <span class="col-xs-2">
   	    <span class="glyphicon glyphicon-play"></span>
   	  </span>
   	</a>
  </div>
</div>