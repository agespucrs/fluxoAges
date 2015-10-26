<%@page import="java.util.Arrays"%>
<%@page import="java.util.List"%>
<%@page import="br.ages.crud.model.Usuario"%>
<%@page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>

	
<script type="text/javascript">

	function cadastrar() {
		var formCadastro =document.forms[0]; 
		formCadastro.action ="main?acao=addUser";
		formCadastro.submit();
	}

</script>

<jsp:include page="../template/head.jsp"></jsp:include>
	
	<div class="panel panel-primary panel-addUser">
    		
		<div class="panel-heading text-center">
			Cadastro de Usuário
		</div>
		
		
		<div class="panel-body">
		
        	<jsp:include page="/template/msg.jsp"></jsp:include>
        	
        	<div class="table-responsive">
                
                <form method="post" action="#">
                
               		<div class="form-group">
			           	<label class="form-label ages">Matrícula: <span class="red">*</span></label>
			           	<input class="form-control" id="matricula" name="matricula" value="${param.matricula}" type="text" maxlength="9" required>
		            </div>
		            
		            <div class="form-group">
			           	<label class="form-label ages">Nome: <span class="red">*</span></label>
			           	<input class="form-control" id="nome" name="nome" value="${param.nome}" type="text" maxlength="120" required>
		            </div>

					<div class="form-group">
			           	<label class="form-label ages">Usuário: <span class="red">*</span></label>
			           	<input class="form-control" id="usuario" name="usuario" value="${param.usuario}" type="text" maxlength="120" required>
		            </div>
                    
					<div class="form-group">
			           	<label class="form-label ages">Senha: <span class="red">*</span></label>
			           	<input class="form-control" id="senha" name="senha" value="${param.senha}" type="password" maxlength="120" required>
		            </div>
				
					<div class="form-group">
			           	<label class="form-label ages">E-Mail: <span class="red">*</span></label>
			           	<input class="form-control" id="email" name="email" value="${param.email}" type="text" maxlength="120" required>
		            </div>
                    
                    <div class="form-group">
			           	<label class="form-label ages">Perfil de Acesso: <span class="red">*</span></label>
			           	<select class="form-control" id="perfilAcesso" name="perfilAcesso" required>
			           		<option value="ADMINISTRADOR" <%= "ADMINISTRADOR".equals(request.getParameter("perfilAcesso")) ? "selected" : "" %>>Administrador</option>
                            <option value="NAVEGADOR" <%= "NAVEGADOR".equals(request.getParameter("perfilAcesso")) ? "selected" : "" %>>Navegador</option>
		           		</select>
		            </div>
		            
		            <div class="form-group">
			           	<label class="form-label ages">Status: <span class="red">*</span></label>
			           	<select class="form-control" id="statusUsuario" name="statusUsuario" required>
			           		<option value="ATIVO" <%= "ATIVO".equals(request.getParameter("statusUsuario")) ? "selected" : "" %>>Ativo</option>
                            <option value="INATIVO" <%= "INATIVO".equals(request.getParameter("statusUsuario")) ? "selected" : "" %>>Inativo</option>
		           		</select>
		            </div>
		            
		            <div class="form-group">
			           	<label class="form-label ages">Tipo de Usuário: <span class="red">*</span></label>
			           	<select class="form-control" id="tipoUsuario" name="tipoUsuario" required>
                            <option value="ALUNO" <%= "ALUNO".equals(request.getParameter("tipoUsuario")) ? "selected" : "" %>>Aluno</option>
			           		<option value="ARQUITETO" <%= "ARQUITETO".equals(request.getParameter("tipoUsuario")) ? "selected" : "" %>>Arquiteto</option>
		           		</select>
		            </div>
                    
                    <hr>
                    
                    <p>Campos que contém <span class="red">*</span> são obrigatórios</p>
                    
                    
                    <div class="text-center">
			           	<input class="btn btn-warning limparUser pull-left" type="reset" value="Limpar">
			           	<input class="btn btn-primary addUser pull-right" type="submit" value="Cadastrar" onclick="cadastrar()">
			        </div>
                </form>
            </div>
		</div>
	</div>

<jsp:include page="/template/foot.jsp"></jsp:include>