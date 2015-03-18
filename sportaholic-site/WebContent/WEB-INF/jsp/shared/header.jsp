<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="security" %>

<div class="container">
  <div class="header">
  
    <nav>
      <ul class="nav nav-pills pull-right">
      
        <li class="visible-xs">
          <a href="#" class="mobile-menu">
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
          </a>
        </li>
      
        <security:authorize access="isAuthenticated()">
          <li class="dropdown hidden-xs">
            <a href="#" class="dropdown-toggle" data-toggle="dropdown">
              <span>Minha conta <span class="caret"></span></span>
            </a>
            <ul class="dropdown-menu" role="menu">
              <li><a href="${uriService.getFriendlyUri('/clients/profile')}">Perfil</a></li>
              <li><a href="${uriService.getFriendlyUri('/clients/edit-password')}">Alterar senha</a></li>
              <li class="divider"></li>
              <li><a href="/<c:url value='j_spring_security_logout' />">Logout</a></li>
            </ul>
          </li>
        </security:authorize>
        
        <security:authorize access="!isAuthenticated()">
          <li class="hidden-xs"><a href="${uriService.getFriendlyUri('/clients/new')}">Cadastre-se</a></li>
          <li class="hidden-xs"><a href="${uriService.getFriendlyUri('/login')}">Login</a></li>
        </security:authorize>
      </ul>
    </nav>
    

    <a href="/">
      <img id="logo" src="<%=com.sportaholic.EnvironmentConstants.IMAGES_URL%>logo.png" height="40px">
    </a>
    
</div>
  <div class="list-group visible-xs">
    <div class="collapse" id="menu" aria-expanded="false">
      <security:authorize access="!isAuthenticated()">
        <a href="${uriService.getFriendlyUri('/clients/new')}" class="list-group-item">Cadastre-se</a>
        <a href="${uriService.getFriendlyUri('/login')}" class="list-group-item">Login</a>
      </security:authorize>
      <security:authorize access="isAuthenticated()">
        <a href="${uriService.getFriendlyUri('/clients/profile')}" class="list-group-item">Perfil</a>
        <a href="${uriService.getFriendlyUri('/clients/edit-password')}" class="list-group-item">Alterar senha</a>
        <a href="/<c:url value='j_spring_security_logout' />" class="list-group-item">Logout</a>
      </security:authorize>
    </div>
  </div>
</div>

<div id="main-menu">
  <div class="container">
    <a href="/"><span class="glyphicon glyphicon-home"></span></a> 
    <a href="${uriService.getFriendlyUri('/sports/1')}">Natação</a>
    <a href="${uriService.getFriendlyUri('/about-us')}">Quem somos</a>

  </div>
</div>

<script>
  $(document).ready(function(){
    $(".mobile-menu").click(function(){
	  $("#menu").toggle();
	});
  });
</script>


