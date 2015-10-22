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

public class TestaProjetoBO {
	
	private Projeto projetoCerto, projetoErrado;
	private ProjetoBO projetoBO;
	
	

	@Before
	public void setUp() throws Exception {
		projetoBO = new ProjetoBO();
		ArrayList<Usuario> usuarios = new ArrayList<Usuario>();
		ArrayList<Stakeholder> stakeholders = new ArrayList<Stakeholder>();
		Date dataInicio = new Date();
		Date dataFim = new Date();
		
		
		//projetoCerto = new Projeto(10, "Projeto Correto", equipes, "Spipspops 2.0", StatusProjeto.ATIVO , stakeholders, dataInicio, dataFim);
		projetoErrado = new Projeto(-1, null, null, null, null, null, null, null);
		
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
