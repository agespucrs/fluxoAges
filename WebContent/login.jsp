<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html lang="pt-br">

    <head>
        <meta charset="utf-8">
        <title>AGES - Agência Experimental de Engenharia de Software</title><!-- Título da página -->
        <link rel="icon" href="img/favicon.ico">

        <!-- JQUERY -->
		<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
		
		<!-- BOOTSTRAP -->
		<link rel="stylesheet" href="./css/bootstrap.min.css">
		<link rel="stylesheet" href="./css/bootstrap-theme.min.css">
		<script src="./js/bootstrap.min.js"></script>
		
		<!-- STYLE -->
		<link rel="stylesheet" href="./css/style.css">
		

    </head>
    
    
    <body>
		<div class="container">
    		
    		<br>
    		<div class="text-center">
    			<img class="logo" src="img/logo-ages.png" alt="AGES">
    		</div>
    		<br>
   		
   		<!-- MODAL / POPUP -->
   		<jsp:include page="./template/modalSenha.jsp"></jsp:include>
    	
    		<div class="panel panel-primary panel-login">
    		
    			<div class="panel-heading text-center">
    				Sistema de Controle de Fluxo de Projetos
    			</div>
                <div class="panel-body">
    
    				<h1 class="welcome">Bem <span class="ages">v</span>indo!</h1>
    				<jsp:include page="/template/msg.jsp"></jsp:include>
 			         <form method="post" action="main?acao=login">
			         	<div class="form-group">
			            	<label class="form-label ages">Usuário:</label>
			            	<input class="form-control" id="login" name="login" value="${param.login}" type="text" maxlength="120" required>
		             	</div>
		             	<div class="form-group">
			            	<label class="form-label ages">Senha:</label>
			            	<input class="form-control" id="senha" name="senha" value="${param.senha}" type="password" maxlength="15" required>
		            	</div>
		            	<i class="glyphicon glyphicon-lock"></i>
		            	<a href="" data-toggle="modal" data-id="login" data-usuario="#login" data-target="#modalSenha" title="Click para recuperar a senha senha"> Recuperar Senha</a>
		             	<hr>
			            <div class="text-center">
			             	<input class="btn btn-primary login pull-center" type="submit" value="Entrar">
			         	</div>
			         </form>
			         
		         </div>
		         
	         </div>
        </div>
    </body>
</html>