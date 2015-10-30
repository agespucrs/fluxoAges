package br.ages.crud.servlet;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.ages.crud.command.*;
import br.ages.crud.util.LogParametrosSession;

@WebServlet("/main")
public class MainServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	private Map<String, Command> comandos = new HashMap<String, Command>();

	@Override
	public void init() throws ServletException {
		
		comandos.put("login", new LoginCommand());
		comandos.put("logout", new LogoutCommand());
		
		//COMANDOS DE USUARIO
		
		comandos.put("telaUser", new CreateScreenUserCommand());
		comandos.put("listUser", new ListUserCommand());
		comandos.put("addUser", new AddUserCommand());
		comandos.put("editUser", new EditUserCommand());
		comandos.put("removerUsuario", new RemoveUserCommand());
		
		//COMANDOS DE PROJETO
		
		comandos.put("telaProjeto", new CreateScreenProjectCommand());
		comandos.put("listaProjetos", new ListaProjetosCommand());
		comandos.put("adicionaProjeto", new AdicionaProjetoCommand());
		comandos.put("editaProjeto", new EditaProjetoCommand());
		comandos.put("removeProjeto", new RemoveProjetoCommand());
		
	}

	@Override
	protected void service(HttpServletRequest request, HttpServletResponse reponse) throws ServletException, IOException {

		String acao = request.getParameter("acao");
		String proxima = null;

		try {
			Command comando = verificarComando(acao);
			proxima = comando.execute(request);
		} catch (Exception e) {
			request.setAttribute("msgErro", e.getMessage());
		}
	
		LogParametrosSession.logParametros(request);
		
		request.getRequestDispatcher(proxima).forward(request, reponse);
		
	}

	private Command verificarComando(String acao) {
		Command comando = null;
		for (String key : comandos.keySet()) {
			if (key.equalsIgnoreCase(acao)) {
				comando = comandos.get(key);
			}
		}
		return comando;
	}
}

