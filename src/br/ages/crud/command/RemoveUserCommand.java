package br.ages.crud.command;

import javax.servlet.http.HttpServletRequest;

import br.ages.crud.bo.UsuarioBO;
import br.ages.crud.exception.NegocioException;
import br.ages.crud.model.PerfilAcesso;
import br.ages.crud.model.Usuario;
import br.ages.crud.util.MensagemContantes;

public class RemoveUserCommand implements Command {

	private String proximo;

	private UsuarioBO usuarioBO;

	@Override
	public String execute(HttpServletRequest request) {
		proximo = "main?acao=listUser";
		this.usuarioBO = new UsuarioBO();
		
		Usuario usuario = (Usuario)request.getSession().getAttribute("usuarioSessao");		

		try {
			if( !usuario.getPerfilAcesso().equals(PerfilAcesso.ADMINISTRADOR) ) throw new NegocioException(MensagemContantes.MSG_INF_DENY);
			
			Integer idUsuario = Integer.parseInt(request.getParameter("id_usuario"));
			usuarioBO.removerUsuario(idUsuario);
			
			request.setAttribute("msgSucesso", MensagemContantes.MSG_SUC_REMOVE_USUARIO.replace("?", idUsuario.toString()).concat("<br/>"));
			
		} catch (Exception e) {
			request.setAttribute("msgErro", e.getMessage());
		}
		
		return proximo;
	}

}
