package br.ages.crud.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

import br.ages.crud.dao.ProjetoDAO;
import br.ages.crud.exception.PersistenciaException;
import br.ages.crud.model.Projeto;
import br.ages.crud.model.Stakeholder;
import br.ages.crud.model.StatusProjeto;
import br.ages.crud.model.Usuario;
import br.ages.crud.util.Util;

/** 
 * @author Daniele Souza e Victor Diehl
 */

public class TestaProjetoDAO {
	private Projeto projeto, projetoErrado, projetoRemove, projetoCadastro, projetoBusca;
	private ProjetoDAO dao;
	
	private ArrayList<Usuario> usuarios = new ArrayList<>();
	private ArrayList<Stakeholder> stakeholders = new ArrayList<>();
	 
	
	@Before
	public void init() throws ParseException{
		projeto = new Projeto(0, "pips", this.usuarios, "eoq", StatusProjeto.CONCLUIDO, this.stakeholders, Util.stringToDate("05/08/2015"), Util.stringToDate("10/22/2015"), Util.stringToDate("10/12/2015"));
		projetoErrado = new Projeto(0, "pips", this.usuarios, "eoq", StatusProjeto.CONCLUIDO, this.stakeholders, Util.stringToDate("05/08/2015"), Util.stringToDate("10/22/2015"), Util.stringToDate("10/12/2015"));

		dao = new ProjetoDAO();
	}	
	

	@Test
	public void testaListarProjetos() {
		fail("Not yet implemented");
	}
	
	@Test
	public void testaBuscarUsuariosProjeto() {
		fail("Not yet implemented");
	}
	
	@Test
	public void testaCadastrarProjeto() throws PersistenciaException, SQLException, ParseException {
		boolean projetoOk = false; 
		dao.cadastrarProjeto(this.projeto);
		Projeto projeto =  dao.consultarProjeto(0);
		if (projeto != null) {
			projetoOk = true;
		}
		
		assertEquals(projetoOk, true);
	}
	
	@Test
	public void testaInserirUsuariosProjeto() {
		fail("Not yet implemented");
	}
	
	@Test
	public void testaConsultarProjeto() {
		fail("Not yet implemented");
	}
	
	@Test
	public void testaEditarProjeto() {
		fail("Not yet implemented");
	}

	@Test
	public void testaRemoverProjeto() {
		fail("Not yet implemented");
	}
}
