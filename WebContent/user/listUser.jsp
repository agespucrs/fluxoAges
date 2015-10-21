<%@page import="br.ages.crud.model.Usuario"%>
<%@page import="java.util.List"%>

<script type="text/javascript">
	function remover(id) {
		var resposta = confirm("Deseja remover o usuario " + id);

		if (resposta == true) {
			var formCadastro = document.forms[0];
			formCadastro.action = "main?acao=removerUsuario&id_usuario=" + id;
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
		                    <th style="text-align: center;">Matricula</th>
							<th style="text-align: center;">Nome</th>
							<th style="text-align: center;">E-mail</th>
							<th style="text-align: center;">Usuário</th>
							<th style="text-align: center;">Administrador</th>
							<th colspan="2" style="text-align: center;">Ações</th>
		                </tr>
		            </thead>

		            <tbody> 
		            	<%
							List<Usuario> listaUsuarios = (List<Usuario>) request.getAttribute("listaUsuarios");
							for (Usuario usuario : listaUsuarios) {
						%>
						          
		            	<tr>
			            	<td align="center"><%=usuario.getIdUsuario()%></td>
			            	<td align="center"><%=usuario.getMatricula()%></td>
			            	<td align="center"><%=usuario.getNome()%></td>
			            	<td align="center"><%=usuario.getEmail()%></td>
			            	<td align="center"><%=usuario.getUsuario()%></td>
			            	<td align="center"><%=usuario.getAdministrador()%></td>
			            	<td align="center">
			            		<form action="" method="post">
			            			<a href="" title="Editar"> <i class="glyphicon glyphicon-pencil"></i></a>
			            		</form>
		            		</td>
		            		<td align="center">
		            			<form action="" method="post">
			            			<a href="" onclick="remover('<%=usuario.getIdUsuario() %>');" title="Deletar"> <i class="glyphicon glyphicon-trash"></i></a>
		            			</form>
		            		</td>
			            </tr>
							
						<% } %>
					</tbody>
		            
		        </table> 
  
   			 </div>
	         
         </div>
         
     </div>


<jsp:include page="../template/foot.jsp"></jsp:include>