<div class="head">

	<a href="index.jsp">
		<img style="width: 12%;" alt="Logo AgES" src="img/logo-ages.png">
	</a>
	
	<ul>
		<%-- <li><a href="index.jsp"  class="${param.acao eq 'index' ? 'selected' : ''}">Home</a></li> --%>
		<li><a href="main?acao=telaUser"    class="${param.acao eq 'telaUser' ? 'selected' : ''}">Cadastar Usuario</a></li>
		<li><a href="main?acao=listUser"   class="${param.acao eq 'listUser' ? 'selected' : ''}">Listar Usuario</a></li>
		<li><a href="main?acao=logout" class="${param.acao eq 'logout' ? 'selected' : ''}">Sair</a></li>
	</ul>
	
	<div class="welcome">
		Usuário: <b>${sessionScope.usuario.usuario}</b>!
	</div>
	
</div>