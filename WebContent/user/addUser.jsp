<%@page import="java.util.Arrays"%>
<%@page import="java.util.List"%>
<%@page import="br.ages.crud.model.Usuario"%>
<%@page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>

<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<link rel="icon" href="img/favicon.ico">
	
  	<link rel="stylesheet" href="css/cadastro-usuario.css">
  	<link rel="stylesheet" href="css/navbar.css">
  	
	<title>AGES - Adicionar Usuário</title>
	
		<script type="text/javascript">
		
			function cadastrar() {
				var formCadastro =document.forms[0]; 
				formCadastro.action ="main?acao=addUser";
				formCadastro.submit();
			}
		
		</script>
</head>


<body>
	<jsp:include page="/template/head.jsp"></jsp:include>
	<br>
	<main>
            <section class="cadastro">
                <h1>Cadastro de Usuários</h1><!-- Título principal -->
                
                <jsp:include page="/template/msg.jsp"></jsp:include>
                
                <form method="post" action="#">
                
                    <label for="projeto" class="lb-text">
                        <span class="obg">*</span>Matricula:
                    </label>
                    
                    <input id="matricula" name="matricula" type="text" maxlength="9" value="${param.matricula}" required>
                    
                    <label for="projeto" class="lb-text">
                        <span class="obg">*</span>Nome:
                    </label>
                    
                    <input id="nome" name="nome" type="text" maxlength="120" value="${param.nome}" required>
                    
                    <label for="projeto" class="lb-text">
                        <span class="obg">*</span>Usuário:
                    </label>
                    
                    <input id="usuario" name="usuario" type="text" maxlength="120" value="${param.usuario}" required>
                    
                    <label for="projeto" class="lb-text">
                        <span class="obg">*</span>Senha:
                    </label>
                    
                    <input id="senha" name="senha" type="password" maxlength="120" value="${param.senha}" required>
                    
                    <label for="projeto" class="lb-text">
                        <span class="obg">*</span>E-Mail:
                    </label>
                    
                    <input id="email" name="email" type="text" maxlength="120" value="${param.email}" required>
                    
                    <div class="selects">
                        <label for="status">
                            <span class="obg">*</span>Administrador:<!-- O <span> foi utilizado apenas para estilar o "*" -->
                        </label>
                        
                        <select id="adm" name="adm" required>
                            <option value="N" <%= "N".equals(request.getParameter("adm")) ? "selected" : "" %> required>Não</option>
                            <option value="S" <%= "S".equals(request.getParameter("adm")) ? "selected" : "" %> required>Sim</option>
                        </select>
                    </div>
                    
                    <hr>
                    
                    <p>Campos que contém <span>*</span> são obrigatórios</p>
                    
                    
                   
                    <input class="reset" type="reset" value="Limpar">
                     <input class="submit" type="submit" value="Cadastrar" onclick="cadastrar()">
                </form>
            </section>
        </main>
	<jsp:include page="/template/foot.jsp"></jsp:include>
</body>
</html>