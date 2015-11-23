package br.ages.crud.command;

import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;

import br.ages.crud.exception.NegocioException;

/**
 * Interface que implementa o Designer Partner Command (GoF)
 * 
 * @author Cássio Trindade
 *
 */
public interface Command {
	/**
	 * execução de comando dos request das telas.
	 * @param request
	 * @return
	 * @throws SQLException 
	 * @throws NegocioException
	 */

	public String execute(HttpServletRequest request) throws SQLException, NegocioException ;
}
