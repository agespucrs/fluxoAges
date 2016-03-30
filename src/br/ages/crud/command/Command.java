package br.ages.crud.command;

import java.sql.SQLException;
import java.text.ParseException;

import javax.servlet.http.HttpServletRequest;

import br.ages.crud.exception.NegocioException;
import br.ages.crud.exception.PersistenciaException;

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
	 * @throws PersistenciaException 
	 * @throws ParseException 
	 */

	public String execute(HttpServletRequest request) throws SQLException, NegocioException, ParseException, PersistenciaException ;
}
