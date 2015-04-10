<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

  <div class="center-form">
     <form:form class="form-horizontal" action="edit" modelAttribute="clientDto">
       <div class="form-group">
         <label for="firstNameInput" class="control-label col-sm-4 col-xs-12">Nome * </label>
         <div class="col-sm-8 col-xs-12">
  <form:input path="firstName" class="form-control" id="firstNameInput" placeholder="Nome" required="true" autofocus="true" />
</div>
 </div>
 <div class="form-group">
         <label for="lastNameInput" class="control-label col-sm-4 col-xs-12">Sobrenome</label>
         <div class="col-sm-8 col-xs-12">
  <form:input path="lastName" class="form-control" id="lastNameInput" placeholder="Sobrenome" />
</div>
 </div>
 <div class="form-group">
         <label for="birthDayInput" class="control-label col-sm-4 col-xs-12">Data de nascimento </label>
         <div class="col-sm-8 col-xs-12">
           <div class="input-group">
  <form:input path="birthDay" class="form-control" id="birthDayInput" placeholder="DD" />
  <span class="input-group-addon">/</span>
  <form:input path="birthMonth" class="form-control" id="birthMonthInput" placeholder="MM" />
  <span class="input-group-addon">/</span>
  <form:input path="birthYear" class="form-control" id="birthYearInput" placeholder="YYYY" />
  </div>
</div>
 </div>
 <div class="form-group">
         <label for="genderOptions" class="control-label col-sm-4 col-xs-12">Sexo </label>
         <div class="col-sm-8 col-xs-12">
  <form:select path="gender" class="form-control" id="genderOptions">
    <form:option value="">Selecione seu sexo</form:option>
    <form:option value="m">Masculino</form:option>
    <form:option value="f">Feminino</form:option>
     </form:select>
</div>
 </div>
 <div class="form-group">
         <label for="emailInput" class="control-label col-sm-4 col-xs-12">E-mail * </label>
         <div class="col-sm-8 col-xs-12">
  <form:input path="email" class="form-control" id="emailInput" placeholder="E-mail" required="true" />
</div>
 </div>
 <div class="form-group">
   <div class="col-sm-offset-4 col-sm-8 col-xs-12">
           <input class="form-control btn btn-primary" type="submit" value="Atualizar" />
         </div>
 </div>

   </form:form>
   </div>