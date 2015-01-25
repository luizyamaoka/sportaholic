<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page import="com.sportaholic.model.UrlConstants" %>

<html>
  <head>
    <meta charset="utf-8">
    <link rel='shortcut icon' type='image/x-icon' href='/favicon.ico'/>
    <meta name="description" content="${activeUri.metaDescription}">
    <title>Sportaholic | Your goal is our device</title>
    <c:import url="/WEB-INF/jsp/shared/js.jsp" />
    <c:import url="/WEB-INF/jsp/shared/css.jsp" />
  </head>
  <body>
    <c:import url="/WEB-INF/jsp/shared/tags.jsp" />
    
    <c:import url="/WEB-INF/jsp/shared/header.jsp" />
    
    <div class="container theme-showcase" role="main">
    
      <c:import url="/WEB-INF/jsp/shared/alert.jsp" />
      <c:import url="/WEB-INF/jsp/shared/breadcrumb.jsp" />

      <div id="about-us">
        
        <div class="row">
          <div class="col-xs-12 col-sm-6">
            <h1 style="margin-bottom: 40px;">
              <img src="https://s3-sa-east-1.amazonaws.com/sportaholic/images/logo.png" style="max-height: 70px; max-width: 80%;">
            </h1>
          </div>
          <div class="col-xs-12 col-sm-6">
            <h2>
              Your goal is our device.<br />
              <small>Sua meta � nosso mote.</small>
            </h2>
          </div>
        </div>
        
        <h3>O que define um atleta? <small>Talento? Dedica��o? Conquistas?</small></h3>
        
        <p>Deixemos de lado os tra�os de personalidade, caracter�sticas f�sicas ou acasos geogr�ficos. Todo atleta � um produto de fatores que podem oscilar indefinidamente e generaliza��es diminuem a complexidade de tais indiv�duos. Nesse mar de singularidades, a��es ou atitudes n�o seriam capazes de unificar esse grupo.</p>
        <p>Um atleta se reconhece na <strong>busca incessante pelo aperfei�oamento</strong>. A conquista de hoje � apenas um alicerce para a constru��o de algo ainda maior. Trata-se de uma mistura de adora��o e desapego. Nossas vidas giram em torno de uma meta, mas apenas at� que ela seja alcan�ada e outra tome seu lugar.</p>
        <p>A <strong>Sportaholic</strong> n�o existe como solu��o final. Atletas n�o se interessam por jornadas com um fim. Estamos aqui �nica e exclusivamente para ajud�-lo em sua pr�xima empreitada. <strong>Nossa fixa��o � sua pr�xima conquista</strong>.</p>
        <p>Para ajud�-lo a alcan�ar seus objetivos desenvolvemos um conte�do especialmente para voc�, com <strong>novidades</strong>, <strong>dicas</strong>, <strong>curiosidades</strong> e muito mais. N�o deixe de confer�-los na <a href="${uriService.getFriendlyUri('/articles')}">se��o de artigos</a></p>
        
      
      </div>
    </div>
    
    <c:import url="/WEB-INF/jsp/shared/footer.jsp" />
    
  </body>
</html>