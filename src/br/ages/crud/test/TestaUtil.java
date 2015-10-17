package br.ages.crud.test;

import static org.junit.Assert.*;

import java.sql.SQLException;

import org.junit.Test;

import br.ages.crud.util.ConexaoUtil;

/** 
 * @author Daniele Souza e Victor Diehl
 */

public class TestaUtil {

	@Test
	public void test() {
		fail("Not yet implemented");
	}
	
	@Test
	public void testConexaoBanco() throws ClassNotFoundException, SQLException {
		boolean conexaoOK = ConexaoUtil.getConexao() != null;

		assertEquals(true, conexaoOK);

	}

}
