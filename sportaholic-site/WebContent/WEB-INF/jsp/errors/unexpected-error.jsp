<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
  <head>
    <meta charset="utf-8">
    <link rel='shortcut icon' type='image/x-icon' href='/favicon.ico'/>
    <title>Erro inesperado</title>
    <c:import url="/WEB-INF/jsp/shared/css.jsp" />
    <c:import url="/WEB-INF/jsp/shared/js.jsp" />
  </head>
  <body>
    <c:import url="/WEB-INF/jsp/shared/tags.jsp" />
    
    <c:import url="/WEB-INF/jsp/shared/header.jsp" />
    
    <div class="container theme-showcase" role="main">
    
    <c:import url="/WEB-INF/jsp/shared/alert.jsp" />

      <!-- Main jumbotron for a primary marketing message or call to action -->
      <div class="jumbotron" style="text-align: center">
        <h1 style="margin-bottom: 40px;">
          Erro inesperado
        </h1>
        
        <p>Ocorreu um erro inesperado. Por favor tente novamente. Caso o erro persista, entre em contato com os desenvolvedores.</p>
        
      </div>
	  
    </div>
    
    <c:import url="/WEB-INF/jsp/shared/footer.jsp" />
    
  </body>
</html>