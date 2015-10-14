<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<div class="page-header">
  <h1>${articleTypeDto.name}</h1>
</div>
      
<c:import url="/WEB-INF/jsp/article-types/_form.jsp" />