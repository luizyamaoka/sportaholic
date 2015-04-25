<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
  <head>
    <meta charset="utf-8">
    <meta name="description" content="${activeUri.metaDescription}">
    <meta name="viewport" content ="width=device-width,initial-scale=1,user-scalable=yes" />
    <link rel='shortcut icon' type='image/x-icon' href='/favicon.ico'/>
    <title>${activeUri.name} | Sportaholic</title>
    <!-- c:import url="/WEB-INF/jsp/shared/js.jsp" /-->
    <!-- jQuery -->
	<script src="/resources/js/jquery/jquery.min.js"></script>
	<script async src="/resources/js/jquery/jquery-ui.min.js"></script>
	<!-- Latest compiled and minified JavaScript -->
	<script async src="/resources/js/bootstrap/bootstrap.min.js"></script>
  </head>
  <body>
    <c:import url="/WEB-INF/jsp/shared/tags.jsp" />
    <c:import url="/WEB-INF/jsp/shared/header.jsp" />
    
    <div class="container theme-showcase" role="main">

	  <c:import url="/WEB-INF/jsp/shared/alert.jsp" />
	  <c:import url="/WEB-INF/jsp/shared/breadcrumb.jsp" />

      <tiles:insertAttribute name="body" />
    
    </div>
    
    <c:import url="/WEB-INF/jsp/shared/footer.jsp" />
    
  </body>
</html>

<!-- c:import url="/WEB-INF/jsp/shared/css.jsp" /-->
<link rel="stylesheet" href="/resources/css/jquery/jquery-ui.css" />
<link rel="stylesheet" href="/resources/css/bootstrap/bootstrap.min.css" />
<link rel="stylesheet" href="/resources/css/bootstrap/bootstrap-theme.min.css" />
<link rel="stylesheet" href="/resources/css/main.css" />