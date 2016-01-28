<%@page import="br.ages.crud.model.Usuario"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html lang="pt-br">

    <head>
        <meta charset="utf-8">
        <title>AGES - Agência Experimental de Engenharia de Software</title>
        <link rel="icon" href="img/favicon.ico">
        
		<!-- BOOTSTRAP -->
		<link rel="stylesheet" href="./css/bootstrap.min.css">
	 	<link rel="stylesheet" href="./css/bootstrap-theme.min.css"> 

		<!-- Include the plugin's CSS and JS: Cassio DataTable -->
	<!-- 	<link rel="stylesheet" href="https://cdn.datatables.net/1.10.10/css/jquery.dataTables.min.css"> -->
		<link rel="stylesheet" href="https://cdn.datatables.net/1.10.10/css/dataTables.bootstrap.min.css">

		<!-- STYLE -->
		<link rel="stylesheet" href="./css/style.css">

        <!-- JQUERY -->
        <script src="http://code.jquery.com/jquery-1.12.0.min.js"></script>

		<!-- BOOTSTRAP -->
		<script src="./js/bootstrap.min.js"></script> 


		<!-- Include the plugin's CSS and JS: Cassio DataTable -->
		<script type="text/javascript" src="https://cdn.datatables.net/1.10.10/js/jquery.dataTables.min.js"></script>
 		<script type="text/javascript" src="https://cdn.datatables.net/1.10.10/js/dataTables.bootstrap.min.js"></script>

		
		<!-- Include the plugin's CSS and JS: Cassio Dual ListBox -->
		<script src="./js/jquery.bootstrap-duallistbox.min.js"></script>
		<link rel="stylesheet" type="text/css" href="./css/bootstrap-duallistbox.min.css">
		
		<!-- Include the plugin's CSS and JS: Cassio DateTime Picker -->
		<script type="text/javascript" src="./js/moment.js"></script>
		<script type="text/javascript" src="./js/pt-br.js"></script>
		<script type="text/javascript" src="./js/transition.js"></script>
		<script type="text/javascript" src="./js/collapse.js"></script>
		<script type="text/javascript" src="./js/bootstrap-datetimepicker.min.js"></script>
		<link rel="stylesheet" type="text/css" href="./css/bootstrap-datetimepicker.min.css">
		
	
	</head>
    
    <body>
     <% Usuario usuarioSessao = (Usuario) session.getAttribute("usuarioSessao"); %>
    	<div class="container">
    	
    			<nav class="navbar navbar-default">
				<div class="container-fluid">
				
		    		<div class="navbar-header">
			      		<a class="navbar-brand" href="main?acao=listaProjetos">
			        		<img class="logoNavBar" src="./img/logo-ages.png" alt="AGES">
			      		</a>
			    	</div>

					<ul class="nav navbar-nav">
						
						<li class="dropdown">
			          		<a class="dropdown-toggle" data-toggle="dropdown" href="#">Usuários
				          		<span class="caret"></span>
				          	</a>
				          	<ul class="dropdown-menu">
				            	<li><a href="main?acao=listUser">Listar</a></li>
				            	<li><a href="main?acao=telaUser">Cadastrar</a></li> 
				          	</ul>
        				</li>

			        	<li class="dropdown">
			          		<a class="dropdown-toggle" data-toggle="dropdown" href="#">Projetos
				          		<span class="caret"></span>
			          		</a>
				          	<ul class="dropdown-menu">
				            	<li><a href="main?acao=listaProjetos">Listar</a></li>
				            	<li><a href="main?acao=telaProjeto">Cadastrar</a></li> 
				          	</ul>
        				</li>
        				
        				<li class="dropdown">
			          		<a class="dropdown-toggle" data-toggle="dropdown" href="#">Stakeholders
				          		<span class="caret"></span>
			          		</a>
				          	<ul class="dropdown-menu">
				            	<li><a href="main?acao=listaStakeholders">Listar</a></li>
				            	<li><a href="main?acao=telaStakeholder">Cadastrar</a></li> 
				          	</ul>
        				</li>

        				<li class="dropdown">
			          		<a class="dropdown-toggle" data-toggle="dropdown" href="#">Alunos
				          		<span class="caret"></span>
			          		</a>
				          	<ul class="dropdown-menu">
				            	<li><a href="main?acao=registrarPonto">Registrar Ponto</a></li>
				            	<li><a href="main?acao=listaPontoHora&id_usuario=0">Lista Horas Ponto</a></li>
				            	
				          	</ul>
        				</li>

					</ul>

					<ul class="nav navbar-nav navbar-right">
        				<li class="dropdown">
        					<a class="dropdown-toggle" data-toggle="dropdown" href="#">
        						<span class="glyphicon glyphicon-user"></span>
        						Olá, <%=usuarioSessao.getNome()%>!
        						<span class="caret"></span>
        					</a>
                           
        					<ul class="dropdown-menu dropdown-menu-right">
        						<li><a href="main?acao=logout">Logout</a></li>
        					</ul>
        				</li>
			      	</ul>
			      	
		    	</div>
			</nav>

    		
