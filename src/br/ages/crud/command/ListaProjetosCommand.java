package br.ages.crud.command;

import java.sql.SQLException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import br.ages.crud.bo.ProjetoBO;
import br.ages.crud.exception.NegocioException;
import br.ages.crud.model.Projeto;

public class ListaProjetosCommand implements Command{
	private String proxima;
	private ProjetoBO projetoBO;
	
	@Override
	public String execute(HttpServletRequest request) throws SQLException {
		this.projetoBO = new ProjetoBO();
		proxima = "project/listProject.jsp";

		try {
			List<Projeto> listaProjetos = projetoBO.listarProjeto();
			request.setAttribute("listaProjetos", listaProjetos);
		} catch (NegocioException e) {
			e.printStackTrace();
			request.setAttribute("msgErro", e.getMessage());
		}
		return proxima;
	}
}

