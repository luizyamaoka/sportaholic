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
              <small>Sua meta é nosso mote.</small>
            </h2>
          </div>
        </div>
        
        <h3>O que define um atleta? <small>Talento? Dedicação? Conquistas?</small></h3>
        
        <p>Deixemos de lado os traços de personalidade, características físicas ou acasos geográficos. Todo atleta é um produto de fatores que podem oscilar indefinidamente e generalizações diminuem a complexidade de tais indivíduos. Nesse mar de singularidades, ações ou atitudes não seriam capazes de unificar esse grupo.</p>
        <p>Um atleta se reconhece na <strong>busca incessante pelo aperfeiçoamento</strong>. A conquista de hoje é apenas um alicerce para a construção de algo ainda maior. Trata-se de uma mistura de adoração e desapego. Nossas vidas giram em torno de uma meta, mas apenas até que ela seja alcançada e outra tome seu lugar.</p>
        <p>A <strong>Sportaholic</strong> não existe como solução final. Atletas não se interessam por jornadas com um fim. Estamos aqui única e exclusivamente para ajudá-lo em sua próxima empreitada. <strong>Nossa fixação é sua próxima conquista</strong>.</p>
        <p>Para ajudá-lo a alcançar seus objetivos desenvolvemos um conteúdo especialmente para você, com <strong>novidades</strong>, <strong>dicas</strong>, <strong>curiosidades</strong> e muito mais. Não deixe de conferí-los na <a href="${uriService.getFriendlyUri('/articles')}">seção de artigos</a></p>
        
      
      </div>
    </div>
    
    <c:import url="/WEB-INF/jsp/shared/footer.jsp" />
    
  </body>
</html>