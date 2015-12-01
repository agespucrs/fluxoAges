<%@page import="java.util.ArrayList"%>
<%@page import="br.ages.crud.model.ResumoPonto"%>
<%@page import="br.ages.crud.model.Usuario"%>
<%@page import="java.util.List"%>
<jsp:include page="../template/head.jsp"></jsp:include>


<div class="panel panel-primary">

	<div class="panel-heading text-center">Hora Ponto Alunos</div>

	<jsp:include page="/template/msg.jsp"></jsp:include>

	<div class="panel-body">
		<form id="formListAluno" action="">
		<div class='' id='nomeAluno'>
			<label for="sel1" class="form-label ages">Aluno:<span class="red">*</span></label> 
			<select class="form-control" id="idAluno" name="idAluno" >
				<%
					List<Usuario> listaUsuarios = (List<Usuario>) request.getAttribute("usuarios");
					for (Usuario u : listaUsuarios) {
				%>
				<option value="<%=u.getIdUsuario()%>"><%=u.getNome()%></option>
				<%
					}
				%>
			</select>
		</div>
		</form>
		<div class="table-responsive">

			<table class="table table-hover table-striped table-bordered" id="listaAlunos">

				<thead>
					<tr>
						<th style="text-align: center;">ID</th>
						<th style="text-align: center;">Nome</th>
						<th style="text-align: center;">Data Entrada</th>
						<th style="text-align: center;">Total Horas</th>
					</tr>
				</thead>

				<tbody>
					<%
						ArrayList<ResumoPonto> listaPontos = (ArrayList<ResumoPonto>) request.getAttribute("listaPontos");
						for (ResumoPonto ponto : listaPontos) {
					%>

					<tr class="coluna-sh">
						<td align="center" class="sh-id"><%=ponto.getIdPonto()%></td>
						<td align="center"><%=ponto.getNomeAluno()%></td>
						<td align="center"><%=ponto.getDataEtrada()%></td>
						<td align="center"><%=ponto.getHoraEntrada()%></td>
					</tr>

					<%
						}
					%>
				</tbody>

			</table>

		</div>

	</div>

</div>

<script>
	$("#idAluno").change(
			function() {
				var id = this.value;
				var formPai = $("#formListAluno");
				formPai.attr("action","main?acao=listaPontoHora&id_usuario=" + id).submit;
				console.log("main?acao=listaPontoHora&id_usuario=" + id);
			});
	
	
</script>
<jsp:include page="../template/foot.jsp"></jsp:include>