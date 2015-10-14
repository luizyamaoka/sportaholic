<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<div class="page-header">
  <h1>${productTypeDto.name}</h1>
</div>
      
<c:import url="/WEB-INF/jsp/product-types/_form.jsp" />