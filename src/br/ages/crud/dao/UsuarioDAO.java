package br.ages.crud.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import br.ages.crud.exception.PersistenciaException;
import br.ages.crud.model.PerfilAcesso;
import br.ages.crud.model.TipoUsuario;
import br.ages.crud.model.Usuario;
import br.ages.crud.util.ConexaoUtil;

import com.mysql.jdbc.Statement;

/**
 * 
 * @author C�ssio Trindade
 *
 */
public class UsuarioDAO {

	private DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");

	private ArrayList<Usuario> listarUsuarios;

	public UsuarioDAO() {
		listarUsuarios = new ArrayList<>();
	}

	/**
	 * Autentica o usu�rio
	 * 
	 * @author cassio trindade
	 * @param usuarioDTO
	 * @return
	 * @throws PersistenciaException
	 */

	public boolean validarUsuario(Usuario usuarioDTO)
			throws PersistenciaException {
		try {
			Connection conexao = ConexaoUtil.getConexao();
			StringBuilder sql = new StringBuilder();
			sql.append("SELECT * FROM TB_USUARIO ");
			sql.append("WHERE USUARIO = ? AND SENHA = ?");

			PreparedStatement statement = conexao.prepareStatement(sql
					.toString());
			statement.setString(1, usuarioDTO.getUsuario());
			statement.setString(2, usuarioDTO.getSenha());

			ResultSet resultSet = statement.executeQuery();
			return resultSet.next();

		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
			throw new PersistenciaException(e);
		}

	}

	/**
	 * Lista os Usuarios da basee
	 * 
	 * @return
	 * @throws PersistenciaException
	 * @throws SQLException
	 */
	public List<Usuario> listarUsuarios() throws PersistenciaException,
			SQLException {
		Connection conexao = null;
   //tentativa de readaptação do listarUsuarios()
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

			sql.append("FROM AGES_E.TB_USUARIO u inner join AGES_E.tb_tipo_usuario t");
			sql.append("on t.id_tipo_usuario = u.id_tipo_usuario;");
			
			PreparedStatement statement = conexao.prepareStatement(sql
					.toString());
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

	public int cadastrarUsuario(Usuario usuario) throws PersistenciaException,
			SQLException, ParseException {
		//adicionar paranauês de tipo de usuário e tal
		Connection conexao = null;

		try {
			Integer idUsuario = null;

			conexao = ConexaoUtil.getConexao();

			StringBuilder sql = new StringBuilder();
			sql.append("INSERT INTO TB_USUARIO (USUARIO, SENHA, ADMINISTRADOR, MATRICULA, NOME, EMAIL, DATA_CADASTRO)");
			sql.append("VALUES (?, ?, ?, ?, ?, ?, ? )");

			// converte a data para data Juliana, data que o banco reconhece;
			java.util.Date utilDate = new java.util.Date();
			java.sql.Date dataCadastro = new java.sql.Date(utilDate.getTime());

			// Cadastra a pessoa e gera e busca id gerado
			PreparedStatement statement = conexao.prepareStatement(
					sql.toString(), Statement.RETURN_GENERATED_KEYS);
			statement.setString(1, usuario.getUsuario());
			statement.setString(2, usuario.getSenha());
			statement.setString(3, String.valueOf(usuario.getPerfilAcesso()));
			statement.setString(4, usuario.getMatricula());
			statement.setString(5, usuario.getNome());
			statement.setString(6, usuario.getEmail());
			statement.setDate(7, dataCadastro);

			statement.executeUpdate();

			ResultSet resultset = statement.getGeneratedKeys();
			if (resultset.first()) {
				idUsuario = resultset.getInt(1);

			}
			return idUsuario;

		} catch (ClassNotFoundException | SQLException e) {
			throw new PersistenciaException(e);
		} finally {
			conexao.close();
		}
	}

	/**
	 * M�todo de remo��o de um usu�rio a partir do seu id.
	 * 
	 * @param idPessoa
	 * @throws PersistenciaException
	 */
	public boolean removerUsuario(Integer idUsuario)
	//adicionar algum paranauê para remover da tabela tb_tipo_usuario também
			throws PersistenciaException {
		boolean removidoOK = false;
		Connection conexao = null;
		try {
			conexao = ConexaoUtil.getConexao();

			StringBuilder sql = new StringBuilder();
			sql.append("DELETE FROM TB_USUARIO WHERE ID_USUARIO= ?");

			PreparedStatement statement = conexao.prepareStatement(sql
					.toString());
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

	public Usuario buscaUsuarioNome(String nomeUsuario)
	//adicionar informações de tipo de usuario?
			throws PersistenciaException {

		Usuario usuario = new Usuario();
		Connection conexao = null;
		try {
			conexao = ConexaoUtil.getConexao();

			StringBuilder sql = new StringBuilder();
			sql.append("SELECT * FROM TB_USUARIO WHERE NOME = ?");

			PreparedStatement statement = conexao.prepareStatement(sql
					.toString());
			statement.setString(1, nomeUsuario);

			ResultSet resultset = statement.executeQuery();

			while (resultset.next()) {

				usuario.setIdUsuario(resultset.getInt("ID_USUARIO"));
				usuario.setMatricula(resultset.getString("MATRICULA"));
				usuario.setNome(resultset.getString("NOME"));
				usuario.setEmail(resultset.getString("EMAIL"));
				usuario.setUsuario(resultset.getString("USUARIO"));
				usuario.setSenha(resultset.getString("SENHA"));
				usuario.setPerfilAcesso(PerfilAcesso.valueOf(resultset.getString("ADMINISTRADOR")));
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

	public Usuario buscaUsuarioId(int idUsuario) throws PersistenciaException {
		//adicionar informações de tipo de usuario?
		Usuario usuario = new Usuario();

		Connection conexao = null;
		try {
			conexao = ConexaoUtil.getConexao();

			StringBuilder sql = new StringBuilder();
			sql.append("SELECT * FROM TB_USUARIO WHERE ID_USUARIO = ?");

			PreparedStatement statement = conexao.prepareStatement(sql
					.toString());
			statement.setInt(1, idUsuario);
			ResultSet resultset = statement.executeQuery();

			while (resultset.next()) {

				usuario.setIdUsuario(resultset.getInt("ID_USUARIO"));
				usuario.setMatricula(resultset.getString("MATRICULA"));
				usuario.setNome(resultset.getString("NOME"));
				usuario.setEmail(resultset.getString("EMAIL"));
				usuario.setUsuario(resultset.getString("USUARIO"));
				usuario.setSenha(resultset.getString("SENHA"));
				usuario.setPerfilAcesso(PerfilAcesso.valueOf(resultset.getString("ADMINISTRADOR")));
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



