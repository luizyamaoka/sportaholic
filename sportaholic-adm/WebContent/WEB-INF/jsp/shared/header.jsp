<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="security" %>

<nav class="navbar navbar-default navbar-fixed-top">
  <div class="container">
    <div class="navbar-header">
      <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#navbar" aria-expanded="false" aria-controls="navbar">
        <span class="sr-only">Toggle navigation</span>
        <span class="icon-bar"></span>
        <span class="icon-bar"></span>
        <span class="icon-bar"></span>
      </button>
      <a class="navbar-brand" href="/">Sportaholic</a>
    </div>
    <div id="navbar" class="navbar-collapse collapse">
      <ul class="nav navbar-nav">
        <li class="dropdown">
          <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-expanded="false">Blog <span class="caret"></span></a>
          <ul class="dropdown-menu" role="menu">
            <li><a href="/articles">Artigos</a></li>
            <li><a href="/authors">Autores</a></li>
            <li><a href="/sports">Esportes</a></li>
            <li><a href="/article-types">Tipos de artigo</a></li>
          </ul>
        </li>
        <li class="dropdown">
          <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-expanded="false">Loja <span class="caret"></span></a>
          <ul class="dropdown-menu" role="menu">
            <li><a href="/brands">Marcas</a></li>
            <li><a href="/product-categories">Categorias de produtos</a></li>
          </ul>
        </li>
      </ul>
      <ul class="nav navbar-nav navbar-right">
        <li><a href="/<c:url value='j_spring_security_logout' />">Logout</a></li>
      </ul>
    </div><!--/.nav-collapse -->
  </div>
</nav>