<%@page import="java.util.Arrays"%>
<%@page import="java.util.List"%>
<%@page import="br.ages.crud.model.Usuario"%>
<jsp:include page="/template/head.jsp"></jsp:include>

<script type="text/javascript">
		
			function cadastrar() {
				var formCadastro =document.forms[0]; 
				formCadastro.action ="main?acao=adicionarProjeto";
				formCadastro.submit();
			}
		
</script>


	<div class="panel panel-primary">
    		
		<div class="panel-heading text-center">
			Cadastro de Projetos
		</div>
         
                
        <div class="panel-body">
            
	         <form method="post" action="main?acao=login">
	         	
	         	
	         	<div class="form-group">
	            	<label class="form-label">Código:</label>
	            	<input class="form-control" id="codigo" name="codigo" value="" type="text" maxlength="120" disabled required>
             	</div>
             	
             	<div class="form-group">
	            	<label class="form-label">Nome do Projeto:</label>
	            	<input class="form-control" id="nome" name="nome" value="" type="text" maxlength="15" required>
            	</div>
            	
            	<div class="form-group">
	            	<label class="form-label">Integrantes:</label>
	            	<input class="form-control" id="integrantes" name="integrantes" value="" type="select">
            	</div>
            	
            	<div class="form-group">
	            	<label class="form-label">Status:</label>
	            	<input class="form-control" id="status" name="status" value="" type="select" required>
            	</div>
            	
            	<div class="form-group">
	            	<label class="form-label">Workspace:</label>
	            	<input class="form-control" id="workspace" name="workspace" value="" type="text" maxlength="200" >
            	</div>
             	
             	<hr>
             	
	            <div class="text-center">
	             	<input class="btn btn-primary pull-right" type="submit" value="Cadastrar">
	             	<input class="btn btn-default pull-left" type="submit" value="Voltar">
	         	</div>
	         	
	         	<input class="reset" type="reset" value="Limpar">
                <input class="submit" type="submit" value="Cadastrar" onclick="cadastrar()">
	         	
	         </form>
	         
         </div>
         
     </div>

<jsp:include page="/template/foot.jsp"></jsp:include>