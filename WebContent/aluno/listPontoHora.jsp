<%@page import="java.util.ArrayList"%>
<%@page import="br.ages.crud.model.ResumoPonto"%>
<%@page import="java.util.List"%>
<jsp:include page="../template/head.jsp"></jsp:include>


<div class="panel panel-primary">

	<div class="panel-heading text-center">Hora Ponto Alunos</div>


	<div class="panel-body">

		<jsp:include page="/template/msg.jsp"></jsp:include>

		<div class="table-responsive">

			<table class="table table-hover table-striped table-bordered">

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
							for (ResumoPonto ponto: listaPontos) {
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

$(".glyphicon-ok").click(function(){
	
	console.log("pai")
	
	var pai = $(this).parent().parent();
	
	console.log(pai);
	
	var nome = pai.find('.new-sh-nome').val();
	
	var id = pai.find('.sh-id').attr('id');

	console.log(nome, id);
	//if (nome != pai.find('.sh-nome p').val()){
		pai.find('.form-edit').prop('action', "main?acao=editaStakeholder&id_sh=" + id + "&nome=" + nome).submit();
	//}
	
	
});


$('.glyphicon-pencil').click(function(){
	
	var pai = $(this).parent().parent();
	
	console.log(pai)
	
	pai.find('.sh-nome p').hide();
	
	pai.find('.sh-nome input').removeClass('hidden');
	
	$(this).hide();
	
	pai.find(".glyphicon-ok").removeClass("hidden");	

});



</script>
<jsp:include page="../template/foot.jsp"></jsp:include>