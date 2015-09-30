package br.ages.crud.command;

import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;

import br.ages.crud.bo.UsuarioBO;

public class EditUserCommand implements Command{
	

	private String proxima;
	
	private UsuarioBO usuarioBO;

	@Override
	public String execute(HttpServletRequest request) throws SQLException {
		usuarioBO = new UsuarioBO();
		proxima = null;		
		
		return proxima;
	}

}
