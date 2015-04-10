<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<h1 class="page-header">Todas as marcas</h1>

<p class="lead">Veja nesta seção todas as marcas que a Sportaholic trata no momento. Estamos trabalhando para trazer mais produtos de outras marcas especialmente para você. </p>

<div class="row brands-list">
  <c:forEach var="brand" items="${brands}">
    <div class="col-md-3 col-sm-4 col-xs-12 brand-logo">
      <a href="${uriService.getFriendlyUri(brand.getUri())}">
        <img src="<%=com.sportaholic.EnvironmentConstants.IMAGES_URL%>${brand.logo}" alt="${brand.name}"/>
      </a>
    </div>
  </c:forEach>
</div>

<p style="margin-top: 40px;">Não encontrou a marca que estava procurando? Mande um e-mail para <a href="mailto:sportaholicoficial@gmail.com">sportaholicoficial@gmail.com</a> com a sua sugestão de marca.</p>
