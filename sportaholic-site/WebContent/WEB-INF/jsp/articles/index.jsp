<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<div class="row">
  <div class="col-sm-9 col-sm-push-3">
    <c:import url="/WEB-INF/jsp/articles/_articles.jsp" />
  </div>
 
  <div class="col-sm-3 col-sm-pull-9">
    <h4 class="page-header">Leia mais</h4>
    <c:import url="/WEB-INF/jsp/articles/_menu.jsp" />
  </div>
 </div>
