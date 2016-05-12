package br.ages.crud.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.sql.SQLException;

import org.apache.log4j.Logger;
import org.junit.Test;

import br.ages.crud.model.Usuario;
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
	public static void main(String[] args) {
	  Logger logger = Logger.getLogger("servlet.FileUploadServlet");
	  logger.info("Iniciando procedimentos");
	   Usuario u = new Usuario();
	  logger.debug("Criando novo objeto venda");
	   u.setMatricula("1234");
	   u.setNome("MINHA VENDA XX");
	  logger.info("venda salvo no banco com sucesso");
	  System.out.println(u.toString());
}

}
