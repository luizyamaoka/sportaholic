<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

  <div class="list-group">
    <c:forEach var="sport" items="${sports}">
    <c:forEach var="productCategory" items="${sport.productCategories}">
      <a href="#menu-${productCategory.id}" class="list-group-item menu-header" data-menu="${productCategory.id}" data-toggle="collapse" aria-expanded="false" aria-controls="collapseExample">
        ${productCategory.name}
        <span class="pull-right">
          <span class="glyphicon glyphicon-chevron-down" id="icon-${productCategory.id}"></span>
        </span>
      </a>
      <div class="collapse" id="menu-${productCategory.id}" aria-expanded="false">
        <c:forEach var="productType" items="${productCategory.productTypes}">
          <a href="${uriService.getFriendlyUri(sport.getArticleTypesUri(productType.id))}" class="list-group-item">&nbsp;&nbsp;&nbsp;${productType.name}</a>
        </c:forEach>
      </div>
    </c:forEach>
    </c:forEach>
  </div>	  
	  
<script>
  $(document).ready(function(){
    $(".menu-header").click(function(){
	  $("#icon-"+$(this).attr('data-menu')).toggleClass("glyphicon-chevron-up");
	  $("#icon-"+$(this).attr('data-menu')).toggleClass("glyphicon-chevron-down");
	});
  });
</script>