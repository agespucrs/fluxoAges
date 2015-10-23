package br.ages.crud.test;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Date;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import br.ages.crud.bo.ProjetoBO;
import br.ages.crud.model.Projeto;
import br.ages.crud.model.Stakeholder;
import br.ages.crud.model.StatusProjeto;
import br.ages.crud.model.Usuario;
import br.ages.crud.util.Util;

public class TestaProjetoBO {
	
	private Projeto projetoCerto, projetoErrado;
	private ProjetoBO projetoBO;
	
	

	@Before
	public void setUp() throws Exception {
		projetoBO = new ProjetoBO();
		ArrayList<Usuario> usuarios = new ArrayList<Usuario>();
		ArrayList<Stakeholder> stakeholders = new ArrayList<Stakeholder>();
		Date dataInicio = Util.stringToDate("01/06/2015");
		Date dataFimPrevisto = Util.stringToDate("01/12/2015");
		Date dataFim = Util.stringToDate("01/01/2016");
		
		
		projetoCerto = new Projeto();
		projetoCerto.setIdProjeto(10);
		projetoCerto.setNomeProjeto("ProjetoCorreto");
		projetoCerto.setUsuarios(usuarios);
		projetoCerto.setStatus(StatusProjeto.ATIVO);
		projetoCerto.setWorkspace("Workspace");
		projetoCerto.setStakeholders(stakeholders);
		projetoCerto.setDataInicio(dataInicio);
		projetoCerto.setDataFimPrevisto(dataFimPrevisto);
		projetoCerto.setDataFim(dataFim);
		
		projetoErrado = new Projeto(-1, null, null, null, null, null, null, null, null);
		
		}

	@Test
	public void testValidarProjetoOK() {
		boolean valido = projetoBO.validarProjeto(projetoCerto);
		assertEquals(valido, true);
	}
	
	@Test
	public void testValidarProjetoNotOK(){
		boolean valido = projetoBO.validarProjeto(projetoErrado);
		assertEquals(valido, false);
	}
}
