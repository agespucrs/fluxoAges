package br.ages.crud.test;

import static org.junit.Assert.assertEquals;

import java.sql.SQLException;
import java.text.ParseException;
import java.util.Date;

import org.junit.Before;
import org.junit.Test;

import br.ages.crud.dao.UsuarioDAO;
import br.ages.crud.exception.PersistenciaException;
import br.ages.crud.model.Usuario;

/**
 * @author Cassio Trindade
 */
public class TesteUsuarioDAO {
	private Usuario usuario, usuarioErrado, usuarioCadastro, usuarioRemove, usuarioDeleta, usuarioBusca;
	private UsuarioDAO dao;
	private int idUsuario;

	@Before
	public void init() {
		usuario         = new Usuario("admin", "admin");
		usuarioErrado   = new Usuario("errado", "senhaErrada");
		/*usuarioCadastro = new Usuario("cadastro", "admin", "S", "0cadastro", "Nome Teste","Email Teste", new Date());
		usuarioRemove   = new Usuario("remove", "admin", "S", "0remove", "Nome Teste","Email Teste", new Date());
		usuarioBusca    = new Usuario("busca", "admin", "S", "0busca", "Nome Teste","Email Teste", new Date());
		usuarioDeleta   = new Usuario("deleta", "admin", "S", "0deleta", "Nome Teste","Email Teste", new Date());*/
		dao = new UsuarioDAO();
	}

	@Test
	public void testLoginUsuarioOk() throws PersistenciaException {

		boolean isValido = dao.validarUsuario(usuario) == null;

		assertEquals(true, isValido);
	}

	@Test
	public void testLoginUsuarioNaoOk() throws PersistenciaException {

		boolean isValido = dao.validarUsuario(usuarioErrado) != null;

		assertEquals(false, isValido);
	}

	@Test
	public void testCadastraUsuario() throws PersistenciaException,	SQLException, ParseException {

		int idUsuario = dao.cadastrarUsuario(usuarioCadastro);
		Usuario usuarioCadastrado = dao.buscaUsuarioId(idUsuario);

		assertEquals(idUsuario, usuarioCadastrado.getIdUsuario());

		dao.removerUsuario(idUsuario);
	}

	@Test
	public void testBuscaUsuarioPorNome() throws Exception {

		dao.cadastrarUsuario(usuarioBusca);
		Usuario usuarioNome = dao.buscaUsuarioNome("Nome Teste");

		assertEquals("Nome Teste", usuarioNome.getNome());
		
		dao.removerUsuario(usuarioNome.getIdUsuario());
	}

	@Test
	public void testDeletaUsuario() throws Exception {

		int idUsuario = dao.cadastrarUsuario(usuarioRemove);
		boolean removidoOK = dao.removerUsuario(idUsuario);
	
		assertEquals(false, removidoOK);
	}

	/*
	 * @Test public void testTotalUsuarios() throws Exception {
	 * 
	 * int total = dao.listaTotalUsuario(); boolean totalOK; if (total > 0)
	 * totalOK = true; else totalOK = false; assertEquals(totalOK, true);
	 * 
	 * 
	 * }
	 */
}
