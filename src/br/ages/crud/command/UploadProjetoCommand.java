package br.ages.crud.command;

import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;

import br.ages.crud.model.Projeto;

public class UploadProjetoCommand implements Command {
	
	private String proxima;
	
	private Projeto projeto;

	@Override
	public String execute(HttpServletRequest request) throws SQLException {
		proxima = "uploadProject.jsp";
		projeto = (Projeto)request.getAttribute("projeto");
		
		return proxima;
	}

}
