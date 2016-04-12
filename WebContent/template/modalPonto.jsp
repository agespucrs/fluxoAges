<script>
$( document ).ready(function() {
	$('#modalPonto').on('show.bs.modal', function (event) {
	  	var botao = $(event.relatedTarget);

		var idPonto = botao.data('idPonto');
	  	
	  	$(this).find('.modal-title').text('Validar ponto');
	  	$(this).find('#modal-descricao').text('Selecione o Responsável e digite sua senha, para validar o Ponto ');
		var idPonto = botao.data('id');
	});
	
	});
	
function enviar() {
	var idResponsavel =  document.getElementById("responsavel").value;
	var senha =  document.getElementById("senha").value;
	$('#formPonto').attr('action',"main?acao=validaPontoHora&id_responsavel=" + idResponsavel+"&senha=" + senha+"&id_ponto=" + idPonto);
	$('#formPonto').submit();
};
	
</script>

	<div class="modal fade" id="modalValidar" role="dialog">
	var idPonto =  document.getElementById("modal-id_ponto").value;
	$('#formPonto').attr('action',"main?acao=validaPontoHora&id_responsavel=" + idResponsavel+"&senha=" + senha+"&id_ponto=" + idPonto);
	$('#formPonto').submit();
};
</script>

	<div class="modal fade" id="modalPonto" role="dialog">
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
		      		<form action="" method="post" id="formPonto">
	        		<p id="modal-id_ponto" readonly="readonly"/>
					<div class="form-group">
						<label class="form-label ages">Responsável</label> 
						<input class="form-control" id="responsavel" name="responsavel" value="${param.responsavel}"
						type="text" maxlength="120" required>
					
						<label class="form-label ages">Senha</label>
						<input class="form-control" id="senha" name="senha" value="${param.senha}"
							type="password" maxlength="120" required>
					</div>
						<button type="button" onclick="enviar()" class="btn btn-primary">Validar</button>
				</form>
		      	</div>
		    </div>
	  	</div>
	</div>