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

public class TesteUsuarioDAO {
	private Usuario usuario, usuarioErrado, usuarioCadastrado;
	private UsuarioDAO dao;
	private int idUsuario;

	@Before
	public void init() {
		usuario = new Usuario("admin", "admin", "S", "0", "Nome Teste",
				"Email Teste", new Date());
		usuarioErrado = new Usuario("admin", "senhaErrada");
		usuarioCadastrado = new Usuario();
		dao = new UsuarioDAO();
	}

	@Test
	public void testCadastraUsuario() throws PersistenciaException,
			SQLException, ParseException {

		int idUsuario = dao.cadastrarUsuario(usuario);
		Usuario usuarioCadastrado = dao.buscaUsuarioId(idUsuario);

		assertEquals(idUsuario, usuarioCadastrado.getIdUsuario());

		dao.removerUsuario(idUsuario);

	}

	@Test
	public void testLoginUsuarioOk() throws PersistenciaException {

		boolean isValido = dao.validarUsuario(usuario);

		assertEquals(true, isValido);
	}

	@Test
	public void testLoginUsuarioNaoOk() throws PersistenciaException {

		boolean isValido = dao.validarUsuario(usuarioErrado);

		assertEquals(false, isValido);
	}

	@Test
	public void testBuscaUsuarioPorNome() throws Exception {

		dao.cadastrarUsuario(usuario);
		Usuario usuarioNome = dao.buscaUsuarioNome("Nome Teste");

		assertEquals("NomeTeste", usuarioNome.getNome());
		
		dao.removerUsuario(usuarioNome.getIdUsuario());

	}

	@Test
	public void testDeletaUsuario() throws Exception {

		dao.cadastrarUsuario(usuario);
		boolean removidoOK = dao.removerUsuario(idUsuario);
	
		assertEquals(removidoOK, false);

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
