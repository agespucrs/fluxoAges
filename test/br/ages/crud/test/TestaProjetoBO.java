package br.ages.crud.test;

import java.sql.SQLException;
import java.text.ParseException;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import br.ages.crud.bo.ProjetoBO;
import br.ages.crud.dao.ProjetoDAO;
import br.ages.crud.exception.NegocioException;
import br.ages.crud.exception.PersistenciaException;
import br.ages.crud.model.Projeto;
import br.ages.crud.model.StatusProjeto;
import br.ages.crud.util.Util;

@RunWith(MockitoJUnitRunner.class)
public class TestaProjetoBO {

	@Mock
	private ProjetoDAO projetoMockDAO;

	private ProjetoBO projetoBO;

	@Before
	public void setUp() throws Exception {
		projetoBO = new ProjetoBO();
		projetoBO.setProjetoDAO(projetoMockDAO);
	}

	@Test
	public void testCadastrarProjetoOK() throws PersistenciaException, SQLException, NegocioException, ParseException {

		Projeto projeto = new Projeto(10, "Projeto OK", StatusProjeto.ATIVO, Util.stringToDate("01/01/2016"), Util.stringToDate("01/01/2015"));

		Mockito.when(projetoMockDAO.cadastrarProjeto(projeto)).thenReturn(true);

		boolean projetoOK = projetoBO.cadastrarProjeto(projeto);

		Assert.assertTrue(projetoOK == true);
	}

/*	@Test(expected=NegocioException.class)
	public void testCadastrarProjetoNaoOK() throws PersistenciaException, SQLException, ParseException {
		try {
			Projeto projeto = new Projeto(10, "Projeto Não OK", StatusProjeto.ATIVO, Util.stringToDate("01/01/2016"), Util.stringToDate("01/01/2015"));
			Mockito.when(projetoMockDAO.cadastrarProjeto(projeto)).thenReturn(false);
			boolean projetoOK = projetoBO.cadastrarProjeto(projeto);
		} catch (NegocioException e) {
		//	Assert.assertTrue("Mensagem do erro não esta correta:", e.getMessage().equals("MensagemContantes.MSG_ERR_CADASTRO_PROJETO"));
		}
	}*/

	@Test
	public void testBuscarProjetoOK() throws PersistenciaException, SQLException, NegocioException, ParseException {

		Projeto projeto = new Projeto(10, "Projeto CERTO", StatusProjeto.ATIVO, Util.stringToDate("01/01/2016"), Util.stringToDate("01/01/2015"));

		Mockito.when(projetoMockDAO.consultarProjeto(10)).thenReturn(projeto);

		Projeto projetoNaoOK = projetoBO.buscarProjeto(10);

		Assert.assertSame(projetoNaoOK.getNomeProjeto(), "Projeto CERTO");
	}

	@Test
	public void testBuscarProjetoNotOK() throws PersistenciaException, SQLException, NegocioException, ParseException {

		Projeto projeto = new Projeto(10, "Projeto ERRADO", StatusProjeto.ATIVO, Util.stringToDate("01/01/2016"), Util.stringToDate("01/01/2015"));
		System.out.println(projeto.getNomeProjeto());

		Mockito.when(projetoMockDAO.consultarProjeto(10)).thenReturn(projeto);

		Projeto projetoNaoOK = projetoBO.buscarProjeto(10);

		Assert.assertFalse("Esperado nome errado do Projeto", projetoNaoOK.getNomeProjeto().equals("Projeto CERTO"));
	}

}
