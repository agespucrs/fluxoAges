package br.ages.crud.command;

import java.sql.SQLException;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import br.ages.crud.bo.ProjetoBO;

import br.ages.crud.model.Projeto;

public class AdicionaProjetoCommand implements Command {
	
	private String proxima;
	
	private ProjetoBO projetoBO;

	@Override
	public String execute(HttpServletRequest request) throws SQLException {
		projetoBO =  new ProjetoBO();
		proxima = "project/addProject.jsp";
		
		String nome = request.getParameter("nome");
		String equipe = request.getParameter("equipe");
		String status = request.getParameter("status");
		String workspace = request.getParameter("workspace");
		
		try{
			Projeto projeto = new Projeto(nome, equipe, status, workspace);
		}
		
		
		
		return null;
	}

}
