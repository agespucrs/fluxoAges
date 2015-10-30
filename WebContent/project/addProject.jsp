<%@page import="br.ages.crud.model.Stakeholder"%>
<%@page import="java.util.Arrays"%>
<%@page import="java.util.List"%>
<%@page import="br.ages.crud.model.Usuario"%>
<%@page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>


<script src="../js/maskedinput.js" type="text/javascript"></script>
<script src="./js/cadastro-projeto.js"></script>

<jsp:include page="../template/head.jsp"></jsp:include>

<div class="panel panel-primary panel-addUser">

	<div class="panel-heading text-center">Cadastro de Projeto</div>


	<div class="panel-body">

		<jsp:include page="/template/msg.jsp"></jsp:include>

		<div class="table-responsive">

			<form method="post" action="main?acao=adicionaProjeto" > <!-- enctype="multipart/form-data" -->

				<%-- <div class="form-group">
			           	<label class="form-label ages">Código do Projeto:</label>
			           	<input class="form-control" id="codigo" name="codigo" value="${param.codigo}" type="text" maxlength="120" disabled>
		            </div> --%>

				<div class="form-group">
					<label class="form-label ages">Nome: <span class="red">*</span></label> 
					<input class="form-control" id="nomeProjeto" name="nomeProjeto" value="${param.nomeProjeto}" type="text"	maxlength="120" required>
				</div>

				<div class="form-group">
					<label class="form-label ages">Status do Projeto: <span class="red">*</span></label> 
					<select class="form-control" id="statusProjeto" name="statusProjeto"
						required>
						<option value="ATIVO" <%="ATIVO".equals(request.getParameter("statusProjeto")) ? "selected" : ""%>>Ativo</option>
						<option value="INATIVO" <%="INATIVO".equals(request.getParameter("statusProjeto")) ? "selected" : ""%>>Inativo</option>
						<option value="CONCLUIDO" <%="CONCLUIDO".equals(request.getParameter("statusProjeto")) ? "selected" : ""%>>Concluído</option>
					</select>
				</div>
				<div class="form-group integrante ">
					<label class="form-label ages">Integrantes:</label>
					
					<!-- STAKEHOLDER -->
					<!-- http://davidstutz.github.io/bootstrap-multiselect/#faq -->
					<select id="select-stakeholders" name="listaStakeholders" multiple="multiple">
						<%
							List<Stakeholder> listaStakeholders = (List<Stakeholder>) request.getAttribute("listaStakeholders");
							for (Stakeholder stakeholder : listaStakeholders) {
						%>

						<option id="stakeholders" name="stakeholders" value="<%=stakeholder.getIdStakeholder()%>"><%=stakeholder.getNomeStakeholder()%></option>

						<%
							}
						%>
					</select> 
					<!-- USUARIOS -->
					<!-- http://www.virtuosoft.eu/code/bootstrap-duallistbox/ -->
					<div class="col-md-12">
						<select multiple="multiple" size="10" name="listaUsuarios" class="listaUsuarios">
						<%
							List<Usuario> listaUsuarios = (List<Usuario>) request.getAttribute("listaUsuarios");
							for (Usuario usuario : listaUsuarios) {
						%>
							<option value="<%=usuario.getIdUsuario()%>"><%=usuario.getNome()%></option>
						<%
							}
						%>
						</select>

					</div>
				</div>
				<div class="form-group">
					<label class="form-label ages">Workspace:</label> 
					<input class="form-control" id="workspace" name="workspace" value="${param.workspace}" type="text"
						maxlength="120" required>
				</div>

				<div class="form-group">
					<label class="form-label ages">Data de Início:</label> <input class="form-control" id="dataInicio" name="dataInicio" value="${param.dataInicio}"
						type="text" maxlength="10" placeholder="DD/MM/AAAA" required>
				</div>

				<div class="form-group">
					<label class="form-label ages">Data de Fim Prevista:</label> <input class="form-control" id="dataFimPrevista" name="dataFimPrevista"
						value="${param.dataFimPrevista}" type="text" maxlength="10" placeholder="DD/MM/AAAA" required>
				</div>

				<div class="form-group">
					<label class="form-label ages">Data de Fim:</label> <input class="form-control" id="dataFim" name="dataFim" value="${param.dataFim}" type="text"
						maxlength="10" placeholder="DD/MM/AAAA" required>
				</div>

				<div class="form-group">
					<label class="form-label ages">Arquivo: <span class="red">*</span></label> <input class="form-control" id="arquivo" name="arquivo" value="${param.arquivo}"
						type="file">
				</div>

				<hr>

				<p>
					Campos que contém <span>*</span> são obrigatórios
				</p>


				<div class="text-center">
					<input class="btn btn-warning limparUser pull-left" type="reset" value="Limpar"> <input class="btn btn-primary addUser pull-right" type="submit"
						value="Cadastrar">
				</div>
			</form>
		</div>
	</div>
</div>

<jsp:include page="/template/foot.jsp"></jsp:include>
<!-- Initialize the plugin: -->
<!-- http://davidstutz.github.io/bootstrap-multiselect/#faq -->
<script type="text/javascript">
	$(document).ready(function() {
		$('#select-stakeholders').multiselect({
			enableFiltering : true,
			includeSelectAllOption : true,
			maxHeight : 400,
			dropUp : true,
			nonSelectedText : 'Selecione os Stakeholders',
			nSelectedText : '- Stakeholders',
			numberDisplayed: 1
		});
	});
</script>
<!-- USUARIOS -->
<!-- http://www.virtuosoft.eu/code/bootstrap-duallistbox/ -->
<script>
	var demo2 = $('.listaUsuarios').bootstrapDualListbox({
		nonSelectedListLabel : 'Usuários',
		selectedListLabel : 'Usuários do Projeto',
		preserveSelectionOnMove : 'moved',
		moveOnSelect : false,
		nonSelectedFilter : '',
		filterTextClear : 'Mostrar Todos',
		infoTextEmpty : 'Sem usuarios '
	});
</script>