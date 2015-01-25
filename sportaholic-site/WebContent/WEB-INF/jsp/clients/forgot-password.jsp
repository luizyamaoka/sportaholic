<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<html>
  <head>
    <meta charset="utf-8">
    <link rel='shortcut icon' type='image/x-icon' href='/favicon.ico'/>
    <title>Esqueci a minha senha</title>
    <c:import url="/WEB-INF/jsp/shared/css.jsp" />
    <c:import url="/WEB-INF/jsp/shared/js.jsp" />
  </head>
  <body>
    <c:import url="/WEB-INF/jsp/shared/tags.jsp" />
    
    <c:import url="/WEB-INF/jsp/shared/header.jsp" />
    
    <div class="container theme-showcase" role="main">

	  <c:import url="/WEB-INF/jsp/shared/alert.jsp" />
	  <c:import url="/WEB-INF/jsp/shared/breadcrumb.jsp" />

	  <div class="center-form">
        <form class="form-horizontal" action="/clients/forgot-password" method="post">
          <div class="form-group">
            <label for="emailInput" class="control-label col-sm-4 col-xs-12">E-mail</label>
            <div class="col-sm-8 col-xs-12">
			  <input name="email" type="email" class="form-control" id="emailInput" placeholder="E-mail" required autofocus />
			</div>
		  </div>
		  <div class="form-group">
		    <div class="col-sm-offset-4 col-sm-8 col-xs-12">
              <input class="form-control btn btn-primary" type="submit" value="Gerar nova senha" />
            </div>
		  </div>
		
        </form>
      </div>

    </div>
    
    <c:import url="/WEB-INF/jsp/shared/footer.jsp" />
    
  </body>
</html>