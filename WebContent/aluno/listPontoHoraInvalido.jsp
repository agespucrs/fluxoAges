<%@page import="java.util.ArrayList"%>
<%@page import="br.ages.crud.model.ResumoPonto"%>
<%@page import="br.ages.crud.model.Usuario"%>
<%@page import="java.util.List"%>
<jsp:include page="../template/head.jsp"></jsp:include>

<!-- MODAL / POPUP -->
<jsp:include page="../template/modalPonto.jsp"></jsp:include>

<div class="panel panel-primary">

	<div class="panel-heading text-center">Hora Ponto Alunos à Ser Validado</div>

	<jsp:include page="/template/msg.jsp"></jsp:include>

	<div class="panel-body">
		<div class="table-responsive">
			<table id="listaAlunos" class="table table-responsive table-striped table-hover table-condensed">
				<thead>
					<tr>
						<th style="text-align: center;">ID</th>
						<th style="text-align: center;">Nome</th>
						<th style="text-align: center;">Data Entrada</th>
						<th style="text-align: center;">Horas Dia</th>
						<th style="text-align: center;"></th>
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
						<td align="center">
							<form action="" method="post">
								<a href="" data-toggle="modal" data-idPonto=<%=ponto.getIdPonto()%> 
								data-target="#modalPonto" title="Clique para validar o ponto"> <i class="glyphicon glyphicon-pencil"></i></a>
						// ARRUMAR
							</form>
						</td>
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
	$(document).ready(function(){
	    $('#listaAlunos').dataTable({
	    	"language": {
	            "lengthMenu": "Mostrando _MENU_ registros por página",
	            "zeroRecords": "Sem registros - sorry",
	            "info": "Mostrando _PAGE_ de _PAGES_ páginas",
	            "infoEmpty": "Nenhum registros encontrados!",
	            "infoFiltered": "(Filtrado _MAX_ do total deregistros)",
	            "search": "Pesquisar",
	            "paginate": {
	                "first":      "Primeiro",
	                "last":       "Último",
	                "next":       "Próximo",
	                "previous":   "Anterior"
	            },
	        }
	    });
	});
</script>