package br.ages.crud.command;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import br.ages.crud.bo.PontoBO;
import br.ages.crud.bo.UsuarioBO;
import br.ages.crud.exception.NegocioException;
import br.ages.crud.model.ResumoPonto;
import br.ages.crud.model.Usuario;

public class ListPontoTotalHorasCommand implements Command {

	private String proxima;
	private UsuarioBO usuarioBO;
	private List<Usuario> usuarios;
	private PontoBO pontoBO;
	private ArrayList<ResumoPonto> listaPontos;

	@Override
	public String execute(HttpServletRequest request) throws SQLException {
		pontoBO = new PontoBO();
		usuarioBO = new UsuarioBO();
		usuarios = new ArrayList<>();
		proxima = "aluno/listPontoHora.jsp";

		try {

			Integer idUsuario = Integer.valueOf(request.getParameter("id_usuario"));

			usuarios = usuarioBO.listarUsuarioAlunos();

			request.setAttribute("usuarios", usuarios);

			listaPontos = pontoBO.listaPontoAlunos(idUsuario);
			request.setAttribute("listaPontos", listaPontos);
			request.setAttribute("totalHorasAluno", pontoBO.calculatotalHorasAluno(listaPontos));

		} catch (NegocioException e) {
			e.printStackTrace();
			request.setAttribute("msgErro", e.getMessage());
		}

		return proxima;
	}
}
