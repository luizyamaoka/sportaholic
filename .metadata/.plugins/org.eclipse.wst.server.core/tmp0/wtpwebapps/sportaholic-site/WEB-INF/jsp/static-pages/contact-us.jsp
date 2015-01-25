<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page import="com.sportaholic.model.UrlConstants" %>

<html>
  <head>
    <meta charset="utf-8">
    <link rel='shortcut icon' type='image/x-icon' href='/favicon.ico'/>
    <meta name="description" content="${activeUri.metaDescription}">
    <title>Contato | Sportaholic</title>
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
          <div class="col-sm-12">
            <h1 style="margin-bottom: 40px;">
              <img src="https://s3-sa-east-1.amazonaws.com/sportaholic/images/logo.png" style="max-height: 70px; max-width: 80%;">
            </h1>
          </div>
        </div>
        
        <h3>Entre em contato conosco</h3>
        
        <p>Tudo que desenvolvemos aqui é feito especialmente para você, esperamos que tenha gostado. Caso tenha alguma dúvida, sugestão, crítica ou simplesmente palavras de incentivo, não hesite e entre em contato conosco através do e-mail <a href="mailto:sportaholicoficial@gmail.com">sportaholicoficial@gmail.com</a>.</p>
        <p>Ficaremos muito felizes com o seu contato e responderemos o mais breve possível.</p>
      
      </div>
    </div>
    
    <c:import url="/WEB-INF/jsp/shared/footer.jsp" />
    
  </body>
</html>