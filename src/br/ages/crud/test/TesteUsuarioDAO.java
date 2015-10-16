package br.ages.crud.test;


import static org.junit.Assert.assertEquals;

import java.sql.SQLException;
import java.util.Date;

import org.junit.Before;
import org.junit.Test;

import br.ages.crud.dao.UsuarioDAO;
import br.ages.crud.exception.PersistenciaException;
import br.ages.crud.model.Usuario;
import br.ages.crud.util.ConexaoUtil;


public class TesteUsuarioDAO {
	private Usuario usuario, usuarioErrado, usuarioCadastrado;
	private UsuarioDAO dao;
	private int idUsuario;

	@Before
	public void init() {
		usuario = new Usuario("admin", "admin", "S", "0", "Nome Teste", "Email Teste", new Date());
		usuarioErrado = new Usuario("admin", "senhaErrada");
		usuarioCadastrado = new Usuario();
		dao = new UsuarioDAO();
	}
	
	@Test
	public void testConexaoBanco() throws ClassNotFoundException, SQLException {

		boolean conexaoOK = ConexaoUtil.getConexao() != null;

		assertEquals(true, conexaoOK);

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

	/*@Test
	public void testBuscaUsuarioPorNome() throws Exception {
		// cadastra usuário;
		dao.cadastrarUsuario(usuario);
		
		Usuario usuarioNome = dao.buscaUsuarioNome("Nome Teste");

		assertEquals("Nome Teste", usuarioNome.getNome());

		// remove usuário
		usuarioCadastrado = dao.buscaUsuarioId(idUsuario);
		dao.removerUsuario(usuarioNome.getIdUsuario());
	
		
	}*/

	/*@Test
	public void testDeletaUsuario() throws Exception {

		// cadastra usuário;
		idUsuario = dao.cadastrarUsuario(usuario);
				
		boolean removidoOK = dao.removerUsuario(idUsuario );
		System.out.println(removidoOK + " ID USUARIO removido: " + idUsuario );
		
		assertEquals(removidoOK, false);

	}*/
	
	
	
	/*@Test
	public void testTotalUsuarios() throws Exception {
		
		int total = dao.listaTotalUsuario();
		boolean totalOK;
		if (total > 0)
			totalOK = true;
		else
			totalOK = false;
		assertEquals(totalOK, true);
	
		
	}*/
	
}
