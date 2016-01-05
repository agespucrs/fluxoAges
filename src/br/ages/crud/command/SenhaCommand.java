package br.ages.crud.command;

import javax.servlet.http.HttpServletRequest;

import br.ages.crud.bo.UsuarioBO;
import br.ages.crud.model.Usuario;
import br.ages.crud.util.SendMail;

public class SenhaCommand implements Command {

	private UsuarioBO usuarioBO;

	private String proxima;

	@Override
	public String execute(HttpServletRequest request) {
		// seta a mesma pagina, para o caso de erro/exceção
		proxima = "login.jsp";
		Usuario user = new Usuario();
		usuarioBO = new UsuarioBO();

		String login = request.getParameter("login");
		String email = request.getParameter("email");

		Usuario usuarioDTO = new Usuario();
		usuarioDTO.setUsuario(login);
		usuarioDTO.setEmail(email);

		try {
			user = usuarioBO.buscaSenha(usuarioDTO); 

			if (user.getEmail() != null || user.getUsuario() != null) {
			SendMail mail = new SendMail();
			
			String corpo = "Senha: " + user.getSenha() + "\n  Login: " + user.getUsuario();
			mail.envio(user.getEmail(), user.getNome(), "Recuperação de Senha Fluxo AGES", corpo);
		
			request.setAttribute("msgSucesso","Enviada a senha para o e-mail:" + user.getEmail() );
			} else {
				request.setAttribute("msgErro", "Login ou Usuario inexistente !!" );
			}
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("msgErro", e.getMessage());
		}
		
		return proxima;
	}

}
