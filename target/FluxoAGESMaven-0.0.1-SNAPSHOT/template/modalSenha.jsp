
<script>
	$(document).ready(
			function() {
				$('#modalSenha').on(
						'show.bs.modal',
						function(event) {
							var botao = $(event.relatedTarget);
							var usuario = botao.data('login');
							//var id = botao.data('id');

							$(this).find('.modal-title').text(
									'Recuperar Senha ');
							$(this).find('#modal-descricao').text(
									'Digite Login e o E-Mail do usuário');

						
						});
			});
	
	function enviar() {
		var login =  document.getElementById("login").value;
		var email =  document.getElementById("email").value;
		$('#formSenha').attr('action',"main?acao=recuperarSenha&login="+login+"&email="+email);
		$('#formSenha').submit();
	};
</script>

<div class="modal fade" id="modalSenha" role="dialog">
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
				<form action="" method="post" id="formSenha">
					<div class="form-group">
						<label class="form-label ages">Login </label> 
						<input class="form-control" id="login" name="login" value="${param.login}"
						type="text" maxlength="120" required>
					</div>
					<div class="form-group">
						<label class="form-label ages"> E-Mail </label>
						<input class="form-control" id="email" name="email" value="${param.email}"
							type="text" maxlength="120" required>
					</div>
					<button type="button" class="btn btn-default" data-dismiss="modal">Fechar</button>
					<button type="button" onclick="enviar()" class="btn btn-primary">Enviar</button>
				</form>
			</div>

		</div>
	</div>
</div>