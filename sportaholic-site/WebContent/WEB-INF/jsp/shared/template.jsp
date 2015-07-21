<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://htmlcompressor.googlecode.com/taglib/compressor" prefix="compress" %>

<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml" lang="pt">
  <compress:html removeComments="true">
  <head>
    <meta http-equiv="Content-Type" content="text/html;charset=utf-8" />
    <meta name="description" content="${activeUri.metaDescription}" />
    <meta name="viewport" content ="width=device-width,initial-scale=1,user-scalable=yes" />
    <link rel='shortcut icon' type='image/x-icon' href='/favicon.ico'/>
    <title>${activeUri.name} | Sportaholic</title>
    <link rel="stylesheet" type="text/css" href="/wro/all.css" />
	<script type="text/javascript" src="/wro/jquery.js"></script>
	<script async="async" type="text/javascript" src="/wro/all.js"></script>
	
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
  </compress:html>
</html>
