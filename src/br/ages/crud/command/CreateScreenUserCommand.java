package br.ages.crud.command;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

import javax.servlet.http.HttpServletRequest;

import br.ages.crud.bo.UsuarioBO;
import br.ages.crud.dao.UsuarioDAO;
import br.ages.crud.exception.NegocioException;
import br.ages.crud.model.PerfilAcesso;
import br.ages.crud.model.Stakeholder;
import br.ages.crud.model.TipoUsuario;
import br.ages.crud.model.Usuario;
import br.ages.crud.util.MensagemContantes;

public class CreateScreenUserCommand implements Command {

	private String proxima;

	private UsuarioBO usuarioBO;
	
	//private StakeholderBO stakeholderBO;

	public String execute(HttpServletRequest request) throws SQLException {
		proxima = "main?acao=listUser";
		Usuario currentUser = (Usuario)request.getSession().getAttribute("usuarioSessao");

		try {			
			if( !currentUser.getPerfilAcesso().equals(PerfilAcesso.ADMINISTRADOR) ) throw new NegocioException(MensagemContantes.MSG_INF_DENY);
			// Verifica se abre tela edição de pessoa ou de adição de pessoa.
			
			String isEdit = request.getParameter("isEdit");
			
			
			if (isEdit != null && !"".equals(isEdit)) {
				
				usuarioBO = new UsuarioBO();
				
				int id = Integer.parseInt(request.getParameter("id_usuario"));
				Usuario usuario = usuarioBO.buscaUsuarioId(id);
				
				
				request.setAttribute("usuario", usuario);
				proxima = "user/editUser.jsp";
				
			} else { // Adiciona um novo usuário
				usuarioBO = new UsuarioBO();
				List<TipoUsuario> tipoUsuarios = new ArrayList<TipoUsuario>();

				tipoUsuarios = usuarioBO.listaTipoUsuarios();
				request.setAttribute("tipoUsuarios", tipoUsuarios);
				
				proxima = "user/addUser.jsp";		
			}

		} catch (Exception e) {
			request.setAttribute("msgErro", e.getMessage());
		}

		return proxima;
	}
}
