package br.ages.crud.command;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import br.ages.crud.bo.UsuarioBO;
import br.ages.crud.model.PerfilAcesso;
import br.ages.crud.model.StatusUsuario;
import br.ages.crud.model.TipoUsuario;
import br.ages.crud.model.Usuario;
import br.ages.crud.util.MensagemContantes;

public class AddUserCommand implements Command {


	private String proxima;

	private UsuarioBO usuarioBO;

	@Override
	public String execute(HttpServletRequest request) {
		usuarioBO = new UsuarioBO();
		proxima = "user/addUser.jsp";

		String nome = request.getParameter("nome");
		String email = request.getParameter("email");
		String matricula = request.getParameter("matricula");
		String usuario = request.getParameter("usuario");
		String senha = request.getParameter("senha");
		String statusUsuario = request.getParameter("statusUsuario"); // XXX Acrescentado  StatusUsuario
		String perfilAcesso = request.getParameter("perfilAcesso");
		String tipoUsuario = request.getParameter("tipoUsuario");
		try {
			Usuario user = new Usuario();
			user.setNome(nome);
			user.setEmail(email);
			user.setMatricula(matricula);
			user.setUsuario(usuario);
			user.setSenha(senha);
			user.setStatusUsuario(StatusUsuario.valueOf(statusUsuario));
			user.setPerfilAcesso(PerfilAcesso.valueOf(perfilAcesso));
			TipoUsuario tUser = new TipoUsuario();
			
			tUser = usuarioBO.consultaTipoUsuario(tipoUsuario);
			user.setTipoUsuario(tUser);
			
			boolean isValido = usuarioBO.validaUsuarioA(user);
			if (isValido == false) {
				request.setAttribute("msgErro", MensagemContantes.MSG_ERR_USUARIO_DADOS_INVALIDOS);
			} else { // cadastro de pessoa com sucesso
				usuarioBO.cadastraUsuario(user);
				proxima = "main?acao=listUser";
				request.setAttribute("msgSucesso", MensagemContantes.MSG_SUC_CADASTRO_USUARIO.replace("?", user.getNome()));

			}
		} catch (Exception e) {
			request.setAttribute("msgErro", e.getMessage());
			//proxima = "main?acao=addUser";
		}

		return proxima;
	}
}
