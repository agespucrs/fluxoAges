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
        <!-- Conteúdo principal -->
        <main>
            <img href="index.jsp" class="logo" src="img/logo-ages.png" alt="AGES">
           
            <section class="login">
                <h1>Bem <span>v</span>indo!</h1><!-- Título principal -->
                
                <label id="msg" name="msg"><jsp:include page="/template/msg.jsp"></jsp:include></label>
                
                <form method="post" action="main?acao=login">
                    <label for="login">Usuário:</label>
                    <input id="login" name="login" value="${param.login}" type="text" maxlength="120" required>
                    
                    <label for="senha">Senha:</label>
                    <input id="senha" name="senha" value="${param.senha}" type="password" maxlength="15" required>
                    <a class="forgot-password" href="#">Esqueci minha senha</a>
                    
                    <hr>
                    
                    <input class="submit" type="submit" value="Entrar">
                </form>
            </section>
        </main>
        
        <!-- Rodapé -->
        <footer>
            <!-- Pseudo rodapé -->
        </footer>
    </body>
</html>