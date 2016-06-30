<%@page import="java.util.ArrayList"%>
<%@page import="br.ages.crud.model.Ponto"%>
<%@page import="br.ages.crud.util.Util"%>
<%@page import="br.ages.crud.model.Usuario"%>
<%@page import="java.util.List"%>
<jsp:include page="../template/head.jsp"></jsp:include>

<!-- MODAL / POPUP -->
<jsp:include page="../template/modalAluno.jsp"></jsp:include>
<div class="panel panel-primary">

	<div class="panel-heading text-center">Hora Ponto Alunos</div>

	<jsp:include page="/template/msg.jsp"></jsp:include>

	<div class="panel-body">
		<form id="formAluno" method="post">
		<div class="form-group row">
		<div class='col-sm-6' id='nomeAluno'>
			<label for="sel1" class="form-label ages">Aluno:<span class="red">*</span></label> 
			<select class="form-control" id="idAluno" name="idAluno" onchange="listar()" > 
				<option value="0">Selecione um aluno</option>
				<%
					String totalHorasAluno = (String) request.getAttribute("totalHorasAluno");
					List<Usuario> listaUsuarios = (List<Usuario>) request.getAttribute("usuarios");
					for (Usuario u : listaUsuarios) {
				%>
				<option value="<%=u.getIdUsuario()%>"<%=(u.getNome()).equals(request.getParameter("idAluno")) ? "selected" : "" %>><%=u.getNome()%></option>
				<%
					}
				%>
			</select>
		</div>
		</div>
		</form>
		<form id="formListAluno" method="post">
			<div class="table-responsive">
				<table id="listaAlunos" class="table table-responsive table-striped table-hover table-condensed">
					<thead>
						<tr>
							<th style="text-align: center;">Nome Aluno</th>
							<th style="text-align: center;">Data Entrada</th>
							<th style="text-align: center;">Data Saída</th>
							<th style="text-align: center;">Responsável</th>
							<th style="text-align: center;">Status</th>
							<th style="text-align: center;"></th>
							<th style="text-align: center;"></th>
						</tr>
					</thead>

					<tbody>
						<%
							ArrayList<Ponto> Pontos = (ArrayList<Ponto>) request.getAttribute("listaAlunos");
							for (Ponto ponto : Pontos) {
						%>

						<tr class="coluna-sh">
							<td align="center"><%=ponto.getAluno().getNome()%></td>
							<td align="center"><%=Util.dateTimeToString(ponto.getDataEntrada())%></td>
							<td align="center"><%=Util.dateTimeToString(ponto.getDataSaida()) == null ?  "--" :  Util.dateTimeToString(ponto.getDataSaida())%></td>
							<td align="center"><%=ponto.getResponsavel().getNome()%></td>
							<td align="center"><%=ponto.getStatus().name()%></td>
							<td align="center">
								<form action="" method="post">
									<a href="" data-toggle="modal" data-id="<%=ponto.getIdPonto()%>" data-usuario="<%=ponto.getAluno().getNome()%>" data-target="#modalEditar"
										title="Editar"> <i class="glyphicon glyphicon-pencil"></i></a>
								</form>
							</td>

							<td align="center">
								<form action="" method="post">
									<a href="" data-toggle="modal" data-id="<%=ponto.getIdPonto()%>" data-usuario="<%=ponto.getAluno().getNome()%>" data-target="#modalExcluir"
										title="Deletar"> <i class="glyphicon glyphicon-trash"></i></a>
								</form>
							</td>
						</tr>

						<%
							}
						%>
					</tbody>

				</table>

			</div>

		</form>
	</div>

</div>
<jsp:include page="../template/foot.jsp"></jsp:include>
<script>
	function listar() {
		var id = document.getElementById("idAluno").value;
		document.forms[0].action = 'main?acao=listaPontoHora&id_usuario=' + id;
		document.forms[0].submit();
		winconsole.log(id);
	};
</script>
<script>
	$(document).ready(function() {
		$('#listaAlunos').dataTable({
			"language" : {
				"lengthMenu" : "Mostrando _MENU_ registros por página",
				"zeroRecords" : "Sem registros - sorry",
				"info" : "Mostrando _PAGE_ de _PAGES_ páginas",
				"infoEmpty" : "Nenhum registros encontrados!",
				"infoFiltered" : "(Filtrado _MAX_ do total deregistros)",
				"search" : "Pesquisar",
				"paginate" : {
					"first" : "Primeiro",
					"last" : "Último",
					"next" : "Próximo",
					"previous" : "Anterior"
				},
			}
		});
	});
</script>