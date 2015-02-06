<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<div class="center-form">
        <form:form class="form-horizontal" modelAttribute="articleDto">
          <div class="form-group">
            <label for="titleInput" class="control-label col-sm-4 col-xs-12">Título * </label>
            <div class="col-sm-8 col-xs-12">
			  <form:input path="title" class="form-control" id="titleInput" placeholder="Título" required="true" autofocus="true" />
			</div>
		  </div>
		  <div class="form-group">
            <label for="subtitleInput" class="control-label col-sm-4 col-xs-12">Subtítulo * </label>
            <div class="col-sm-8 col-xs-12">
			  <form:textarea rows="3" path="subtitle" class="form-control" id="subtitleInput" placeholder="Subtítulo" required="true" />
			</div>
		  </div>
		  <div class="form-group">
            <label for="contentInput" class="control-label col-sm-4 col-xs-12">Conteúdo * </label>
            <div class="col-sm-8 col-xs-12">
			  <form:textarea rows="20" path="content" class="form-control" id="contentInput" placeholder="Conteúdo em HTML, usar tags p, strong, a, ul, li" required="true" />
			</div>
		  </div>
		  <div class="form-group">
            <label for="authorIdOptions" class="control-label col-sm-4 col-xs-12">Autor * </label>
            <div class="col-sm-8 col-xs-12">
			  <form:select path="authorId" class="form-control" id="authorIdOptions" required="true">
			    <form:option value="">Selecione um autor</form:option>
			    <c:forEach var="author" items="${authors}">
			      <form:option value="${author.id}">${author.name}</form:option>
			    </c:forEach>
		      </form:select>
			</div>
		  </div>
		  <div class="form-group">
		    <label for="publishedAtInput" class="control-label col-sm-4 col-xs-12">Data da publicação * </label>
            <div class="col-sm-8 col-xs-12">
              <div class="input-group">
			    <form:input path="publishedAtDay" class="form-control" id="publishedAtDayInput" placeholder="DD" required="true" />
			    <span class="input-group-addon">/</span>
			    <form:input path="publishedAtMonth" class="form-control" id="publishedAtMonthInput" placeholder="MM" required="true" />
			    <span class="input-group-addon">/</span>
			    <form:input path="publishedAtYear" class="form-control" id="publishedAtYearInput" placeholder="YYYY" required="true" />
			  </div>
			</div>
		  </div>
		  <div class="form-group">
		    <label for="publishedAtInput" class="control-label col-sm-4 col-xs-12">Horário da publicação * </label>
            <div class="col-sm-8 col-xs-12">
              <div class="input-group">
			    <form:input path="publishedAtHour" class="form-control" id="publishedAtHourInput" placeholder="HH" required="true" />
			    <span class="input-group-addon">:</span>
			    <form:input path="publishedAtMinute" class="form-control" id="publishedAtMinuteInput" placeholder="mm" required="true" />
			  </div>
			</div>
		  </div>
		  <div class="form-group">
            <label for="sportIdsCheckbox" class="control-label col-sm-4 col-xs-12">Esportes * </label>
            <div class="col-sm-8 col-xs-12">
			  <c:forEach var="sport" items="${sports}">
			    <form:checkbox path="sportIds" value="${sport.id}" />${sport.name}
			  </c:forEach>
			</div>
		  </div>
		  <div class="form-group">
            <label for="articleTypeIdsCheckbox" class="control-label col-sm-4 col-xs-12">Tipos de artigo * </label>
            <div class="col-sm-8 col-xs-12">
			  <c:forEach var="articleType" items="${articleTypes}">
			    <form:checkbox path="articleTypeIds" value="${articleType.id}" />${articleType.name}
			  </c:forEach>
			</div>
		  </div>
	      <form:hidden path="uriId" class="form-control" id="uriIdInput"  />
		  <div class="form-group">
            <label for="friendlyUriInput" class="control-label col-sm-4 col-xs-12">Url amigável * </label>
            <div class="col-sm-8 col-xs-12">
			  <form:input path="friendlyUri" class="form-control" id="friendlyUriInput" placeholder="Url amigável" required="true" />
			</div>
		  </div>
		  <div class="form-group">
            <label for="nameInput" class="control-label col-sm-4 col-xs-12">Nome da página * </label>
            <div class="col-sm-8 col-xs-12">
			  <form:input path="name" class="form-control" id="nameInput" placeholder="Nome da página (breadcrumbs)" required="true" />
			</div>
		  </div>
		  <div class="form-group">
            <label for="parentIdOptions" class="control-label col-sm-4 col-xs-12">Url pai * </label>
            <div class="col-sm-8 col-xs-12">
			  <form:select path="parentId" class="form-control" id="parentIdOptions" required="true">
			    <form:option value="">Selecione uma Url pai</form:option>
			    <c:forEach var="uri" items="${uris}">
			      <form:option value="${uri.id}">${uri.friendlyUri}</form:option>
			    </c:forEach>
		      </form:select>
			</div>
		  </div>
		  <div class="form-group">
            <label for="metaDescriptionInput" class="control-label col-sm-4 col-xs-12">Meta Description * </label>
            <div class="col-sm-8 col-xs-12">
			  <form:textarea rows="3" path="metaDescription" class="form-control" id="metaDescriptionInput" placeholder="Meta Description" required="true" />
			</div>
		  </div>
		  <div class="form-group">
		    <div class="col-sm-offset-4 col-sm-8 col-xs-12">
              <input class="form-control btn btn-primary" type="submit" value="Atualizar" />
            </div>
		  </div>
		
      </form:form>
      </div>