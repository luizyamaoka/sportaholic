<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<div class="page-header">
  <h1>${productDto.name}</h1>
</div>
      
<c:import url="/WEB-INF/jsp/products/_form.jsp" />