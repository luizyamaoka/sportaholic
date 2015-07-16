<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<h1>Confirma��o de pedido</h1>

<div class="row" id="order-confirmation">
  <div class="col-sm-12 col-md-6">
    <p>Seu pedido <strong>${transactionId}</strong> foi finalizado com sucesso!</p>
    <p>Assim que o pagamento por aprovado pelo PagSeguro, realizaremos o envio do seu pedido.</p>
    <p>Desde j� a Sportaholic agradece a confian�a depositada em n�s.</p>
    <p>Caso haja alguma d�vida, problema, sugest�o, cr�tica ou elogio, por favor, n�o hesite em nos contatar atrav�s do e-mail <a href="mailto:sportaholicoficial@gmail.com">sportaholicoficial@gmail.com</a></p>
  </div>
  <div class="col-sm-12 col-md-6">
    <p>Enquanto seu pedido n�o chega, acompanhe a Sportaholic nas redes sociais.</p>
    <ul id="footer-social">
      <li><a href="http://www.facebook.com.br/sportaholicoficial" target="blank"><img src="<%=com.sportaholic.EnvironmentConstants.IMAGES_URL%>facebook.png" height="29px" width="29px" alt="facebook" /></a></li>
      <li><a href="http://plus.google.com/+SportaholicBrasil" target="blank"><img src="<%=com.sportaholic.EnvironmentConstants.IMAGES_URL%>gplus.png" height="29px" width="29px" alt="google plus" /></a></li>
      <li><a href="http://twitter.com/sportaholicBr" target="blank"><img src="<%=com.sportaholic.EnvironmentConstants.IMAGES_URL%>twitter.png" height="29px" width="29px" alt="twitter" /></a></li>
      <li><a href="http://instagram.com/sportaholicoficial" target="blank"><img src="<%=com.sportaholic.EnvironmentConstants.IMAGES_URL%>instagram.png" height="29px" width="29px" alt="instagram" /></a></li>
      <li><a href="http://www.youtube.com/channel/UCfRO7VrKyT8ZBSdm6AiHqew" target="blank"><img src="<%=com.sportaholic.EnvironmentConstants.IMAGES_URL%>youtube.png" height="29px" width="29px" alt="youtube" /></a></li>
    </ul>
    <br /><br />
    <p>E n�o deixe de conferir os �ltimos artigos preparados especialmente para voc�!</p>
  </div>
</div>

<h3 class="page-header">�ltimos artigos</h3>

<c:import url="/WEB-INF/jsp/articles/_articles.jsp" />
  
<nav>
  <ul class="pager">
    <li class="next"><a href="${uriService.getFriendlyUri('/articles')}">Ver mais <span aria-hidden="true">&rarr;</span></a></li>
  </ul>
</nav>
