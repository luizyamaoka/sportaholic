<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="security" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

    
    <meta name="author" content="${article.author.name}">
    <div id="fb-root"></div>
    <script>(function(d, s, id) {
      var js, fjs = d.getElementsByTagName(s)[0];
      if (d.getElementById(id)) return;
      js = d.createElement(s); js.id = id;
      js.src = "//connect.facebook.net/pt_BR/sdk.js#xfbml=1&version=v2.0";
      fjs.parentNode.insertBefore(js, fjs);
      }(document, 'script', 'facebook-jssdk'));
    </script>

    <div class="row">

      <div class="col-sm-9 col-sm-push-3">
      <div class="article" itemscope itemtype="http://schema.org/Article">
	    <h1 itemprop="name">${article.title}</h1>
	    <h3>${article.subtitle}</h3>
	    
	    <div class="author">
	      Por <a href="${uriService.getFriendlyUri(article.author.uri)}"><span itemprop="author" itemscope itemtype="http://schema.org/Person">${article.author.name}</span></a>
	      em <span itemprop="datePublished" content="<fmt:formatDate value="${article.publishedAt}" pattern="yyyy-MM-dd" timeZone="Brazil/East"/>T<fmt:formatDate value="${article.publishedAt}" pattern="HH:mm" timeZone="Brazil/East"/>"><fmt:formatDate value="${article.publishedAt}" pattern="dd/MM/yyyy HH:mm" timeZone="Brazil/East"/></span>
	    </div>
	    <br />
	    <div class="fb-like" data-href="http://www.sportaholic.com.br${uriService.getFriendlyUri(article.uri)}" 
        data-layout="button" data-action="like" data-show-faces="true" data-share="true"></div>
		<br /><br />
		<div class="content" itemprop="articleBody">
          ${article.content}
        </div>
        
        <security:authorize access="!isAuthenticated()">
          <p class="secondary-text">Gostou deste artigo? Não deixe de se <a href="${uriService.getFriendlyUri('/clients/new')}">cadastrar</a> para receber mais artigos sobre o esporte em primeira mão.</p>
        </security:authorize>
      </div>
      
      <h4 class="page-header">Comentários</h4>
	  
	  <c:forEach var="comment" items="${article.articleComments}">
	    <div class="comment">
	      <p>${comment.content}</p>
	      <p class="client">Por <a href="${uriService.getFriendlyUri(comment.client.uri)}">${comment.client.firstName} ${comment.client.lastName}</a> em <fmt:formatDate value="${comment.createdAt}" pattern="dd/MM/yyyy HH:mm" /></p>
	    </div>
	  </c:forEach>
	  
	  <security:authorize access="isAuthenticated()">
	    <form action="/article-comments/new" method="post">
	      <input type="hidden" name="articleId" value="${article.id}">
	      <textarea rows="3" class="form-control" name="content" placeholder="Deixe seu comentário aqui" style="height:100px;" required></textarea>
	      <input type="submit" class="form-control btn btn-primary" value="salvar">
	    </form>
	  </security:authorize>
	  <security:authorize access="!isAuthenticated()">
	    <div class="article">
	      <div class="secondary-text">
	        <p>Quer deixar seu comentário? Efetue seu <a href="${uriService.getFriendlyUri('/login')}">login</a> ou, caso ainda não possua cadastro, <a href="${uriService.getFriendlyUri('/clients/new')}">crie uma conta</a>.</p>
	      </div>
	    </div>
	  </security:authorize>
	  
	  </div>
	  
	  <div class="col-sm-3 col-sm-pull-9">
        <h4 class="page-header">Leia mais</h4>
	    <c:import url="/WEB-INF/jsp/articles/_menu.jsp" />
	  </div>
	  
    </div>