<%@page import="java.util.ArrayList"%>
<%@page import="br.ages.crud.model.ResumoPonto"%>
<%@page import="br.ages.crud.model.Usuario"%>
<%@page import="java.util.List"%>
<jsp:include page="../template/head.jsp"></jsp:include>


<div class="panel panel-primary">

	<div class="panel-heading text-center">Hora Ponto Alunos</div>

	<jsp:include page="/template/msg.jsp"></jsp:include>

	<div class="panel-body">
		<form id="formListAluno" method="post">
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
		<div class="table-responsive">
			<table id="listaAlunos" class="table table-responsive table-striped table-hover table-condensed">
				<thead>
					<tr>
						<th style="text-align: center;">Total Horas</th>
						<th style="text-align: center;"><%=totalHorasAluno %></th>
					</tr>
					<tr>
						<th style="text-align: center;">ID</th>
						<th style="text-align: center;">Nome</th>
						<th style="text-align: center;">Data Entrada</th>
						<th style="text-align: center;">Horas Dia</th>
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
						<td align="center"><%=ponto.getHoraTotalDia()%></td>
					</tr>

					<%
						}
					%>
				</tbody>

			</table>

		</div>

	</div>

</div>

<jsp:include page="../template/foot.jsp"></jsp:include>
<script>
	function listar() {
			var id =  document.getElementById("idAluno").value;
			 document.forms[0].action= 'main?acao=listaPontoHora&id_usuario=' + id;
			 document.forms[0].submit();
			 winconsole.log(id);
		};
</script>
<script>
	
	$(document).ready(function(){
	    $('#listaAlunos').dataTable({
	    	"language": {
	            "lengthMenu": "Mostrando _MENU_ registros por p�gina",
	            "zeroRecords": "Sem registros - sorry",
	            "info": "Mostrando _PAGE_ de _PAGES_ p�ginas",
	            "infoEmpty": "Nenhum registros encontrados!",
	            "infoFiltered": "(Filtrado _MAX_ do total deregistros)",
	            "search": "Pesquisar",
	            "paginate": {
	                "first":      "Primeiro",
	                "last":       "�ltimo",
	                "next":       "Pr�ximo",
	                "previous":   "Anterior"
	            },
	        }
	    });
	});
</script>