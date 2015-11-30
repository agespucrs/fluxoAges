package br.ages.crud.command;

import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

import br.ages.crud.bo.PontoBO;
import br.ages.crud.exception.NegocioException;
import br.ages.crud.model.ResumoPonto;

public class ListPontoTotalHorasCommand implements Command {

	private String proxima;
	private PontoBO pontoBO;
	
	@Override
	public String execute(HttpServletRequest request) throws SQLException {
		pontoBO = new PontoBO();
		proxima = "aluno/listPontoHora.jsp";

		try {
			ArrayList<ResumoPonto>  listaPontos = pontoBO.listaPontoAlunos();
			request.setAttribute("listaPontos", listaPontos );
		} catch (NegocioException e) {
			e.printStackTrace();
			request.setAttribute("msgErro", e.getMessage());
		}

		return proxima;
	}
}
