<%@page import="br.ages.crud.model.Usuario"%>
<%@page import="java.util.List"%>
<jsp:include page="../template/head.jsp"></jsp:include>

<!-- MODAL / POPUP -->
<jsp:include page="../template/modal.jsp"></jsp:include>
 		
<div class="panel panel-primary">
   		
	<div class="panel-heading text-center">Lista de Usuários</div>
               
       <div class="panel-body">
       
		<jsp:include page="/template/msg.jsp"></jsp:include>
        <div class="table-responsive">
        
        <table id="listaAlunos" class="table table-responsive table-striped table-hover table-condensed table-bordered">

            <thead>
                <tr>
                    <th style="text-align: center;">ID</th>
                    <th style="text-align: center;">Matricula</th>
					<th style="text-align: center;">Nome</th>
					<th style="text-align: center;">E-mail</th>
					<th style="text-align: center;">Usuário</th>
					<th style="text-align: center;">Perfil</th>
					<th style="text-align: center;">Status</th>
					<th style="text-align: center;">Tipo</th>
					<th style="text-align: center;"></th>
					<th style="text-align: center;"></th>
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
	            	<td align="center"><%=usuario.getPerfilAcesso()%></td> 
	            	<td align="center"><%=usuario.getStatusUsuario()%></td>
	            	<td align="center"><%=usuario.getTipoUsuario().getNome()%></td>
	            	<td align="center">
						<form action="" method="post">
            				<a href="" data-toggle="modal" data-id="<%=usuario.getIdUsuario() %>" data-usuario="<%=usuario.getNome()%>" 
            				data-target="#modalEditar" title="Editar"> <i class="glyphicon glyphicon-pencil"></i></a>
            			</form>
            		</td>
            		
            		<td align="center">
            			<form action="" method="post">
            				<a href="" data-toggle="modal" data-id="<%=usuario.getIdUsuario() %>" data-usuario="<%=usuario.getNome()%>" 
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
<script>

$(document).ready(function(){
	$('#listaAlunos').dataTable({
	    "language": {
            "lengthMenu": "Mostrando _MENU_ registros por página",
            "zeroRecords": "Sem registros - sorry",
            "info": "Mostrando _PAGE_ de _PAGES_ páginas",
            "infoEmpty": "Nenhum registros encontrados!",
            "infoFiltered": "(Filtrado _MAX_ do total deregistros)",
            "search":"Busca",
           	"paginate": {
                "first":      "Primeiro",
                "last":       "Último",
                "next":       "Próximo",
                "previous":   "Anterior"
	        },
        }
	});
});;
</script>