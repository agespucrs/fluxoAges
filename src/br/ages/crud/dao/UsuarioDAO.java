package br.ages.crud.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import com.mysql.jdbc.Statement;

import br.ages.crud.exception.PersistenciaException;
import br.ages.crud.model.PerfilAcesso;
import br.ages.crud.model.StatusUsuario;
import br.ages.crud.model.TipoUsuario;
import br.ages.crud.model.Usuario;
import br.ages.crud.util.ConexaoUtil;
import br.ages.crud.util.MensagemContantes;

/**
 * 
 * @author Cassio Trindade
 *
 */
public class UsuarioDAO {

	private ArrayList<Usuario> listarUsuarios;

	public UsuarioDAO() {
		listarUsuarios = new ArrayList<>();
	}

	/**
	 * Autentica o usu?rio
	 * 
	 * @author cassio trindade
	 * @param usuarioDTO
	 * @return
	 * @throws PersistenciaException
	 */

	public Usuario validarUsuario(Usuario usuarioDTO) throws PersistenciaException {
		Usuario usuario = new Usuario();
		try {

			Connection conexao = ConexaoUtil.getConexao();
			StringBuilder sql = new StringBuilder();
			sql.append("select * from tb_usuario ");
			sql.append("where usuario = ? and senha = ?");

			PreparedStatement statement = conexao.prepareStatement(sql.toString());
			statement.setString(1, usuarioDTO.getUsuario());
			statement.setString(2, usuarioDTO.getSenha());

			ResultSet resultset = statement.executeQuery();
			if (resultset.next()) {
				usuario.setIdUsuario(resultset.getInt("ID_USUARIO"));
				usuario.setMatricula(resultset.getString("MATRICULA"));
				usuario.setNome(resultset.getString("NOME"));
				usuario.setPerfilAcesso(PerfilAcesso.valueOf(resultset.getString("PERFIL_ACESSO")));
				usuario.setEmail(resultset.getString("EMAIL"));
				usuario.setUsuario(resultset.getString("USUARIO"));
				usuario.setSenha(resultset.getString("SENHA"));
			} else
				usuario = null;
		} catch (ClassNotFoundException | SQLException e) {
			throw new PersistenciaException(e);
		}

		return usuario;
	}

	/**
	 * Lista os Usuarios da basee
	 * 
	 * @return
	 * @throws PersistenciaException
	 * @throws SQLException
	 */
	public List<Usuario> listarUsuarios() throws PersistenciaException, SQLException {
		Connection conexao = null;
		// tentativa de readaptação do listarUsuarios()
		try {
			conexao = ConexaoUtil.getConexao();

			StringBuilder sql = new StringBuilder();
			sql.append("SELECT ");
			sql.append("u.`ID_USUARIO`,");
			sql.append("u.`USUARIO`,");
			sql.append("u.`SENHA`,");
			sql.append("u.`PERFIL_ACESSO`,");
			sql.append("u.`STATUS_USUARIO`,");
			sql.append("u.`ID_TIPO_USUARIO`,");
			sql.append("u.`MATRICULA`,");
			sql.append("u.`NOME` unome,");
			sql.append("u.`EMAIL`,");
			sql.append("t.`ID_TIPO_USUARIO`,");
			sql.append("t.`NOME` tnome,");
			sql.append("t.`DESCRICAO`,");
			sql.append("t.`DATA_INCLUSAO`");
			
			sql.append("from ages_e.tb_usuario u inner join ages_e.tb_tipo_usuario t ");
			sql.append("on t.id_tipo_usuario = u.id_tipo_usuario ");
			
			//funciona no workbench mas aqui n�o
			sql.append("where STATUS_USUARIO='ATIVO'");
			

			PreparedStatement statement = conexao.prepareStatement(sql.toString());
			ResultSet resultset = statement.executeQuery();
			while (resultset.next()) {
				Usuario dto = new Usuario();
				TipoUsuario tipoUsuario = new TipoUsuario();
				dto.setIdUsuario(resultset.getInt("ID_USUARIO"));
				dto.setMatricula(resultset.getString("MATRICULA"));
				dto.setNome(resultset.getString("unome"));
				dto.setEmail(resultset.getString("EMAIL"));
				dto.setUsuario(resultset.getString("USUARIO"));
				dto.setSenha(resultset.getString("SENHA"));
				dto.setPerfilAcesso(PerfilAcesso.valueOf(resultset.getString("PERFIL_ACESSO")));
				dto.setStatusUsuario(StatusUsuario.valueOf(resultset.getString("STATUS_USUARIO")));
				tipoUsuario.setIdTipoUsuario(resultset.getInt("ID_TIPO_USUARIO"));
				tipoUsuario.setNome(resultset.getString("tnome"));
				tipoUsuario.setDescricao(resultset.getString("DESCRICAO"));
				dto.setTipoUsuario(tipoUsuario);

				listarUsuarios.add(dto);
			}

		} catch (ClassNotFoundException | SQLException e) {
			throw new PersistenciaException(e);
		} finally {
			conexao.close();
		}
		return listarUsuarios;
	}

