<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html><!-- Informa ao browser a versão do HTML. Nesse caso HTML5. -->
<html lang="pt-br"><!-- lang="pt-br" informa que a página está na linguagem português(Brasil) -->
    <head>
        <meta charset="utf-8"><!-- Codificação de caracteres. A UTF-8 permite utilizar caaracteres especiais e acentos -->
        
        <title>AGES - Agência Experimental de Engenharia de Software</title><!-- Título da página -->
        
        <link rel="icon" href="img/favicon.ico"><!-- Favicon é aquela imagem que vai na aba do navegador -->
        <link rel="stylesheet" href="css/reset.css"><!-- Esse arquivo css reseta todos os padrões de todas as tags -->
        <link rel="stylesheet" href="css/style.css"><!-- Estilo geral de todas as páginas -->
        <link rel="stylesheet" href="css/index.css"><!-- Estilo dessa página -->
        
        <script src="js/index.js"></script>
    </head>
    <body>
    	<jsp:include page="./template/head.jsp"></jsp:include>
  
        <!-- Conteúdo principal -->
        <main>
            <img class="logo" src="img/logo-ages.png" alt="AGES">
           
            <section class="login">
                <h1>Fluxo <span>A</span>GES</h1><!-- Título principal -->
                
                <label id="msg" name="msg"></label>
        
        <!-- Rodapé -->
        <footer>
            <!-- Pseudo rodapé -->
        </footer>
    </body>
</html>