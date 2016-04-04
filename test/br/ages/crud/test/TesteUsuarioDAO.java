package br.ages.crud.test;

import static org.junit.Assert.assertEquals;

import java.sql.SQLException;
import java.text.ParseException;

import org.junit.Before;
import org.junit.Test;

import br.ages.crud.dao.UsuarioDAO;
import br.ages.crud.exception.PersistenciaException;
import br.ages.crud.model.PerfilAcesso;
import br.ages.crud.model.StatusUsuario;
import br.ages.crud.model.TipoUsuario;
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
		TipoUsuario tipo = new TipoUsuario();
		tipo.setIdTipoUsuario(1);
		usuario = new Usuario("admin", "admin", "123456789", "Nome Teste", "lelew@lelew.com", StatusUsuario.ATIVO, tipo, PerfilAcesso.ADMINISTRADOR);
		usuarioErrado = new Usuario("errado", "senhaErrada", "bibibi", "nome de árvore", "lelewEMAIL.com", StatusUsuario.ATIVO, tipo, PerfilAcesso.ADMINISTRADOR);
		usuarioCadastro = new Usuario("cadastro", "admin", "123456789", "Nome Teste", "lelew@lelew.com", StatusUsuario.ATIVO, tipo, PerfilAcesso.ADMINISTRADOR);
		usuarioRemove = new Usuario("remove", "admin", "123456789", "Nome Teste", "lelew@lelew.com", StatusUsuario.ATIVO, tipo, PerfilAcesso.ADMINISTRADOR);
		usuarioDeleta = new Usuario("deleta", "admin", "123456789", "Nome Teste", "lelew@lelew.com", StatusUsuario.ATIVO, tipo, PerfilAcesso.ADMINISTRADOR);
		usuarioBusca =  new Usuario("busca", "admin", "123456789", "Nome Teste", "lelew@lelew.com", StatusUsuario.ATIVO, tipo, PerfilAcesso.ADMINISTRADOR);
		dao = new UsuarioDAO();
	}

	@Test
	public void testLoginUsuarioOk() throws PersistenciaException {
		boolean isValido = false;
		if (dao.validarUsuario(usuario) != null)
			isValido = true;
		assertEquals(true, isValido);
	}

	@Test
	public void testLoginUsuarioNaoOk() throws PersistenciaException {

		boolean isValido = false;
		if (dao.validarUsuario(usuarioErrado) != null)
			isValido = true;
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
	 * int total = dao.listaTotalUsuario(); boolean totalOK; if (total > 0)
	 * totalOK = true; else totalOK = false; assertEquals(totalOK, true);
	 * }
	 */
}
