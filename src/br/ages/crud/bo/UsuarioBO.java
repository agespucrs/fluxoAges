package br.ages.crud.bo;

import java.sql.SQLException;
import java.text.Normalizer;
import java.text.ParseException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import br.ages.crud.dao.UsuarioDAO;
import br.ages.crud.exception.NegocioException;
import br.ages.crud.exception.PersistenciaException;
import br.ages.crud.model.TipoUsuario;
import br.ages.crud.model.Usuario;
import br.ages.crud.util.MensagemContantes;
import br.ages.crud.validator.SenhaValidator;

/**
 * Gerencia os comportamentos de neggocio do Usuario Associa os parametros da
 * tela as propriedades da classe
 * 
 * @author Cassio Trindade
 * 
 */
public class UsuarioBO {
	UsuarioDAO usuarioDAO = null;

	public UsuarioBO() {
		usuarioDAO = new UsuarioDAO();
	}

	/**
	 * Valida se a senha do Responsavel pelo ponto esta correta. Estando correta
	 * altera o Status do ponto.
	 * 
	 * @param usuario
	 * @param senhaResponsavel
	 * @return
	 * @throws PersistenciaException
	 */
	public boolean validaUsuarioResponsavel(String usuario, String senhaResponsavel) throws PersistenciaException {
		Usuario usuarioResponsavel = new Usuario(usuario, senhaResponsavel);
		Usuario u = usuarioDAO.validarUsuario(usuarioResponsavel);
		Boolean ok = u != null ? true : false;
		return ok;
	}

	/**
	 * Valida Usuario no sistema
	 * 
	 * @param request
	 * @return
	 * @throws NegocioException
	 */
	public Usuario validaUsuario(Usuario usuario) throws NegocioException {

		Usuario user = null;
		try {
			// valida se o usuario existe na base

			user = usuarioDAO.validarUsuario(usuario);
			if (user == null) {
				throw new NegocioException(MensagemContantes.MSG_ERR_USUARIO_SENHA_INVALIDOS);
			}

		} catch (Exception e) {
			e.printStackTrace();
			throw new NegocioException(e);
		}

		return user;

	}

	/**
	 * Valida os dados de usuï¿½rio na tela de cadastro com erros aglutinados
	 * 
	 * @param usuario
	 * @return
	 * @throws NegocioException
	 */
	public boolean validaUsuarioA(Usuario usuario) throws NegocioException {
		boolean isValido = true;
		StringBuilder msg = new StringBuilder();
		msg.append(MensagemContantes.MSG_ERR_USUARIO_DADOS_INVALIDOS.concat("<br/>"));

		String EMAIL_PATTERN = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";

		try {
			// valida campos estï¿½o preenchidos corretamente
			// Matricula
			/*
			 * if (usuario.getMatricula() == null ||
			 * "".equals(usuario.getMatricula())) { isValido = false;
			 * msg.append(MensagemContantes.MSG_ERR_CAMPO_OBRIGATORIO.replace("?",
			 * "Matricula ").concat("<br/>"));
			 * 
			 * }
			 */
			if (!usuario.getMatricula().matches("\\d{5,9}")) {
				isValido = false;
				msg.append(MensagemContantes.MSG_ERR_MATRICULA_INVALIDA.replace("?", "Matricula ").concat("<br/>"));
			}
			// Nome
			if (usuario.getNome() == null || "".equals(usuario.getNome())) {
				isValido = false;
				msg.append(MensagemContantes.MSG_ERR_CAMPO_NOME_OBRIGATORIO + "<br/>");
			}
			// Usuï¿½rio
			if (usuario.getUsuario() == null || "".equals(usuario.getUsuario())) {
				isValido = false;
				msg.append(MensagemContantes.MSG_ERR_CAMPO_OBRIGATORIO.replace("?", "Usuario ").concat("<br/>"));
			}
			if (!usuario.getEmail().matches(EMAIL_PATTERN)) {
				isValido = false;
				msg.append(MensagemContantes.MSG_ERR_EMAIL_INVALIDO.replace("?", "Email ").concat("<br/>"));
			}

			String nome = Normalizer.normalize(usuario.getNome(), Normalizer.Form.NFD).replaceAll("\\p{InCombiningDiacriticalMarks}+", "");

			if (!nome.matches("(([A-Z][a-z]*)\\s{0,1})+")) {
				isValido = false;
				msg.append(MensagemContantes.MSG_ERR_NOME_INVALIDO.replace("?", "Nome ").concat("<br/>"));
			}
			// Senha
			Map<String, Object> valores = new HashMap<>();
			valores.put("Senha", usuario.getSenha());
			if (!new SenhaValidator().validar(valores)) {
				isValido = false;
			}

			// flag administrador
			/*
			 * if (usuario.getPerfilAcesso() == null ||
			 * "".equals(usuario.getPerfilAcesso())) { isValido = false;
			 * msg.append(MensagemContantes.MSG_ERR_CAMPO_OBRIGATORIO.replace("?",
			 * "Flag Administrador").concat("<br/>")); } // tipo usuario if
			 * (usuario.getTipoUsuario() == null ||
			 * "".equals(usuario.getTipoUsuario())) { isValido = false;
			 * msg.append(MensagemContantes.MSG_ERR_CAMPO_OBRIGATORIO.replace("?",
			 * "Flag Tipo Usuï¿½rio").concat("<br/>")); }
			 */

			// valida se Pessoa esta ok
			if (!isValido) {
				throw new NegocioException(msg.toString());
			}
			//

		} catch (Exception e) {
			e.printStackTrace();
			throw new NegocioException(e);
		}

		return isValido;

	}

