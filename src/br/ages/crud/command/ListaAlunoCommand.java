package br.ages.crud.command;

import java.sql.SQLException;
import java.text.ParseException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import br.ages.crud.bo.PontoBO;
import br.ages.crud.exception.NegocioException;
import br.ages.crud.exception.PersistenciaException;
import br.ages.crud.model.Ponto;

public class ListaAlunoCommand implements Command {
private String proxima;
private PontoBO pontoBO;
	@Override
	public String execute(HttpServletRequest request) throws SQLException, NegocioException, ParseException, PersistenciaException {
		this.pontoBO= new PontoBO();
		proxima = "aluno/listAluno.jsp";

		try {
			
			List<Ponto> listaAlunos = pontoBO.listarAlunos();
			request.setAttribute("listaAlunos", listaAlunos);
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("msgErro", e.getMessage());
		}
		return proxima;
	}

}
