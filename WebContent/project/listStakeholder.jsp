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
						<td align="center" id = 'cel<%=stakeholder.getIdStakeholder()%>'><%=stakeholder.getIdStakeholder()%></td>
						<td class="sh-nome" align="center"><p><%=stakeholder.getNomeStakeholder()%></p>
							<input type="text" class="hidden" value="<%=stakeholder.getNomeStakeholder()%>"></input>
						</td>
						<td align="center">
							<form action="" method="post">
								<a class="edit" title="Editar"> <i class="glyphicon glyphicon-pencil"></i></a>
							</form>
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
$('.edit').click(function(){
	
	console.log("taqui")
	
	var pai = $(this).closest('.coluna-sh');
	
	console.log(pai)
	
	pai.find('.sh-nome p').hide();
	
	pai.find('.sh-nome input').removeClass('hidden');
	
	$(this).find("glyphicon").removeClass('glyphicon-pencil').addClass('glyphicon-ok');

});</script>
<jsp:include page="../template/foot.jsp"></jsp:include>