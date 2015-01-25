<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<html>
  <head>
    <meta charset="utf-8">
    <link rel='shortcut icon' type='image/x-icon' href='/favicon.ico'/>
    <title>Alterar senha</title>
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
        <form class="form-horizontal" action="/clients/edit-password" method="post">
          <div class="form-group">
            <label for="firstNameInput" class="control-label col-sm-4 col-xs-12">Senha atual</label>
            <div class="col-sm-8 col-xs-12">
			  <input name="password" type="password" class="form-control" id="passwordInput" placeholder="Senha atual" required autofocus />
			</div>
		  </div>
		  <div class="form-group">
            <label for="newPasswordInput" class="control-label col-sm-4 col-xs-12">Nova senha</label>
            <div class="col-sm-8 col-xs-12">
			  <input name="newPassword" type="password" class="form-control" id="newPasswordInput" placeholder="Nova senha" required />
			</div>
		  </div>
		  <div class="form-group">
            <label for="newPasswordConfirmationInput" class="control-label col-sm-4 col-xs-12">Confirmação da nova senha</label>
            <div class="col-sm-8 col-xs-12">
			  <input name="newPasswordConfirmation" type="password" class="form-control" id="newPasswordConfirmationInput" placeholder="Repita a nova senha" required />
			</div>
		  </div>

		  <div class="form-group">
		    <div class="col-sm-offset-4 col-sm-8 col-xs-12">
              <input class="form-control btn btn-primary" type="submit" value="Atualizar" />
            </div>
		  </div>
		
        </form>
      </div>

    </div>
    
    <c:import url="/WEB-INF/jsp/shared/footer.jsp" />
    
  </body>
</html>