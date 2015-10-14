<div class="page-header">
   <h1>Arquivos no S3</h1>
 </div>

   <div class="row">
     <div class="col-sm-12 col-md-6">
    <div class="center-form">
      <form action="/amazon-s3-files/delete" method="post">
        <div class="form-group">
          <label for="fileNameInput" class="control-label col-sm-4 col-xs-12">Arquivo * </label>
          <div class="col-sm-8 col-xs-12">
	  <input class="form-control" name="fileName" id="fileNameInput" type="text" placeholder="Nome do arquivo" required />
	</div>
  </div>
        <div class="form-group">
    <div class="col-sm-offset-4 col-sm-8 col-xs-12">
            <input class="form-control btn btn-danger" type="submit" value="Deletar" />
          </div>
  </div>
      </form>
    </div>
  </div>
  <div class="col-sm-12 col-md-6">
    <div class="center-form">
      <form action="/amazon-s3-files/upload" method="post" enctype="multipart/form-data">
        <div class="form-group">
          <label for="targetFileNameInput" class="control-label col-sm-4 col-xs-12">Arquivo * </label>
          <div class="col-sm-8 col-xs-12">
	  <input class="form-control" name="targetFileName" id="targetFileNameInput" type="text" placeholder="Nome do arquivo" required />
	</div>
  </div>
  <div class="form-group">
          <label for="fileInput" class="control-label col-sm-4 col-xs-12">Selecionar arquivo * </label>
          <div class="col-sm-8 col-xs-12">
	  <input class="form-control" name="file" id="fileInput" type="file" placeholder="Arquivo" required />
	</div>
  </div>
        <div class="form-group">
    <div class="col-sm-offset-4 col-sm-8 col-xs-12">
            <input class="form-control btn btn-primary" type="submit" value="Upload" />
          </div>
  </div>
      </form>
    </div>
  </div>
</div>