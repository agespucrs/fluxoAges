<%@page import="br.ages.crud.model.Stakeholder"%>
<%@page import="java.util.List"%>
<jsp:include page="../template/head.jsp"></jsp:include>

<!-- MODAL / POPUP -->
<jsp:include page="../template/modalStakeholder.jsp"></jsp:include>



<div class="panel panel-primary">

	<div class="panel-heading text-center">Stakeholders</div>


	<div class="panel-body">

		<jsp:include page="/template/msg.jsp"></jsp:include>

		<div class="table-responsive">

			<table class="table table-hover table-striped table-bordered">

				<thead>
					<tr>
						<th style="text-align: center;">ID</th>
						<th style="text-align: center;">Nome</th>
						<th colspan="2" style="text-align: center;">Ações</th>
					</tr>
				</thead>

				<tbody>
					<%
							List<Stakeholder> listaStakeholders = (List<Stakeholder>) request.getAttribute("listaStakeholders");
							for (Stakeholder stakeholder : listaStakeholders) {
						%>

					<tr class="coluna-sh">
						<td align="center" class="sh-id" id='<%=stakeholder.getIdStakeholder()%>'><%=stakeholder.getIdStakeholder()%></td>
						<td class="sh-nome" align="center"><p><%=stakeholder.getNomeStakeholder()%></p>
						<form action="" method="post" class="form-edit">
							<input class="new-sh-nome hidden" type="text" value="<%=stakeholder.getNomeStakeholder()%>"></input>
						</form>
						</td>
						<td align="center">
							<i class="glyphicon glyphicon-ok pointer hidden" style="cursor:pointer"></i>
							<i class="glyphicon glyphicon-pencil pointer" style="cursor:pointer"></i>	
						</td>

						<td align="center">
							<form action="" method="post">
								<a href="" data-toggle="modal"
									data-id="<%=stakeholder.getIdStakeholder() %>"
									data-stakeholder="<%=stakeholder.getNomeStakeholder()%>"
									data-target="#modalExcluir" title="Deletar"> <i
									class="glyphicon glyphicon-trash"></i></a>
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