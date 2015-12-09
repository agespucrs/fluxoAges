<%@page import="br.ages.crud.model.Projeto"%>
<%@page import="br.ages.crud.model.Usuario"%>
<%@page import="br.ages.crud.model.Stakeholder"%>
<%@page import="java.util.List"%>


<jsp:include page="../template/head.jsp"></jsp:include>
			
<!-- MODAL / POPUP -->
<jsp:include page="../template/modalProjeto.jsp"></jsp:include>

<div class="panel panel-primary">

	<div class="panel-heading text-center">Projetos</div>


	<div class="panel-body">

		<jsp:include page="/template/msg.jsp"></jsp:include>

		<div class="table-responsive">

			<table class="table table-hover table-striped table-bordered">

				<thead>
					<tr>
						<th style="text-align: center;">ID</th>
						<th style="text-align: center;">Nome</th>
						<th style="text-align: center;">Usuários</th>
						<th style="text-align: center;">Status</th>
						<th style="text-align: center;">Workspace</th>
						<th style="text-align: center;">Stakeholders</th>
						<th style="text-align: center;">Data de Início</th>
						<th style="text-align: center;">Data de Fim</th>
						<th style="text-align: center;">Data de Fim Previsto</th>
						<th colspan="3" style="text-align: center;">Ações</th>
					</tr>
				</thead>

				<tbody>
					<%
						
						List<Projeto> listaProjetos = (List<Projeto>) request.getAttribute("listaProjetos");
						for (Projeto projeto : listaProjetos) {
						
					%>

					<tr>
						<td align="center"><%=projeto.getIdProjeto()%></td>
						<td align="center"><%=projeto.getNomeProjeto()%></td>
						<td align="center">
							<button data-toggle="collapse" data-target="#usuarios<%=projeto.getIdProjeto()%>"><%=projeto.getUsuarios().size()%></button>
							<div id="usuarios<%=projeto.getIdProjeto()%>" class="collapse">
								<%
									List<Usuario> listUsuarios = projeto.getUsuarios();
										for (Usuario usuario : listUsuarios) {
								%>
								<div class="row">
									<div align="left" class="col-sm-10" >* <%=usuario.getNome()%></div>
								</div>
								<%
									}
								%>
							</div>
						</td>
						<td align="center"><%=projeto.getStatusProjeto()%></td>
						<td align="center"><%=projeto.getWorkspace()%></td>
						<td align="center">
							<button data-toggle="collapse" data-target="#stakeholders<%=projeto.getIdProjeto()%>"><%=projeto.getStakeholders().size()%></button>
							<div id="stakeholders<%=projeto.getIdProjeto()%>" class="collapse">
								<%
									List<Stakeholder> listStakeholders = projeto.getStakeholders();
									
									for (Stakeholder stakeholder : listStakeholders) {
											
								%>
								<div class="row">
									<div align="left" class="col-sm-10" >* <%=stakeholder.getNomeStakeholder()%></div>
								</div>
								<%
									}
								%>
							</div>
						</td>
						<%-- 	<td align="center"><%=projeto.getStakeholders()%></td>  --%>
						<td align="center"><%=projeto.getDataInicio()%></td>
						<td align="center"><%=projeto.getDataFim() == null ? "Não definido" : projeto.getDataFim() %></td>
						<td align="center"><%=projeto.getDataFimPrevisto()%></td>
						<td align="center">
						<form action="" method="post">
	            				<a href="" data-toggle="modal" data-id="<%=projeto.getIdProjeto() %>" data-projeto="<%=projeto.getNomeProjeto()%>" 
	            				data-target="#modalEditar" title="Editar"> <i class="glyphicon glyphicon-pencil"></i></a>
	            		</form>
	            		</td>
	            		
						<td align="center">
						<form action="" method="post">
	            				<a href="" data-toggle="modal" data-id="<%=projeto.getIdProjeto() %>" data-projeto="<%=projeto.getNomeProjeto()%>" 
	            				data-target="#modalUpload" title="Upload Arquivo Projeto"> <i class="glyphicon glyphicon-upload"></i></a>
	            		</form>
	            		</td>
	            		
	            		<td align="center">
	            			<form action="" method="post">
	            				<a href="" data-toggle="modal" data-id="<%=projeto.getIdProjeto() %>" data-projeto="<%=projeto.getNomeProjeto()%>" 
	            				data-target="#modalExcluir" title="Deletar"> <i class="glyphicon glyphicon-trash"></i></a>
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