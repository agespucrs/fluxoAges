package br.ages.crud.command;

import javax.servlet.http.HttpServletRequest;

import br.ages.crud.bo.UsuarioBO;
import br.ages.crud.model.Usuario;

public class LoginCommand implements Command {

	private UsuarioBO usuarioBO;

	private String proxima;

	@Override
	public String execute(HttpServletRequest request) {
		// seta a mesma pagina, para o caso de erro/exceção
		proxima = "login.jsp";
		usuarioBO = new UsuarioBO();

		String usuario = request.getParameter("login");
		String senha = request.getParameter("senha");

		Usuario usuarioDTO = new Usuario(usuario, senha);

		try {
			if (usuarioBO.validaUsuario(usuarioDTO)) {
				request.getSession().setAttribute("usuario", usuarioDTO);
				proxima = "index.jsp";
			}
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("msgErro", e.getMessage());
		}
		
		return proxima;
	}
}
