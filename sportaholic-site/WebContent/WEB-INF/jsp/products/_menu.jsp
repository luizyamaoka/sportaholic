<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<c:forEach var="sport" items="${sports}">
  <c:if test="${not empty sport.productCategories}">
  <h5 class="page-header">${sport.name}</h5>
  <div class="list-group">
    <c:forEach var="productCategory" items="${sport.productCategories}">
      <a href="#menu-${productCategory.id}" class="list-group-item menu-header" data-menu="${productCategory.id}" data-toggle="collapse" aria-expanded="false" aria-controls="collapseExample">
        ${productCategory.name}
        <span class="pull-right">
          <span class="glyphicon glyphicon-chevron-down" id="icon-${productCategory.id}"></span>
        </span>
      </a>
      <div class="collapse" id="menu-${productCategory.id}" aria-expanded="false">
        <c:forEach var="productType" items="${productCategory.productTypes}">
          <a href="${uriService.getFriendlyUri(productType.uri)}" class="list-group-item">&nbsp;&nbsp;&nbsp;${productType.name}</a>
        </c:forEach>
      </div>
    </c:forEach>
  </div>	  
  </c:if>
</c:forEach>
	  
<script>
  $(document).ready(function(){
    $(".menu-header").click(function(){
	  $("#icon-"+$(this).attr('data-menu')).toggleClass("glyphicon-chevron-up");
	  $("#icon-"+$(this).attr('data-menu')).toggleClass("glyphicon-chevron-down");
	});
  });
</script>