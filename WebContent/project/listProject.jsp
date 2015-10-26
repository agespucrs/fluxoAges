<%@page import="br.ages.crud.model.Projeto"%>
<%@page import="java.util.List"%>

<script type="text/javascript">
	function remover(id) {to
		var resposta = confirm("Deseja remover o usuario " + id);

		if (resposta == true) {
			var formCadastro = document.forms[0];
			formCadastro.action = "main?acao=removerProjeto&id_projeto=" + id;
			formCadastro.submit();
		}
	}
</script>

<jsp:include page="../template/head.jsp"></jsp:include>

	<div class="panel panel-primary">
    		
		<div class="panel-heading text-center">
			Projetos
		</div>
         
                
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
							<th colspan="2" style="text-align: center;">Ações</th>
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
			            	<td align="center"><%=projeto.getUsuarios()%></td>
			            	<td align="center"><%=projeto.getStatusProjeto()%></td>
			            	<td align="center"><%=projeto.getWorkspace()%></td>
			            	<td align="center"><%=projeto.getStakeholders()%></td> 
			            	<td align="center"><%=projeto.getDataInicio()%></td>
			            	<td align="center"><%=projeto.getDataFim()%></td>
			            	<td align="center"><%=projeto.getDataFimPrevisto()%></td>
			            	<td align="center">
			            		<form action="" method="post">
			            			<a href="" title="Editar"> <i class="glyphicon glyphicon-pencil"></i></a>
			            		</form>
		            		</td>
		            		
		            		<td align="center">
	            				<a href="" data-toggle="modal" data-id="<%=projeto.getIdProjeto() %>" data-projeto="<%=projeto.getNomeProjeto()%>" data-target="#modalExcluir" title="Deletar"> <i class="glyphicon glyphicon-trash"></i></a>
		            		</td>
		            		
			            </tr>
							
						<% } %>
					</tbody>
		            
		        </table> 
  
   			 </div>
	         
         </div>

 </div>


<jsp:include page="../template/foot.jsp"></jsp:include>