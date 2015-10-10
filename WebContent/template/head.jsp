<div class="head">
	
	 <ul id="nav">
	 
        <li><a href="#">Usuários</a>
             <ul>
                 <li><a href="main?acao=telaUser"    class="${param.acao eq 'telaUser' ? 'selected' : ''}">Cadastrar</a></li>
                 <li><a href="main?acao=listUser"   class="${param.acao eq 'listUser' ? 'selected' : ''}">Listar</a></li>
             </ul>
        </li>
        
        <li style="float: right" ><a href="#">Olá, ${sessionScope.usuario.usuario}</a>
        	<ul>
        	<li><a href="main?acao=logout" class="${param.acao eq 'logout' ? 'selected' : ''}">Sair</a></li>
        	</ul>
        </li>   
        
     </ul>
	
	<img class="logo" src="img/logo-ages.png" alt="AGES">
	
</div>
	