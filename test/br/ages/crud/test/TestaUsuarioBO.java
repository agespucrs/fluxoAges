package br.ages.crud.test;

import static org.junit.Assert.fail;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import br.ages.crud.bo.UsuarioBO;
import br.ages.crud.dao.UsuarioDAO;
import br.ages.crud.exception.NegocioException;
import br.ages.crud.exception.PersistenciaException;
import br.ages.crud.model.PerfilAcesso;
import br.ages.crud.model.StatusUsuario;
import br.ages.crud.model.TipoUsuario;
import br.ages.crud.model.Usuario;

@RunWith(MockitoJUnitRunner.class)
public class TestaUsuarioBO {

	@Mock
	private UsuarioDAO usuarioMockDAO;
	private UsuarioBO usuarioBO;
	
	@Before
	public void setUp() throws Exception {
		usuarioBO = new UsuarioBO();
		usuarioBO.setUsuarioDAO(usuarioMockDAO);
	}
	
	@Test
	public void testValidaLogin() throws  PersistenciaException, NegocioException {
		Usuario usuario = new Usuario("admin", "admin");
		Mockito.when(usuarioMockDAO.validarUsuario(usuario)).thenReturn(usuario);
		Assert.assertTrue(usuarioBO.validaLogin(usuario).equals(usuario));

	}

	@Test
	public void testValidaUsuarioResponsavel() throws PersistenciaException {
		TipoUsuario tipoUsuario = new TipoUsuario();
		
		Usuario usuario = new Usuario("admin", "admin", "0000000", "Cássio Trindade", "cassiot@gmmail.com", StatusUsuario.ATIVO, tipoUsuario, PerfilAcesso.ADMINISTRADOR);
		Mockito.when(usuarioMockDAO.validarUsuario(usuario)).thenReturn(usuario);
		Assert.assertEquals(true,usuarioBO.validaUsuarioResponsavel("admin", "admin"));

	}

	@Test
	public void testValidaUsuario() {
		fail("Not yet implemented");
	}

	@Test
	public void testCadastraUsuario() {
		fail("Not yet implemented");
	}

	@Test
	public void testListarUsuarioAlunos() {
		fail("Not yet implemented");
	}

	@Test
	public void testListarUsuario() {
		fail("Not yet implemented");
	}

	@Test
	public void testRemoverUsuario() {
		fail("Not yet implemented");
	}

	@Test
	public void testConsultaTipoUsuario() {
		fail("Not yet implemented");
	}

	@Test
	public void testBuscaUsuarioId() {
		fail("Not yet implemented");
	}

	@Test
	public void testEditaUsuario() {
		fail("Not yet implemented");
	}

	@Test
	public void testListaTipoUsuarios() {
		fail("Not yet implemented");
	}

	@Test
	public void testListaUsuariosReponsaveis() {
		fail("Not yet implemented");
	}

	@Test
	public void testBuscaSenha() {
		fail("Not yet implemented");
	}

}