	public int cadastrarUsuario(Usuario usuario) throws PersistenciaException, SQLException, ParseException {
		Connection conexao = null;

		try {
			Integer idUsuario = null;

			conexao = ConexaoUtil.getConexao();

			StringBuilder sql = new StringBuilder();
			sql.append("insert into tb_usuario (usuario, senha, perfil_acesso, status_usuario, id_tipo_usuario, matricula, nome, email, data_inclusao)");
			sql.append("values (?, ?, ?, ?, ?, ?, ?, ?, ? )");

			// converte a data para data Juliana, data que o banco reconhece;
			java.util.Date utilDate = new java.util.Date();
			java.sql.Date dataCadastro = new java.sql.Date(utilDate.getTime());

			// Cadastra a pessoa e gera e busca id gerado
			PreparedStatement statement = conexao.prepareStatement(sql.toString(), Statement.RETURN_GENERATED_KEYS);
			statement.setString(1, usuario.getUsuario());
			statement.setString(2, usuario.getSenha());
			statement.setString(3, String.valueOf(usuario.getPerfilAcesso()));
			statement.setString(4, String.valueOf(usuario.getStatusUsuario()));
			statement.setInt(5, usuario.getTipoUsuario().getIdTipoUsuario());
			statement.setString(6, usuario.getMatricula());
			statement.setString(7, usuario.getNome());
			statement.setString(8, usuario.getEmail());
			statement.setDate(9, dataCadastro);

			statement.executeUpdate();

			ResultSet resultset = statement.getGeneratedKeys();
			if (resultset.first()) {
				idUsuario = resultset.getInt(1);

			}
			return idUsuario;

		} catch (ClassNotFoundException | SQLException e) {
			throw new PersistenciaException(MensagemContantes.MSG_ERR_USUARIO_JA_EXISTENTE.replace("?", usuario.getUsuario()));

		} finally {
			conexao.close();
		}
	}

