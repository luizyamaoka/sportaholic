<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
  <head>
    <meta charset="utf-8">
    <link rel='shortcut icon' type='image/x-icon' href='/favicon.ico'/>
    <title>${articleTypeDto.name}</title>
    <c:import url="/WEB-INF/jsp/shared/js.jsp" />
    <c:import url="/WEB-INF/jsp/shared/css.jsp" />
  </head>
  <body>    
    <c:import url="/WEB-INF/jsp/shared/header.jsp" />
    
    <div class="container theme-showcase" role="main">
    
    <c:import url="/WEB-INF/jsp/shared/alert.jsp" />

	<div class="page-header">
      <h1>${articleTypeDto.name}</h1>
    </div>
          
    <c:import url="/WEB-INF/jsp/article-types/_form.jsp" />
            
    </div>
    
    <c:import url="/WEB-INF/jsp/shared/footer.jsp" />
    
  </body>
</html>