package br.ages.crud.command;

import java.sql.SQLException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import br.ages.crud.bo.UsuarioBO;
import br.ages.crud.model.Usuario;

public class RegistraPontoCommand implements Command {

	private String proxima;
	private UsuarioBO usuarioBO;

	@Override
	public String execute(HttpServletRequest request) throws SQLException {
		proxima = "aluno/ponto.jsp";
		usuarioBO = new UsuarioBO();
		try {
			Usuario usuario = (Usuario) request.getSession().getAttribute("usuarioSessao");
			List<Usuario> usuarios = usuarioBO.listarUsuario();
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("msgErro", e.getMessage());
		}

		return proxima;
	}
}