	/**
	 * M?todo de remo??o de um usu?rio a partir do seu id.
	 * 
	 * @param idPessoa
	 * @throws PersistenciaException
	 */
	public boolean removerUsuario(Integer idUsuario) throws PersistenciaException {
		boolean removidoOK = false;
		Connection conexao = null;
		try {
			conexao = ConexaoUtil.getConexao();

			StringBuilder sql = new StringBuilder();
			// sql.append("SELECT ID_TIPO_USUARIO FROM TB_USUARIO WHERE ID_USUARIO = ?
			// ")
			sql.append("update tb_usuario set STATUS_USUARIO='INATIVO'  where id_usuario= ? ");
			
			// sql.append("DELETE FROM TB_TIPO_USUARIO WHERE
			PreparedStatement statement = conexao.prepareStatement(sql.toString());
			statement.setInt(1, idUsuario);

			removidoOK = statement.execute();

		} catch (ClassNotFoundException | SQLException e) {
			throw new PersistenciaException(e);
		} finally {
			try {
				conexao.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return removidoOK;
	}

	public Usuario buscaUsuarioNome(String nomeUsuario) throws PersistenciaException {

		Usuario usuario = new Usuario();
		Connection conexao = null;
		try {
			conexao = ConexaoUtil.getConexao();

			StringBuilder sql = new StringBuilder();
			// sql.append("SELECT * FROM TB_USUARIO WHERE NOME = ?");
			sql.append("select ");
			sql.append("u.`id_usuario`,");
			sql.append("u.`usuario`,");
			sql.append("u.`senha`,");
			sql.append("u.`perfil_acesso`,");
			sql.append("u.`status_usuario`,");
			sql.append("u.`id_tipo_usuario`,");
			sql.append("u.`matricula`,");
			sql.append("u.`nome` unome,");
			sql.append("u.`email`,");
			sql.append("t.`id_tipo_usuario`,");
			sql.append("t.`nome` tnome,");
			sql.append("t.`descricao`,");
			sql.append("t.`data_inclusao`");
			sql.append("from ages_e.tb_usuario u inner join ages_e.tb_tipo_usuario t ");
			sql.append("on t.id_tipo_usuario = u.id_tipo_usuario ");
			sql.append("where u.nome = ?;");
			PreparedStatement statement = conexao.prepareStatement(sql.toString());
			statement.setString(1, nomeUsuario);

			ResultSet resultset = statement.executeQuery();

			while (resultset.next()) {

				usuario.setIdUsuario(resultset.getInt("ID_USUARIO"));
				usuario.setMatricula(resultset.getString("MATRICULA"));
				usuario.setNome(resultset.getString("unome"));
				usuario.setEmail(resultset.getString("EMAIL"));
				usuario.setUsuario(resultset.getString("USUARIO"));
				usuario.setSenha(resultset.getString("SENHA"));
				usuario.setPerfilAcesso(PerfilAcesso.valueOf(resultset.getString("PERFIL_ACESSO")));
				usuario.setStatusUsuario(StatusUsuario.valueOf(resultset.getString("STATUS_USUARIO")));
				TipoUsuario tipoUsuario = new TipoUsuario();
				tipoUsuario.setIdTipoUsuario(resultset.getInt("ID_TIPO_USUARIO"));
				tipoUsuario.setNome(resultset.getString("tnome"));
				tipoUsuario.setDescricao(resultset.getString("DESCRICAO"));
				usuario.setTipoUsuario(tipoUsuario);
			}
		} catch (ClassNotFoundException | SQLException e) {
			throw new PersistenciaException(e);
		} finally {
			try {
				conexao.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		return usuario;
	}

	public Usuario buscaUsuarioId(int idUsuario) throws PersistenciaException, SQLException {
		// adicionar informações de tipo de usuario?
		Usuario usuario = new Usuario();

		Connection conexao = null;
		try {
			conexao = ConexaoUtil.getConexao();

			StringBuilder sql = new StringBuilder();
			// sql.append("SELECT * FROM AGES_E.TB_USUARIO WHERE ID_USUARIO = ?;");
			//
			sql.append("select ");
			sql.append("u.`id_usuario`,");
			sql.append("u.`usuario`,");
			sql.append("u.`senha`,");
			sql.append("u.`perfil_acesso`,");
			sql.append("u.`status_usuario`,");
			sql.append("u.`id_tipo_usuario`,");
			sql.append("u.`matricula`,");
			sql.append("u.`nome` unome,");
			sql.append("u.`email`,");
			sql.append("t.`id_tipo_usuario`,");
			sql.append("t.`nome` tnome,");
			sql.append("t.`descricao`,");
			sql.append("t.`data_inclusao`");
			sql.append("from ages_e.tb_usuario u inner join ages_e.tb_tipo_usuario t ");
			sql.append("on t.id_tipo_usuario = u.id_tipo_usuario ");
			sql.append("where id_usuario = ?;");

			PreparedStatement statement = conexao.prepareStatement(sql.toString());
			statement.setInt(1, idUsuario);
			ResultSet resultset = statement.executeQuery();

			while (resultset.next()) {
				usuario.setIdUsuario(resultset.getInt("ID_USUARIO"));
				usuario.setMatricula(resultset.getString("MATRICULA"));
				usuario.setNome(resultset.getString("unome"));
				usuario.setEmail(resultset.getString("EMAIL"));
				usuario.setUsuario(resultset.getString("USUARIO"));
				usuario.setSenha(resultset.getString("SENHA"));
				usuario.setPerfilAcesso(PerfilAcesso.valueOf(resultset.getString("PERFIL_ACESSO")));
				usuario.setStatusUsuario(StatusUsuario.valueOf(resultset.getString("STATUS_USUARIO")));
				TipoUsuario tipoUsuario = new TipoUsuario();
				tipoUsuario.setIdTipoUsuario(resultset.getInt("ID_TIPO_USUARIO"));
				tipoUsuario.setNome(resultset.getString("tnome"));
				tipoUsuario.setDescricao(resultset.getString("DESCRICAO"));
				usuario.setTipoUsuario(tipoUsuario);
			}

		} catch (ClassNotFoundException | SQLException e) {
			throw new PersistenciaException(e);
		} finally {
			try {
				conexao.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		return usuario;

	}

	public int verificaUsuarioProjeto(Integer idUsuario) throws PersistenciaException, SQLException {
		int t = -1;
		Connection conexao = null;
		try {
			conexao = ConexaoUtil.getConexao();

			StringBuilder sql = new StringBuilder();
			sql.append("select id_usuario from ages_e.tb_projeto_usuario where id_usuario = ?;");

			PreparedStatement statement = conexao.prepareStatement(sql.toString());
			statement.setInt(1, idUsuario);
			ResultSet resultset = statement.executeQuery();
			// gambiarra
			try {
				t = resultset.getInt("ID_USUARIO");
			} catch (SQLException e) {
				return -1;
			}
			// fim gambiarra
		} catch (ClassNotFoundException | SQLException e) {
			throw new PersistenciaException(e);
		} finally {
			try {
				conexao.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		return t;
	}

	public TipoUsuario consultaTipoUsuario(String idTipoUsuario) throws PersistenciaException {
		Connection conexao = null;
		TipoUsuario tipoUsuario = new TipoUsuario();
		try {
			conexao = ConexaoUtil.getConexao();

			StringBuilder sql = new StringBuilder();
			sql.append("select * from ages_e.tb_tipo_usuario where id_tipo_usuario = ?;");

			PreparedStatement statement = conexao.prepareStatement(sql.toString());
			statement.setString(1, idTipoUsuario);
			ResultSet resultset = statement.executeQuery();
			while (resultset.next()) {
				tipoUsuario.setIdTipoUsuario(resultset.getInt("ID_TIPO_USUARIO"));
				tipoUsuario.setNome(resultset.getString("NOME"));
				tipoUsuario.setDescricao(resultset.getString("DESCRICAO"));
			}

		} catch (ClassNotFoundException | SQLException e) {
			throw new PersistenciaException(e);
		} finally {
			try {
				conexao.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return tipoUsuario;
	}

	public boolean editaUsuario(Usuario usuario) throws PersistenciaException {
		boolean okei = false;
		Connection conexao = null;
		try {
			conexao = ConexaoUtil.getConexao();
			StringBuilder sql = new StringBuilder();
			int id = usuario.getIdUsuario();

			sql.append("update ages_e.tb_usuario set senha = ?, perfil_acesso = ?," + "status_usuario = ?, id_tipo_usuario = ?, nome = ?, email = ?, matricula = ?" + "  where id_usuario = " + id + ";");

			PreparedStatement statement = conexao.prepareStatement(sql.toString());

			statement.setString(1, usuario.getSenha());
			statement.setString(2, usuario.getPerfilAcesso().name());
			statement.setString(3, usuario.getStatusUsuario().name());
			statement.setInt(4, usuario.getTipoUsuario().getIdTipoUsuario());
			statement.setString(5, usuario.getNome());
			statement.setString(6, usuario.getEmail());
			statement.setString(7, usuario.getMatricula());
			okei = statement.execute();
		} catch (ClassNotFoundException | SQLException e) {
			throw new PersistenciaException(e);
		} finally {
			try {
				conexao.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return okei;
	}

	/**
	 * <<<<<<< HEAD Lista os tipos de usu�rios ======= Lista os tipos de
	 * usu�rios >>>>>>> branch 'dev' of
	 * https://github.com/agespucrs/fluxoAges.git
	 * 
	 * @return
	 * @throws PersistenciaException
	 */
	public List<TipoUsuario> listaTipoUsuarios() throws PersistenciaException {
		Usuario usuario = new Usuario();

		Connection conexao = null;
		try {
			conexao = ConexaoUtil.getConexao();

			List<TipoUsuario> tipoUsuarios = new ArrayList<>();

			StringBuilder sql = new StringBuilder();
			// sql.append("SELECT * FROM AGES_E.TB_USUARIO WHERE ID_USUARIO = ?;");
			//
			sql.append("SELECT ");
			sql.append("`ID_TIPO_USUARIO`,");
			sql.append("`NOME` ,");
			sql.append("`DESCRICAO`,");
			sql.append("`DATA_INCLUSAO` ");
			sql.append("FROM tb_tipo_usuario; ");

			PreparedStatement statement = conexao.prepareStatement(sql.toString());
			ResultSet resultset = statement.executeQuery();

			while (resultset.next()) {
				TipoUsuario tipoUsuario = new TipoUsuario();

				tipoUsuario.setIdTipoUsuario(resultset.getInt("ID_TIPO_USUARIO"));
				tipoUsuario.setNome(resultset.getString("NOME"));
				tipoUsuario.setDescricao(resultset.getString("DESCRICAO"));
				usuario.setTipoUsuario(tipoUsuario);

				tipoUsuarios.add(tipoUsuario);

			}

			return tipoUsuarios;

		} catch (ClassNotFoundException | SQLException e) {
			throw new PersistenciaException(e);
		} finally {
			try {
				conexao.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * Busca nos banco os usu�rios respons�veis
	 * 
	 * @return
	 * @throws PersistenciaException
	 * @throws SQLException
	 */
	public List<Usuario> listarUsuariosReponsaveis() throws PersistenciaException, SQLException {
		Connection conexao = null;
		// tentativa de readaptação do listarUsuarios()
		try {
			conexao = ConexaoUtil.getConexao();
			listarUsuarios = new ArrayList<>();
			
			StringBuilder sql = new StringBuilder();
			sql.append("select ");
			sql.append("u.`id_usuario`,");
			sql.append("u.`usuario`,");
			sql.append("u.`senha`,");
			sql.append("u.`perfil_acesso`,");
			sql.append("u.`status_usuario`,");
			sql.append("u.`id_tipo_usuario`,");
			sql.append("u.`matricula`,");
			sql.append("u.`nome`,");
			sql.append("u.`email` ");

			sql.append("from ages_e.tb_usuario u inner join ages_e.tb_tipo_usuario t ");
			sql.append(" on t.id_tipo_usuario = u.id_tipo_usuario");
			sql.append(" where t.flag_responsavel = 1;");
			
					
			PreparedStatement statement = conexao.prepareStatement(sql.toString());
			ResultSet resultset = statement.executeQuery();
			while (resultset.next()) {
				Usuario dto = new Usuario();
				dto.setIdUsuario(resultset.getInt("ID_USUARIO"));
				dto.setMatricula(resultset.getString("MATRICULA"));
				dto.setNome(resultset.getString("NOME"));
				dto.setEmail(resultset.getString("EMAIL"));
				dto.setUsuario(resultset.getString("USUARIO"));
				dto.setSenha(resultset.getString("SENHA"));
				dto.setPerfilAcesso(PerfilAcesso.valueOf(resultset.getString("PERFIL_ACESSO")));
				dto.setStatusUsuario(StatusUsuario.valueOf(resultset.getString("STATUS_USUARIO")));

				listarUsuarios.add(dto);
			}

		} catch (ClassNotFoundException | SQLException e) {
			throw new PersistenciaException(e);
		} finally {
			conexao.close();
		}
		return listarUsuarios;
	}
	/**
	 * Lista os Usuarios da basee
	 * 
	 * @return
	 * @throws PersistenciaException
	 * @throws SQLException
	 */
	public List<Usuario> listarUsuariosAlunos() throws PersistenciaException, SQLException {
		Connection conexao = null;
		// tentativa de readaptação do listarUsuarios()
		try {
			conexao = ConexaoUtil.getConexao();
			listarUsuarios = new ArrayList<>();

			StringBuilder sql = new StringBuilder();
			sql.append("select ");
			sql.append("u.`id_usuario`,");
			sql.append("u.`usuario`,");
			sql.append("u.`senha`,");
			sql.append("u.`perfil_acesso`,");
			sql.append("u.`status_usuario`,");
			sql.append("u.`id_tipo_usuario`,");
			sql.append("u.`matricula`,");
			sql.append("u.`nome`,");
			sql.append("u.`email` ");

			sql.append("from ages_e.tb_usuario u inner join ages_e.tb_tipo_usuario t ");
			sql.append(" on t.id_tipo_usuario = u.id_tipo_usuario");
			sql.append(" where t.nome = 'aluno';");

			PreparedStatement statement = conexao.prepareStatement(sql.toString());
			ResultSet resultset = statement.executeQuery();
			while (resultset.next()) {
				Usuario dto = new Usuario();
				dto.setIdUsuario(resultset.getInt("ID_USUARIO"));
				dto.setMatricula(resultset.getString("MATRICULA"));
				dto.setNome(resultset.getString("NOME"));
				dto.setEmail(resultset.getString("EMAIL"));
				dto.setUsuario(resultset.getString("USUARIO"));
				dto.setSenha(resultset.getString("SENHA"));
				dto.setPerfilAcesso(PerfilAcesso.valueOf(resultset.getString("PERFIL_ACESSO")));
				dto.setStatusUsuario(StatusUsuario.valueOf(resultset.getString("STATUS_USUARIO")));

				listarUsuarios.add(dto);
			}

		} catch (ClassNotFoundException | SQLException e) {
			throw new PersistenciaException(e);
		} finally {
			conexao.close();
		}
		return listarUsuarios;
	}

	public Usuario buscaUsuarioPorSenha(Usuario usuarioDto) throws PersistenciaException {
		Usuario usuario = new Usuario();
		Connection conexao = null;
		try {
			conexao = ConexaoUtil.getConexao();

			StringBuilder sql = new StringBuilder();
			// sql.append("SELECT * FROM TB_USUARIO WHERE NOME = ?");
			sql.append(" SELECT ");
			sql.append(" ID_USUARIO,");
			sql.append(" USUARIO,");
			sql.append(" SENHA,");
			sql.append(" PERFIL_ACESSO,");
			sql.append(" STATUS_USUARIO,");
			sql.append(" ID_TIPO_USUARIO,");
			sql.append(" MATRICULA,");
			sql.append(" NOME,");
			sql.append(" EMAIL");
			sql.append(" FROM TB_USUARIO ");
			sql.append(" WHERE USUARIO = ? AND EMAIL = ? ;");
			PreparedStatement statement = conexao.prepareStatement(sql.toString());
			statement.setString(1, usuarioDto.getUsuario());
			statement.setString(2, usuarioDto.getEmail());

			ResultSet resultset = statement.executeQuery();

			while (resultset.next()) {

				usuario.setIdUsuario(resultset.getInt("ID_USUARIO"));
				usuario.setMatricula(resultset.getString("MATRICULA"));
				usuario.setNome(resultset.getString("NOME"));
				usuario.setEmail(resultset.getString("EMAIL"));
				usuario.setUsuario(resultset.getString("USUARIO"));
				usuario.setSenha(resultset.getString("SENHA"));
				usuario.setPerfilAcesso(PerfilAcesso.valueOf(resultset.getString("PERFIL_ACESSO")));
				usuario.setStatusUsuario(StatusUsuario.valueOf(resultset.getString("STATUS_USUARIO")));
					
			}
		} catch (ClassNotFoundException | SQLException e) {
			throw new PersistenciaException(e);
		} finally {
			try {
				conexao.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		return usuario;
	}
}
