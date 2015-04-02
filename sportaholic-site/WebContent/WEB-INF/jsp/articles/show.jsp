<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="security" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<html>
  <head>
    <meta charset="utf-8">
    <meta name="author" content="${article.author.name}">
    <meta name="description" content="${activeUri.metaDescription}">
    <link rel='shortcut icon' type='image/x-icon' href='/favicon.ico'/>
    <title>${article.title}</title>
    <c:import url="/WEB-INF/jsp/shared/css.jsp" />
    <c:import url="/WEB-INF/jsp/shared/js.jsp" />
  </head>
  <body>
    <c:import url="/WEB-INF/jsp/shared/tags.jsp" />
    
    <div id="fb-root"></div>
    <script>(function(d, s, id) {
      var js, fjs = d.getElementsByTagName(s)[0];
      if (d.getElementById(id)) return;
      js = d.createElement(s); js.id = id;
      js.src = "//connect.facebook.net/pt_BR/sdk.js#xfbml=1&version=v2.0";
      fjs.parentNode.insertBefore(js, fjs);
      }(document, 'script', 'facebook-jssdk'));
    </script>
    
    <c:import url="/WEB-INF/jsp/shared/header.jsp" />
    
    <div class="container theme-showcase" role="main">
    
    <c:import url="/WEB-INF/jsp/shared/alert.jsp" />
    <c:import url="/WEB-INF/jsp/shared/breadcrumb.jsp" />

    <div class="row">

      <div class="col-sm-9 col-sm-push-3">
      <div class="article">
	    <h1>${article.title}</h1>
	    <h3>${article.subtitle}</h3>
	    
	    <div class="author">
	      Por <a href="${uriService.getFriendlyUri(article.author.uri)}">${article.author.name}</a>
	      em <fmt:formatDate value="${article.publishedAt}" pattern="dd/MM/yyyy HH:mm" timeZone="Brazil/East"/>
	    </div>
	    <br />
	    <div class="fb-like" data-href="http://www.sportaholic.com.br${uriService.getFriendlyUri(article.uri)}" 
        data-layout="button" data-action="like" data-show-faces="true" data-share="true"></div>
		<br /><br />
		<div class="content">
          ${article.content}
        </div>
        
        <security:authorize access="!isAuthenticated()">
          <p class="secondary-text">Gostou deste artigo? N�o deixe de se <a href="${uriService.getFriendlyUri('/clients/new')}">cadastrar</a> para receber mais artigos sobre o esporte em primeira m�o.</p>
        </security:authorize>
      </div>
      
      <h4 class="page-header">Coment�rios</h4>
	  
	  <c:forEach var="comment" items="${article.articleComments}">
	    <div class="comment">
	      <p>${comment.content}</p>
	      <p class="client">Por <a href="${uriService.getFriendlyUri(comment.client.uri)}">${comment.client.firstName} ${comment.client.lastName}</a> em <fmt:formatDate value="${comment.createdAt}" pattern="dd/MM/yyyy HH:mm" /></p>
	    </div>
	  </c:forEach>
	  
	  <security:authorize access="isAuthenticated()">
	    <form action="/article-comments/new" method="post">
	      <input type="hidden" name="articleId" value="${article.id}">
	      <textarea rows="3" class="form-control" name="content" placeholder="Deixe seu coment�rio aqui" style="height:100px;" required></textarea>
	      <input type="submit" class="form-control btn btn-primary" value="salvar">
	    </form>
	  </security:authorize>
	  <security:authorize access="!isAuthenticated()">
	    <div class="article">
	      <div class="secondary-text">
	        <p>Quer deixar seu coment�rio? Efetue seu <a href="${uriService.getFriendlyUri('/login')}">login</a> ou, caso ainda n�o possua cadastro, <a href="${uriService.getFriendlyUri('/clients/new')}">crie uma conta</a>.</p>
	      </div>
	    </div>
	  </security:authorize>
	  
	  </div>
	  
	  <div class="col-sm-3 col-sm-pull-9">
        <h4 class="page-header">Leia mais</h4>
	    <c:import url="/WEB-INF/jsp/articles/_menu.jsp" />
	  </div>
	  
    </div>
    </div>
    
    <c:import url="/WEB-INF/jsp/shared/footer.jsp" />
    
  </body>
</html>