<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

  <div class="list-group">
    <c:forEach var="productCategory" items="${productCategories}">
      <a href="#menu-${productCategory.id}" class="list-group-item menu-header" data-menu="${productCategory.id}" data-toggle="collapse" aria-expanded="false" aria-controls="collapseExample">
        ${productCategory.name}
        <span class="pull-right">
          <span class="glyphicon glyphicon-chevron-down" id="icon-${productCategory.id}"></span>
        </span>
      </a>
      <div class="collapse" id="menu-${productCategory.id}" aria-expanded="false">
        <c:forEach var="articleType" items="${productCategories}">
          <a href="${uriService.getFriendlyUri(sport.getArticleTypesUri(articleType.id))}" class="list-group-item">&nbsp;&nbsp;&nbsp;${articleType.name}</a>
        </c:forEach>
      </div>
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