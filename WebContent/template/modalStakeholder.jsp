
<script>
$( document ).ready(function() {
	$('#modalExcluir').on('show.bs.modal', function (event) {
	  	var botao = $(event.relatedTarget);
	  	var usuario = botao.data('stakeholder');
		var idStakeholder = botao.data('idStakeholder');
	  	
	  	$(this).find('.modal-title').text('Excluir stakeholder');
	  	$(this).find('#modal-descricao').text('Você realmente deseja excluir o stakeholder ('+idStakeholder + ' id ' + stakeholder + ')?');
	  	
	  	$('#formExcluir').attr('action', "main?acao=removerStakeholder&idStakeholder=" + idStakeholder);
	});
	
	
	$('#modalEditar').on('show.bs.modal', function (event) {
	  	var botao = $(event.relatedTarget);
	  	var stakeholder = botao.data('stakeholder');
		var idStakeholder = botao.data('idStakeholder');
	  	
	  	$(this).find('.modal-title').text('Editar stakeholder');
	  	$(this).find('#modal-descricao').text('Você realmente deseja editar o stakeholder (' + stakeholder + ')?');
	  	+
	  	$('#formEditar').attr('action', "main?acao=telaStakeholder&idStakeholder=" + idStakeholder + "&isEdit=true");
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