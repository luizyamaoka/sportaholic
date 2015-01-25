<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<div class="col-sm-3 col-sm-pull-9">
  <h4 class="page-header">Leia mais</h4>
  <div class="list-group">
    <c:forEach var="sport" items="${sports}">
      <a href="#menu-${sport.id}" class="list-group-item menu-header" data-menu="${sport.id}" data-toggle="collapse" aria-expanded="false" aria-controls="collapseExample">
        ${sport.name}
  <span class="pull-right">
    <span class="glyphicon glyphicon-chevron-down" id="icon-${sport.id}"></span>
  </span>
   </a>
         <div class="collapse" id="menu-${sport.id}" aria-expanded="false">
     <a href="${uriService.getFriendlyUri(sport.articlesUri)}" class="list-group-item">&nbsp;&nbsp;&nbsp;Todos sobre ${sport.name}</a>
     <c:forEach var="articleType" items="${articleTypes}">
       <a href="${uriService.getFriendlyUri(sport.getArticleTypesUri(articleType.id))}" class="list-group-item">&nbsp;&nbsp;&nbsp;${articleType.name}</a>
     </c:forEach>
   </div>
    </c:forEach>
     </div>
</div>
	  
	  
<script>
  $(document).ready(function(){
    $(".menu-header").click(function(){
	  $("#icon-"+$(this).attr('data-menu')).toggleClass("glyphicon-chevron-up");
	  $("#icon-"+$(this).attr('data-menu')).toggleClass("glyphicon-chevron-down");
	});
  });
</script>