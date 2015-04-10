<div class="center-form">
     <form class="form-horizontal" action="/clients/edit-password" method="post">
       <div class="form-group">
         <label for="firstNameInput" class="control-label col-sm-4 col-xs-12">Senha atual</label>
         <div class="col-sm-8 col-xs-12">
  <input name="password" type="password" class="form-control" id="passwordInput" placeholder="Senha atual" required autofocus />
</div>
 </div>
 <div class="form-group">
         <label for="newPasswordInput" class="control-label col-sm-4 col-xs-12">Nova senha</label>
         <div class="col-sm-8 col-xs-12">
  <input name="newPassword" type="password" class="form-control" id="newPasswordInput" placeholder="Nova senha" required />
</div>
 </div>
 <div class="form-group">
         <label for="newPasswordConfirmationInput" class="control-label col-sm-4 col-xs-12">Confirmação da nova senha</label>
         <div class="col-sm-8 col-xs-12">
  <input name="newPasswordConfirmation" type="password" class="form-control" id="newPasswordConfirmationInput" placeholder="Repita a nova senha" required />
</div>
 </div>

 <div class="form-group">
   <div class="col-sm-offset-4 col-sm-8 col-xs-12">
           <input class="form-control btn btn-primary" type="submit" value="Atualizar" />
         </div>
 </div>

     </form>
   </div>