<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<div class="page-header">
  <h1>${articleDto.title}</h1>
</div>
      
<c:import url="/WEB-INF/jsp/articles/_form.jsp" />