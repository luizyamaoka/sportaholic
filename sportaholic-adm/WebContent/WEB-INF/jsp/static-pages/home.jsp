<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
  <head>
    <meta charset="utf-8">
    <link rel='shortcut icon' type='image/x-icon' href='/favicon.ico'/>
    <title>Sportaholic | Viciados em esportes</title>
    <c:import url="/WEB-INF/jsp/shared/js.jsp" />
    <c:import url="/WEB-INF/jsp/shared/css.jsp" />
  </head>
  <body>    
    <c:import url="/WEB-INF/jsp/shared/header.jsp" />
    
    <div class="container theme-showcase" role="main">
    
    <c:import url="/WEB-INF/jsp/shared/alert.jsp" />

      <!-- Main jumbotron for a primary marketing message or call to action -->
      <div class="jumbotron" style="text-align: center">
        <h1 style="margin-bottom: 40px;">
          <img src="https://s3-sa-east-1.amazonaws.com/sportaholic/images/logo.png" style="max-height: 70px; max-width: 80%;">
        </h1>
        
      </div>
    
    </div>
    
    <c:import url="/WEB-INF/jsp/shared/footer.jsp" />
    
  </body>
</html>