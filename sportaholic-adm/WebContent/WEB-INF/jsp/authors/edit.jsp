<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<div class="page-header">
  <h1>${authorDto.name}</h1>
</div>
      
<c:import url="/WEB-INF/jsp/authors/_form.jsp" />