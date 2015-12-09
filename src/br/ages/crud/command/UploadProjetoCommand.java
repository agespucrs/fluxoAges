package br.ages.crud.command;

import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;

import br.ages.crud.bo.ProjetoBO;
import br.ages.crud.exception.NegocioException;
import br.ages.crud.model.Projeto;

public class UploadProjetoCommand implements Command {

	private String proxima;

	private Projeto projeto;

	private ProjetoBO projetoBO;

	@Override
	public String execute(HttpServletRequest request) throws SQLException, NegocioException {
		
		projetoBO = new ProjetoBO();
		
		proxima = "project/uploadProject.jsp";
		int idProjeto = Integer.valueOf(request.getParameter("id_projeto"));
		projeto = projetoBO.buscarProjeto(idProjeto);
		request.getSession().setAttribute("projeto", projeto);

		return proxima;
	}

}
