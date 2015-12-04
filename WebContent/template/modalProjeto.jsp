
<script>
$( document ).ready(function() {
	$('#modalExcluir').on('show.bs.modal', function (event) {
		var botao = $(event.relatedTarget);
	  	var projeto = botao.data('projeto');
		var id = botao.data('id');
		
		$(this).find('.modal-title').text('Excluir projeto');
	  	$(this).find('#modal-descricao').text('Você realmente quer excluir o projeto ' + projeto + '?');
	  	+
		$('#formExcluir').attr('action', "main?acao=removeProjeto&id_projeto=" + id );
	});
	
	
	$('#modalEditar').on('show.bs.modal', function (event) {
	  	var botao = $(event.relatedTarget);
	  	var projeto = botao.data('projeto');
		var id = botao.data('id');
	  	
	  	$(this).find('.modal-title').text('Editar projeto');
	  	$(this).find('#modal-descricao').text('Você realmente deseja editar o projeto ' + projeto + '?');
	  	+
	  	$('#formEditar').attr('action', "main?acao=telaProjeto&id_projeto=" + id + "&isEdit=true");
	});
	
	$('#modalUpload').on('show.bs.modal', function (event) {
	  	var botao = $(event.relatedTarget);
	  	var projeto = botao.data('projeto');
		var id = botao.data('id');
	  	
	  	$(this).find('.modal-title').text('Upload Arquivo projeto');
	  	$(this).find('#modal-descricao').text('Você deseja realizar o upload de um novo arquivo para o projeto ' + projeto + '?');
	  	+
	  	$('#formUpload').attr('action', "main?acao=uploadArquivoProjeto&id_projeto=" + id );
	});
});
</script>

	<div class="modal fade" id="modalExcluir" role="dialog">
  		<div class="modal-dialog">
	  		<div class="modal-content">
	  		
	      		<div class="modal-header modal-ages">
		        	<button type="button" class="close" data-dismiss="modal">&times;</button>
		        	<h4 class="modal-title"></h4>
	      		</div>
	      		
		      	<div class="modal-body">
	        		<p id="modal-descricao"></p>
		      	</div>
		      	
		      		<div class="modal-footer">
		      		<form action="" method="post" id="formExcluir">
		      			<button type="button" class="btn btn-default" data-dismiss="modal">Fechar</button>
		      			<button type="submit" class="btn btn-primary">Excluir</button>
		      		</form>
		      	</div>
		      	
		    </div>
	  	</div>
	</div>
	
	<div class="modal fade" id="modalEditar" role="dialog">
  		<div class="modal-dialog">
	  		<div class="modal-content">
	  		
	      		<div class="modal-header modal-ages">
		        	<button type="button" class="close" data-dismiss="modal">&times;</button>
		        	<h4 class="modal-title"></h4>
	      		</div>
	      		
		      	<div class="modal-body">
	        		<p id="modal-descricao"></p>
		      	</div>
		      	
		      	<div class="modal-footer">
		      		<form action="" method="post" id="formEditar">
		      			<button type="button" class="btn btn-default" data-dismiss="modal">Fechar</button>
		      			<button type="submit" class="btn btn-primary">Editar</button>
		      		</form>
		      	</div>
		      	
		    </div>
	  	</div>
	</div>
	
	<div class="modal fade" id="modalUpload" role="dialog">
  		<div class="modal-dialog">
	  		<div class="modal-content">
	  		
	      		<div class="modal-header modal-ages">
		        	<button type="button" class="close" data-dismiss="modal">&times;</button>
		        	<h4 class="modal-title"></h4>
	      		</div>
	      		
		      	<div class="modal-body">
	        		<p id="modal-descricao"></p>
		      	</div>
		      	
		      	<div class="modal-footer">
		      		<form action="" method="post" id="formUpload">
		      			<button type="button" class="btn btn-default" data-dismiss="modal">Fechar</button>
		      			<button type="submit" class="btn btn-primary">Upload</button>
		      		</form>
		      	</div>
		      	
		    </div>
	  	</div>
	</div>