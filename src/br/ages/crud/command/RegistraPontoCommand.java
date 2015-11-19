package br.ages.crud.command;

import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;

import br.ages.crud.model.Usuario;
import br.ages.crud.util.MensagemContantes;

public class RegistraPontoCommand implements Command {

	private String proxima;

	@Override
	public String execute(HttpServletRequest request) throws SQLException {
		proxima = "aluno/ponto.jsp";

		try {
			Usuario usuario = (Usuario) request.getSession().getAttribute("usuarioSessao");
			System.out.println(usuario.getNome());;
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("msgErro", e.getMessage());
		}

		return proxima;
	}
}
