<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
    
<h1 class="page-header">Todos os esportes</h1>

<p class="lead">Veja nesta seção todos os esportes que a Sportaholic trata no momento. Estamos trabalhando para trazer mais artigos e produtos de outros esportes especialmente para você. </p>

<div class="list-group">
  <c:forEach var="sport" items="${sports}">
    <a href="${uriService.getFriendlyUri(sport.getUri())}" class="list-group-item">
      <h4 class="list-group-item-heading">${sport.name}</h4>
      <p class="list-group-item-text">${sport.description}</p>
    </a>
  </c:forEach>
</div>

<p>Não encontrou o esporte que estava procurando? Mande um e-mail para <a href="mailto:sportaholicoficial@gmail.com">sportaholicoficial@gmail.com</a> com a sua sugestão de esporte.</p>
