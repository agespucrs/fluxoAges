package br.ages.crud.test;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Date;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import br.ages.crud.bo.ProjetoBO;
import br.ages.crud.exception.NegocioException;
import br.ages.crud.model.Projeto;
import br.ages.crud.model.Stakeholder;
import br.ages.crud.model.StatusProjeto;
import br.ages.crud.model.Usuario;
import br.ages.crud.util.Util;

public class TestaProjetoBO {
	
	private Projeto projetoCerto, projetoErrado, projetoErradoData;
	private ProjetoBO projetoBO;
	
	

	@Before
	public void setUp() throws Exception {
		projetoBO = new ProjetoBO();
		ArrayList<Usuario> usuarios = new ArrayList<Usuario>();
		ArrayList<Stakeholder> stakeholders = new ArrayList<Stakeholder>();
		
		
		projetoCerto = new Projeto();
		projetoCerto.setIdProjeto(10);
		projetoCerto.setNomeProjeto("ProjetoCorreto");
		projetoCerto.setUsuarios(usuarios);
		projetoCerto.setStatusProjeto(StatusProjeto.ATIVO);
		projetoCerto.setWorkspace("Workspace");
		projetoCerto.setStakeholders(stakeholders);
		projetoCerto.setDataInicio(Util.stringToDate("01/06/2015"));
		projetoCerto.setDataFimPrevisto(Util.stringToDate("01/12/2015"));
		projetoCerto.setDataFim(Util.stringToDate("01/01/2016"));
		
		projetoErrado = new Projeto(-1, null, null, null, null, null, null, null, null);
		
		projetoErradoData = new Projeto();
		projetoErradoData.setIdProjeto(10);
		projetoErradoData.setNomeProjeto("ProjetoErradoData");
		projetoErradoData.setUsuarios(usuarios);
		projetoErradoData.setStatusProjeto(StatusProjeto.CONCLUIDO);
		projetoErradoData.setWorkspace("Workspace");
		projetoErradoData.setStakeholders(stakeholders);
		projetoErradoData.setDataInicio(Util.stringToDate("01/01/2016"));
		projetoErradoData.setDataFimPrevisto(Util.stringToDate("01/12/2015"));
		projetoErradoData.setDataFim(Util.stringToDate("01/01/2016"));
		
		
		
		}

	@Test
	public void testValidarProjetoOK() {
		boolean valido = false;
		try {
			valido = projetoBO.validarProjeto(projetoCerto);
		} catch (NegocioException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		assertEquals(valido, true);
	}
	
	@Test
	public void testValidarProjetoNotOK(){
		boolean valido = true;
		try {
			valido = projetoBO.validarProjeto(projetoErrado);
		} catch (NegocioException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		assertEquals(valido, false);
	}
	
	@Test
	public void testValidarProjetoNotOK2(){
		boolean valido = true;
		try {
			valido = projetoBO.validarProjeto(projetoErradoData);
		} catch (NegocioException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		assertEquals(valido, false);
	}
}