	/**
	 * Valida os dados de usuï¿½rio na tela de cadastro.
	 * 
	 * @param usuario
	 * @return
	 * @throws NegocioException
	 */

	/**
	 * Cadastra Usuario em nï¿½vel de negï¿½cio, chamando o DAO
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
	public List<Usuario> listarUsuarioAlunos() throws NegocioException {

		List<Usuario> listUser = null;

		try {
			listUser = usuarioDAO.listarUsuariosAlunos();
		} catch (PersistenciaException | SQLException e) {
			e.printStackTrace();
			throw new NegocioException(e);
		}

		return listUser;
	}

	/**
	 * Lista as pessoas a partir das classes de DAO
	 * 
	 * @return
	 * @throws NegocioException
	 */
	public List<Usuario> listarUsuario() throws NegocioException {

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
	 * Remove usuï¿½rio da base
	 * 
	 * @param idUsuario
	 * @throws NegocioException
	 * @throws SQLException
	 */
	public void removerUsuario(Integer idUsuario) throws NegocioException, SQLException {
		try {

			if (validaUsuarioProjeto(idUsuario))
				usuarioDAO.removerUsuario(idUsuario);
		} catch (PersistenciaException e) {
			e.printStackTrace();
			throw new NegocioException(MensagemContantes.MSG_ERR_REMOVE_USUARIO_EM_PROJETO);
		}
	}

	private boolean validaUsuarioProjeto(Integer idUsuario) throws NegocioException, SQLException {
		// chama um DAO que verifica se o usuario estÃ¡ em algum projeto,
		// se estiver(retorna qualquer coisa diferente de -1), retorna falso.
		int id = -1;
		try {
			id = usuarioDAO.verificaUsuarioProjeto(idUsuario);
		} catch (PersistenciaException e) {
			e.printStackTrace();
			throw new NegocioException(e);
		}
		if (id != -1)
			return false;
		return true;
	}

	public TipoUsuario consultaTipoUsuario(String idTipoUsuario) throws NegocioException {
		try {
			TipoUsuario tipoUsuario = usuarioDAO.consultaTipoUsuario(idTipoUsuario);
			return tipoUsuario;
		} catch (Exception e) {
			e.printStackTrace();
			throw new NegocioException(e);
		}
	}

	public Usuario buscaUsuarioId(int idUsuario) throws NegocioException {
		try {
			Usuario usuario = usuarioDAO.buscaUsuarioId(idUsuario);

			return usuario;
		} catch (Exception e) {
			e.printStackTrace();
			throw new NegocioException(e);
		}
	}

	public void editaUsuario(Usuario usuario) throws NegocioException {
		try {
			usuarioDAO.editaUsuario(usuario);
		} catch (Exception e) {
			e.printStackTrace();
			throw new NegocioException(e);
		}

	}

	public List<TipoUsuario> listaTipoUsuarios() throws NegocioException {
		try {

			List<TipoUsuario> tipoUsuarios = usuarioDAO.listaTipoUsuarios();
			return tipoUsuarios;

		} catch (Exception e) {
			e.printStackTrace();
			throw new NegocioException(e);
		}
	}

	/**
	 * Lista os usuários responsáveis
	 * 
	 * @return
	 * @throws NegocioException
	 * @throws SQLException
	 */
	public List<Usuario> listaUsuariosReponsaveis() throws NegocioException, SQLException {
		List<Usuario> listResponsaveis = null;

		try {
			listResponsaveis = usuarioDAO.listarUsuariosReponsaveis();
		} catch (PersistenciaException e) {
			e.printStackTrace();
			throw new NegocioException(e);
		}

		return listResponsaveis;
	}

	public Usuario buscaSenha(Usuario usuarioDto) throws NegocioException{

		Usuario usuario;
			try {
				usuario = usuarioDAO.buscaUsuarioPorSenha(usuarioDto);
				} catch (Exception e) {
				e.printStackTrace();
				throw new NegocioException(e);
			}
		
	return usuario;
	}

}
