package br.ages.crud.bo;

import java.sql.SQLException;
import java.text.ParseException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import br.ages.crud.dao.UsuarioDAO;
import br.ages.crud.exception.NegocioException;
import br.ages.crud.exception.PersistenciaException;
import br.ages.crud.model.Usuario;
import br.ages.crud.util.MensagemContantes;
import br.ages.crud.validator.LoginValidator;
import br.ages.crud.validator.SenhaValidator;

/**
 * Gerencia os comportamentos de neg�cio do Usu�rio Associa os par�metros da
 * tela as propriedades da classe
 * 
 * @author C�ssio Trindade
 * 
 */
public class UsuarioBO {
	UsuarioDAO usuarioDAO = null;

	public UsuarioBO() {
		usuarioDAO = new UsuarioDAO();
	}

	/**
	 * Valida Usu�rio no sistema
	 * 
	 * @param request
	 * @return
	 * @throws NegocioException
	 */
	public boolean validaUsuario(Usuario usuario) throws NegocioException {
		boolean isValido = false;
		try {
			// valida se o usu�rio est� na base
			UsuarioDAO usuarioDAO = new UsuarioDAO();
			isValido = usuarioDAO.validarUsuario(usuario);
			if (!isValido) {
				throw new NegocioException(MensagemContantes.MSG_ERR_USUARIO_SENHA_INVALIDOS);
			}

		} catch (Exception e) {
			e.printStackTrace();
			throw new NegocioException(e);
		}

		return isValido;

	}

	/**
	 * Valida os dados de usu�rio na tela de cadastro com erros aglutinados
	 * 
	 * @param usuario
	 * @return
	 * @throws NegocioException
	 */
	public boolean validaCadastroUsuarioA(Usuario usuario) throws NegocioException {
		boolean isValido = true;
		StringBuilder msg = new StringBuilder();

		try {
			// valida campos est�o preenchidos corretamente
			// Matricula
			if (usuario.getMatricula() == null || "".equals(usuario.getMatricula())) {
				isValido = false;
				msg.append(MensagemContantes.MSG_ERR_CAMPO_OBRIGATORIO.replace("?", "Matricula ").concat("<br/>"));

			}
			if (!usuario.getMatricula().matches("\\d{9}")){
				isValido = false;
				msg.append(MensagemContantes.MSG_ERR_MATRICULA_INVALIDA.replace("?", "Matricula ").concat("<br/>"));
			}
			// Nome
			if (usuario.getNome() == null || "".equals(usuario.getNome())) {
				isValido = false;
				msg.append(MensagemContantes.MSG_ERR_CAMPO_NOME_OBRIGATORIO + "<br/>");
			}
			// Usu�rio
			if (usuario.getUsuario() == null || "".equals(usuario.getUsuario())) {
				isValido = false;
				msg.append(MensagemContantes.MSG_ERR_CAMPO_OBRIGATORIO.replace("?", "Usuario ").concat("<br/>"));
			}

			// Senha
			Map<String, Object> valores = new HashMap<>();
			valores.put("Senha", usuario.getSenha());
			if (!new SenhaValidator().validar(valores)) {
				isValido = false;
			}

			// flag administrador
			if (usuario.getPerfilAcesso() == null) {
				isValido = false;
				msg.append(MensagemContantes.MSG_ERR_CAMPO_OBRIGATORIO.replace("?", "Flag Administrador").concat("<br/>"));
			}

			// valida se Pessoa esta ok
			if (!isValido) {
				throw new NegocioException(msg.append(MensagemContantes.MSG_ERR_PESSOA_DADOS_INVALIDOS).toString());
			}
			//
			if (usuario.getTipoUsuario() == null) {
				isValido = false;
				msg.append(MensagemContantes.MSG_ERR_CAMPO_OBRIGATORIO.replace("?", "Flag Tipo Usuário").concat("<br/>"));
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new NegocioException(e);
		}

		return isValido;

	}

	/**
	 * Valida os dados de usu�rio na tela de cadastro.
	 * 
	 * @param usuario
	 * @return
	 * @throws NegocioException
	 */
	public boolean validaCadastroUsuario(Usuario usuario) throws NegocioException {
		boolean isValido = true;
		try {
			// valida campos est�o preenchidos corretamente
			// Matricula
			if (usuario.getMatricula() == null || "".equals(usuario.getMatricula())) {
				throw new NegocioException(MensagemContantes.MSG_ERR_CAMPO_OBRIGATORIO.replace("?", "Matricula ").concat("<br/>"));
			}
			// Nome
			if (usuario.getNome() == null || "".equals(usuario.getNome())) {
				throw new NegocioException(MensagemContantes.MSG_ERR_CAMPO_NOME_OBRIGATORIO);
			}
			// Usuario
			if (usuario.getUsuario() == null || "".equals(usuario.getUsuario())) {
				throw new NegocioException(MensagemContantes.MSG_ERR_CAMPO_OBRIGATORIO.replace("?", "Usuario ").concat("<br/>"));
			}

			// Senha
			if (usuario.getSenha() == null || "".equals(usuario.getSenha())) {
				throw new NegocioException(MensagemContantes.MSG_ERR_CAMPO_OBRIGATORIO.replace("?", "Senha ").concat("<br/>"));
			}

			// flag administrador
			if (usuario.getPerfilAcesso() == null) {
				throw new NegocioException(MensagemContantes.MSG_ERR_CAMPO_OBRIGATORIO.replace("?", "Flag Administrado").concat("<br/>"));
			}

			// valida se Pessoa esta ok
			if (!isValido) {
				throw new NegocioException(MensagemContantes.MSG_ERR_PESSOA_DADOS_INVALIDOS);
			}

		} catch (Exception e) {
			e.printStackTrace();
			throw new NegocioException(e);
		}

		return isValido;

	}

	/**
	 * Cadastra Usuario em n�vel de neg�cio, chamando o DAO
	 * 
	 * @param pessoaDTO
	 * @throws NegocioException
	 * @throws SQLException
	 * @throws ParseException
	 */
	public void cadastraUsuario(Usuario usuario) throws NegocioException, SQLException, ParseException {

		try {
			usuarioDAO.cadastrarUsuario(usuario);
		} catch (PersistenciaException e) {
			e.printStackTrace();
			throw new NegocioException(e);
		}

	}

	/**
	 * Lista as pessoas a partir das classes de DAO
	 * 
	 * @return
	 * @throws NegocioException
	 */
	public List<Usuario> listarUsuario() throws NegocioException   {

		List<Usuario> listUser = null;

		try {
			listUser = usuarioDAO.listarUsuarios();
		} catch (PersistenciaException | SQLException e) {
			e.printStackTrace();
			throw new NegocioException(e);
		}

		return listUser;

	}
/**
 * Remove usu�rio da base
 * @param idUsuario
 * @throws NegocioException
 */
	public void removerUsuario(Integer idUsuario) throws NegocioException {
		try {
			usuarioDAO.removerUsuario(idUsuario);
		} catch (PersistenciaException e) {
			e.printStackTrace();
			throw new NegocioException(e);
		}
	}

public Usuario getUsuario(int idUsuario) throws NegocioException {
	try {
		Usuario usuario = usuarioDAO.buscaUsuarioId(idUsuario);
		
		return usuario;
	} catch (PersistenciaException e) {
		e.printStackTrace();
		throw new NegocioException(e);
	}	
}

}
