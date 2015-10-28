<%@page import="java.util.Arrays"%>
<%@page import="java.util.List"%>
<%@page import="br.ages.crud.model.Usuario"%>
<%@page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>

	
<script type="text/javascript">

	function cadastrar() {
		var formCadastro =document.forms[0]; 
		formCadastro.action ="main?acao=adicionaProjeto";
		formCadastro.submit();
	}
	
	

</script>
<script src="../js/cadastro-projeto.js"></script>

<jsp:include page="../template/head.jsp"></jsp:include>
	
	<div class="panel panel-primary panel-addUser">
    		
		<div class="panel-heading text-center">
			Cadastro de Projeto
		</div>
		
		
		<div class="panel-body">
		
        	<jsp:include page="/template/msg.jsp"></jsp:include>
        	
        	<div class="table-responsive">
                
                <form method="post" action="#">
                
               		<%-- <div class="form-group">
			           	<label class="form-label ages">Código do Projeto:</label>
			           	<input class="form-control" id="codigo" name="codigo" value="${param.codigo}" type="text" maxlength="120" disabled>
		            </div> --%>
		            
		            <div class="form-group">
			           	<label class="form-label ages">Nome: <span class="red">*</span></label>
			           	<input class="form-control" id="nome" name="nome" value="${param.nome}" type="text" maxlength="120" required>
		            </div>

					<%-- <%--<div class="form-group integrante">
			           	<label class="form-label ages">Integrantes:</label>
			           	<input class="form-control" id="usuarios" name="usuarios" value="${param.usuarios}" type="text" maxlength="120" required>
		            </div>
		            
		            <div id="addUserSection">
		            	<div class="addUser">
		            		<label>Integrantes:</label>
			            	<select id="usuarios" name="usuarios">
			            		<option>asd</option>	            
			            	</select>
		            	</div>
		            </div>
		            
                    <div id="addUserBtn">teste</div> --%>
                    
                    <!-- STAKEHOLDER -->
                    
                    <%-- <div class="form-group">
			           	<label class="form-label ages">Status: <span class="red">*</span></label>
			           	<select class="form-control" id="statusUsuario" name="statusUsuario" required>
			           		<option value="ATIVO" <%= "ATIVO".equals(request.getParameter("statusUsuario")) ? "selected" : "" %>>Ativo</option>
                            <option value="INATIVO" <%= "INATIVO".equals(request.getParameter("statusUsuario")) ? "selected" : "" %>>Inativo</option>
		           		</select>
		            </div> --%>
                    
					<div class="form-group">
			           	<label class="form-label ages">Workspace:</label>
			           	<input class="form-control" id="workspace" name="workspace" value="${param.workspace}" type="text" maxlength="120" required>
		            </div>
				
					<div class="form-group">
			           	<label class="form-label ages">Data de Início:</label>
			           	<input class="form-control" id="dataInicio" name="dataInicio" value="${param.dataInicio}" type="text" maxlength="10" required>
		            </div>
                    
                     <div class="form-group">
			           	<label class="form-label ages">Data de Fim Prevista:</label>
			           	<input class="form-control" id="dataFimPrevista" name="dataFimPrevista" value="${param.dataFimPrevista}" type="text" maxlength="10" required>
		            </div>
                    
                    <div class="form-group">
			           	<label class="form-label ages">Data de Fim:</label>
			           	<input class="form-control" id="dataFim" name="dataFim" value="${param.dataFim}" type="text" maxlength="10" required>
		            </div>
		            
                    <hr>
                    
                    <p>Campos que contém <span>*</span> são obrigatórios</p>
                    
                    
                    <div class="text-center">
			           	<input class="btn btn-warning limparUser pull-left" type="reset" value="Limpar">
			           	<input class="btn btn-primary addUser pull-right" type="submit" value="Cadastrar" onclick="cadastrar()">
			        </div>
                </form>
            </div>
		</div>
	</div>

<jsp:include page="/template/foot.jsp"></jsp:include>