<%@page import="java.util.Arrays"%>
<%@page import="java.util.List"%>
<%@page import="br.ages.crud.model.Usuario"%>
<%@page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<link rel="stylesheet" href="./css/comum.css" />
		<title>AgES - Adds</title>
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
	<h1>Cadastro Usuario</h1>
	<div class="main">
		<form action="" method="post">
			<jsp:include page="/template/msg.jsp"></jsp:include>
			<fieldset>
				<table cellpadding="5">
					<tr>
						<td>Matricula <sup class="red">*</sup></td>
						<td><input type="text" id="matricula" name="matricula" maxlength="11" value="${param.matricula}" /></td> 
					</tr>
					<tr>
						<td>Name <sup class="red">*</sup></td>
						<td><input type="text" id="nome" name="nome" maxlength="45" value="${param.nome}" /></td>
					</tr>
					<tr>
						<td>Usuario <sup class="red">*</sup></td>
						<td><input type="text" id="usuario" name="usuario" maxlength="11" value="${param.usuario}" /></td> 
					</tr>
					<tr>
						<td>Senha <sup class="red">*</sup></td>
						<td><input type="text" id="senha" name="senha" maxlength="45" value="${param.senha}" /></td>
					</tr><tr>
						<td>e-Mail Address</td>
						<td><input type="text" id="email" name="email" maxlength="45" value="${param.email}" /></td>
					</tr>
					<tr>
						<td>Administrador <sup class="red">*</sup></td>
						<td><input type="radio" id="adm" name="adm" value="S" <%= "S".equals(request.getParameter("adm")) ? "checked" : "" %>/>SIM
						    <input type="radio" id="adm" name="adm" value="N" <%= "N".equals(request.getParameter("adm")) ? "checked" : "" %>/>NÃO</td>
					</tr>
				</table>
			</fieldset>
			<span><sup class="red">*</sup> campos obrigatórios</span>
			<input type="reset"  value="Limpar"  id="limpar" name="limpar" />
			<input type="button" value=Cadastrar onclick="cadastrar()"/>
		</form>
	</div>
	<jsp:include page="/template/foot.jsp"></jsp:include>
</body>
</html>