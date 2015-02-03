<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
<head>
  <meta charset="utf-8">
  <link rel='shortcut icon' type='image/x-icon' href='/favicon.ico'/>
  <title>Login</title>
  <c:import url="/WEB-INF/jsp/shared/css.jsp" />
  <c:import url="/WEB-INF/jsp/shared/js.jsp" />
</head>
  <body>
  
    <div id="wrapper">
  
  	  <div class="container theme-showcase" role="main">
	    
	    <c:import url="/WEB-INF/jsp/shared/alert.jsp" /> 
	    
	    <div style="text-align: center;">
	      <h1 style="margin-bottom: 40px;">
            <img src="https://s3-sa-east-1.amazonaws.com/sportaholic/images/logo.png" style="max-height: 70px; max-width: 80%;">
          </h1>
        </div>
	      
	    <div class="center-form">
          <form class="form-horizontal" action="/<c:url value='j_spring_security_check' />" method="post">
            <div class="form-group">
              <label for="usernameInput" class="control-label col-sm-4 col-xs-12">E-mail</label>
              <div class="col-sm-8 col-xs-12">
			    <input name="username" type="email" class="form-control" id="usernameInput" placeholder="E-mail" required autofocus />
			  </div>
		    </div>
		    <div class="form-group">
              <label for="passwordInput" class="control-label col-sm-4 col-xs-12">Senha</label>
              <div class="col-sm-8 col-xs-12">
			    <input name="password" type="password" class="form-control" id="passwordInput" placeholder="Senha" required />
			  </div>
		    </div>
		    <div class="form-group">
		      <div class="col-sm-offset-4 col-sm-8 col-xs-12">
                <input class="form-control btn btn-primary" type="submit" value="Entrar" />
              </div>
		    </div>
		    <!--<div class="form-group">
              <label for="forgotPasswordInput" class="control-label col-sm-4 col-xs-12"></label>
              <div class="col-sm-8 col-xs-12">
			    <a class="btn btn-default form-control" href="/clients/forgot-password">Esqueci a senha</a>
			  </div>
		    </div>-->
		
          </form>
        </div>
    
      </div>
    
    </div>
  
  </body>
</html>