<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ page import="com.sportaholic.EnvironmentConstants" %>

<html>
  <head>
    <meta charset="utf-8">
    <link rel='shortcut icon' type='image/x-icon' href='/favicon.ico'/>
    <meta name="description" content="${activeUri.metaDescription}">
    <title>${brand.name} | Sportaholic</title>
    <c:import url="/WEB-INF/jsp/shared/css.jsp" />
    <c:import url="/WEB-INF/jsp/shared/js.jsp" />
  </head>
  <body>
    <c:import url="/WEB-INF/jsp/shared/tags.jsp" />
    
    <c:import url="/WEB-INF/jsp/shared/header.jsp" />
    
    <div class="container theme-showcase" role="main">
    
      <c:import url="/WEB-INF/jsp/shared/alert.jsp" />
      <c:import url="/WEB-INF/jsp/shared/breadcrumb.jsp" />
    
      <h1>${brand.name}</h1>
      <p>${brand.description}</p>

</div>

    
    <c:import url="/WEB-INF/jsp/shared/footer.jsp" />
    
  </body>
</html>