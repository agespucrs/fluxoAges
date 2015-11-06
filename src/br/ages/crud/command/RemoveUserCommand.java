package br.ages.crud.command;

import javax.servlet.http.HttpServletRequest;

import br.ages.crud.bo.UsuarioBO;
import br.ages.crud.util.MensagemContantes;

public class RemoveUserCommand implements Command {

	private String proximo;

	private UsuarioBO usuarioBO;

	@Override
	public String execute(HttpServletRequest request) {
		proximo = "main?acao=listUser";
		this.usuarioBO = new UsuarioBO();

		try {
			Integer idUsuario = Integer.parseInt(request.getParameter("id_usuario"));
			usuarioBO.removerUsuario(idUsuario);
			request.setAttribute("msgSucesso", MensagemContantes.MSG_SUC_REMOVE_USUARIO.replace("?", idUsuario.toString()).concat("<br/>"));
			
		} catch (Exception e) {
			request.setAttribute("msgErro", ((String) MensagemContantes.MSG_REMOVER_USER_USUARIO_EM_PROJETO).replace("?", request.getParameter("id_usuario")).concat("<br/>"));
		}

		return proximo;
	}

}
