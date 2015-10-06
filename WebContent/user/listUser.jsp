<%@page import="br.ages.crud.model.Usuario"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" href="./css/comum.css" />
<link rel="stylesheet" href="//maxcdn.bootstrapcdn.com/font-awesome/4.3.0/css/font-awesome.min.css">
<link rel="icon" href="img/favicon.ico"><!-- Favicon é aquela imagem que vai na aba do navegador -->
        <!-- <link rel="stylesheet" href="css/reset.css">Esse arquivo css reseta todos os padrões de todas as tags -->
        <link rel="stylesheet" href="css/style.css"><!-- Estilo geral de todas as páginas -->
         <link rel="stylesheet" href="css/index.css">
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
<title>AgES - Pagina Inicial</title>
</head>
<body>
	<%@ include file="/template/head.jsp"%>
	<div class="main">
		<form action="" method="post">
			<jsp:include page="/template/msg.jsp"></jsp:include>
			<fieldset>

				<h1>List</h1>
				<table width="100%" border="1" cellspacing="0" cellpadding="4">
					<thead>
						<tr>
							<th>Id</th>
							<th>Matricula</th>
							<th>Nome</th>
							<th>E-Mail</th>
							<th>Usuário</th>
							<!-- <th>Senha</th> -->
							<th>ADM</th>
							<th colspan="2">Ações</th>
						</tr>
					</thead>
					<tbody>
						<%
							List<Usuario> listaUsuarios = (List<Usuario>) request.getAttribute("listaUsuarios");
							for (Usuario usuario : listaUsuarios) {
						%>
						<tr>
							<td class="alignCenter"><%=usuario.getIdUsuario()%></td>
							<td class="alignCenter"><%=usuario.getMatricula()%></td>
							<td class="alignLeft"><%=usuario.getNome()%></td>
							<td class="alignLeft"><%=usuario.getEmail()%></td>
							<td class="alignLeft"><%=usuario.getUsuario()%></td>
							<%-- 	<td class="alignLeft"> 	 <%=usuario.getSenha() %></td> --%>
							<td class="alignCenter"><%=usuario.getAdministrador()%></td>
							<td class="alignCenter"><a href="" title="Editar"> <i class="fa fa-pencil fa-2x"></i>
							</a></td>
							<td class="alignCenter"><a onclick="remover('<%=usuario.getIdUsuario()%>')" title="Deletear"> <i class="fa fa-trash-o fa-2x"></i>
							</a></td>
						</tr>
						<%
							}
						%>
					</tbody>
				</table>
			</fieldset>
		</form>
	</div>
	<jsp:include page="/template/foot.jsp"></jsp:include>
</body>
</html>