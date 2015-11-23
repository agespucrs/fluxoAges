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

					<tr>
						<td align="center"><%=stakeholder.getIdStakeholder()%></td>
						<td align="center"><%=stakeholder.getNomeStakeholder()%></td>
						<td align="center">
							<form action="" method="post">
								<a href="" data-toggle="modal"
									data-idStakeholder="<%=stakeholder.getIdStakeholder() %>"
									data-stakeholder="<%=stakeholder.getNomeStakeholder()%>"
									data-target="#modalEditar" title="Editar"> <i
									class="glyphicon glyphicon-pencil"></i></a>
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


<jsp:include page="../template/foot.jsp"></jsp:include>