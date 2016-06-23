package br.ages.crud.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;

import com.mysql.jdbc.Statement;

import br.ages.crud.exception.NegocioException;
import br.ages.crud.exception.PersistenciaException;
import br.ages.crud.model.Ponto;
import br.ages.crud.model.ResumoPonto;
import br.ages.crud.model.StatusPonto;
import br.ages.crud.model.Usuario;
import br.ages.crud.util.ConexaoUtil;
import br.ages.crud.util.MensagemContantes;

public class PontoDAO {

	public Integer cadastrarPonto(Ponto ponto) throws NegocioException, SQLException, PersistenciaException {
		Connection conexao = null;
		try {
			Integer idPonto = null;

			conexao = ConexaoUtil.getConexao();

			StringBuilder sql = new StringBuilder();
			sql.append("insert into tb_ponto (data_entrada, hora_entrada, data_saida, hora_saida, id_usuario_aluno, id_usuario_responsavel, status_ponto)");
			sql.append("values (?, ?, ?, ?, ?, ?, ?);");

			// Cadastra a pessoa e gera e busca id gerado
			PreparedStatement statement = conexao.prepareStatement(sql.toString(), Statement.RETURN_GENERATED_KEYS);

			// converte a data para data Juliana, data que o banco reconhece;
			java.sql.Timestamp dataEntrada = new java.sql.Timestamp(ponto.getDataEntrada().getTime());
			statement.setTimestamp(1, dataEntrada);

			java.sql.Time horaEntrada = new java.sql.Time(ponto.getDataEntrada().getTime());
			statement.setTime(2, horaEntrada);

			java.sql.Timestamp dataSaida = ponto.getDataSaida() == null ? null : new java.sql.Timestamp(ponto.getDataSaida().getTime());
			statement.setTimestamp(3, dataSaida);

			java.sql.Time horaSaida = ponto.getDataSaida() == null ? null : new java.sql.Time(ponto.getDataSaida().getTime());
			statement.setTime(4, horaSaida);

			statement.setInt(5, ponto.getAluno().getIdUsuario());
			statement.setInt(6, ponto.getResponsavel().getIdUsuario());
			statement.setString(7, String.valueOf(ponto.getStatus()));

			statement.executeUpdate();

			ResultSet resultset = statement.getGeneratedKeys();
			if (resultset.first()) {
				idPonto = resultset.getInt(1);

			}

			System.out.println(ponto);

			return idPonto;

		} catch (ClassNotFoundException | SQLException e) {

			e.printStackTrace();
			throw new PersistenciaException(MensagemContantes.MSG_ERR_PONTO_JA_EXISTENTE.replace("?", ponto.getAluno().getNome()));

		} finally {
			conexao.close();
		}

	}

	public ArrayList<ResumoPonto> listaPontoAlunos(int idUsuario) throws SQLException {
		ArrayList<ResumoPonto> listaPontos = new ArrayList<>();
		Connection conexao = null;
		try {
			conexao = ConexaoUtil.getConexao();

			StringBuilder sql = new StringBuilder();
			sql.append("select p.id_ponto, u.nome, p.data_entrada, timediff(p.hora_saida,p.hora_entrada) horas ");
			sql.append("FROM tb_ponto p, tb_usuario u ");
			sql.append("where p.id_usuario_aluno = u.id_usuario ");
			sql.append("and p.status_ponto ='" + StatusPonto.VALIDO + "'");

			PreparedStatement statement;
			if (idUsuario == 0) {
				// sql.append(" and p.id_usuario_aluno = ?; ");
				statement = conexao.prepareStatement(sql.toString());
				// statement.setInt(1, -1);
			} else {
				sql.append(" and p.id_usuario_aluno = ?; ");
				statement = conexao.prepareStatement(sql.toString());
				statement.setInt(1, idUsuario);
			}

			ResultSet resultSet = statement.executeQuery();

			while (resultSet.next()) {
				ResumoPonto ponto = new ResumoPonto();
				ponto.setIdPonto(resultSet.getInt("ID_PONTO"));
				ponto.setNomeAluno(resultSet.getString("NOME"));
				ponto.setDataEtrada(resultSet.getDate("DATA_ENTRADA"));
				ponto.setHoraTotalDia(resultSet.getTime("HORAS"));
				// " + horaTotal);

				listaPontos.add(ponto);
			}
		} catch (ClassNotFoundException | SQLException e) {

			e.printStackTrace();

		} finally {
			conexao.close();
		}

		return listaPontos;
	}

	public ArrayList<ResumoPonto> listaPontoInvalidoAlunos() throws SQLException {
		ArrayList<ResumoPonto> listaPontos = new ArrayList<>();
		Connection conexao = null;
		try {
			conexao = ConexaoUtil.getConexao();

			StringBuilder sql = new StringBuilder();
			sql.append("select p.id_ponto, u.nome, p.data_entrada, timediff(p.hora_saida,p.hora_entrada) horas ");
			sql.append("FROM tb_ponto p, tb_usuario u ");
			sql.append("where p.id_usuario_aluno = u.id_usuario ");
			sql.append("and p.status_ponto ='" + StatusPonto.INVALIDO + "'");

			PreparedStatement statement;

			statement = conexao.prepareStatement(sql.toString());

			ResultSet resultSet = statement.executeQuery();

			while (resultSet.next()) {
				ResumoPonto ponto = new ResumoPonto();
				ponto.setIdPonto(resultSet.getInt("ID_PONTO"));
				ponto.setNomeAluno(resultSet.getString("NOME"));
				ponto.setDataEtrada(resultSet.getDate("DATA_ENTRADA"));
				ponto.setHoraTotalDia(resultSet.getTime("HORAS"));

				listaPontos.add(ponto);
			}
		} catch (ClassNotFoundException | SQLException e) {

			e.printStackTrace();

		} finally {
			conexao.close();
		}

		return listaPontos;
	}

	public static void main(String[] args) throws SQLException {
		PontoDAO p = new PontoDAO();
		System.out.println(p.listaPontoAlunos(1));
	}

	public Ponto buscaPontoId(int idPonto) throws PersistenciaException, SQLException {

		Ponto ponto = new Ponto();

		UsuarioDAO usuarioDAO = new UsuarioDAO();

		try (Connection conexao = ConexaoUtil.getConexao()) {

			StringBuilder sql = new StringBuilder();
			sql.append("SELECT ");
			sql.append("ID_PONTO, ");
			sql.append("DATA_ENTRADA, ");
			sql.append("DATA_SAIDA, ");
			sql.append("ID_USUARIO_ALUNO, ");
			sql.append("ID_USUARIO_RESPONSAVEL, ");
			sql.append("STATUS_PONTO ");
			sql.append("FROM TB_PONTO ");
			sql.append("WHERE ID_PONTO = ?");
			PreparedStatement statement = conexao.prepareStatement(sql.toString());
			statement.setInt(1, idPonto);
			ResultSet resultset = statement.executeQuery();

			while (resultset.next()) {
				ponto.setIdPonto(resultset.getInt("ID_PONTO"));
				ponto.setDataEntrada(resultset.getTimestamp("DATA_ENTRADA"));
				ponto.setDataSaida(resultset.getTimestamp("DATA_SAIDA"));

				Usuario usuarioAluno = usuarioDAO.buscaUsuarioId(resultset.getInt("ID_USUARIO_ALUNO"));
				ponto.setAluno(usuarioAluno);
				
				Usuario usuarioResponsavel = usuarioDAO.buscaUsuarioId(resultset.getInt("ID_USUARIO_RESPONSAVEL"));
				ponto.setResponsavel(usuarioResponsavel);

				String status = resultset.getString("STATUS_PONTO");
				StatusPonto statusPonto = StatusPonto.valueOf(status);
				ponto.setStatus(statusPonto);
			}

		} catch (ClassNotFoundException | SQLException e) {
			throw new PersistenciaException(e);
		}

		return ponto;

	}

	public boolean editaPonto(Ponto ponto) throws NegocioException, SQLException, PersistenciaException {
		boolean ok = false;
		Connection conexao = null;
		try {
			int idPonto = ponto.getIdPonto();
			conexao = ConexaoUtil.getConexao();
			StringBuilder sql = new StringBuilder();
			sql.append("UPDATE TB_PONTO SET ");
			sql.append("DATA_ENTRADA = ?, ");
			sql.append("HORA_ENTRADA = ?, ");
			sql.append("DATA_SAIDA = ?, ");
			sql.append("HORA_SAIDA = ?, ");
			sql.append("ID_USUARIO_ALUNO = ?, ");
			sql.append("ID_USUARIO_RESPONSAVEL = ?, ");
			sql.append("STATUS_PONTO = ? ");
			sql.append("WHERE ID_PONTO = ?;");

			PreparedStatement statement = conexao.prepareStatement(sql.toString(), Statement.RETURN_GENERATED_KEYS);

			java.sql.Timestamp dataEntrada = new java.sql.Timestamp(ponto.getDataEntrada().getTime());
			statement.setTimestamp(1, dataEntrada);

			java.sql.Time horaEntrada = new java.sql.Time(ponto.getDataEntrada().getTime());
			statement.setTime(2, horaEntrada);

			java.sql.Timestamp dataSaida = ponto.getDataSaida() == null ? null : new java.sql.Timestamp(ponto.getDataSaida().getTime());
			statement.setTimestamp(3, dataSaida);

			java.sql.Time horaSaida = ponto.getDataSaida() == null ? null : new java.sql.Time(ponto.getDataSaida().getTime());
			statement.setTime(4, horaSaida);

			statement.setInt(5, ponto.getAluno().getIdUsuario());
			statement.setInt(6, ponto.getResponsavel().getIdUsuario());
			statement.setString(7, String.valueOf(ponto.getStatus()));
			statement.setInt(8, idPonto);
			statement.executeUpdate();

			/*ResultSet resultset = statement.getGeneratedKeys();
			if (resultset.first()) {
				idPonto = resultset.getInt(1);

			}*/

			System.out.println(ponto);
			ok = true;
			return ok;

		} catch (ClassNotFoundException | SQLException e) {

			e.printStackTrace();
			throw new PersistenciaException(e);

		} finally {
			conexao.close();
		}

	}

	public ArrayList<Ponto> listaAlunos() throws SQLException {
		UsuarioDAO alunoDAO = new UsuarioDAO();
		UsuarioDAO responsavelDAO = new UsuarioDAO();
		ArrayList<Ponto> listaAlunos = new ArrayList<>();
		Connection conexao = null;
		try {
			conexao = ConexaoUtil.getConexao();

			StringBuilder sql = new StringBuilder();
			sql.append("SELECT P.ID_PONTO, ID_USUARIO_ALUNO, ID_USUARIO_RESPONSAVEL, ");
			sql.append("P.DATA_ENTRADA, P.HORA_ENTRADA, P.DATA_SAIDA, P.HORA_SAIDA, STATUS_PONTO ");
			sql.append("FROM TB_PONTO P ");

			PreparedStatement statement;

			statement = conexao.prepareStatement(sql.toString());

			ResultSet resultSet = statement.executeQuery();

			while (resultSet.next()) {
				Ponto ponto = new Ponto();
				ponto.setIdPonto(resultSet.getInt("ID_PONTO"));
				Usuario aluno = alunoDAO.buscaUsuarioId(resultSet.getInt("ID_USUARIO_ALUNO"));
				ponto.setAluno(aluno);
				Usuario responsavel = responsavelDAO.buscaUsuarioId(resultSet.getInt("ID_USUARIO_RESPONSAVEL"));
				ponto.setResponsavel(responsavel);
				ponto.setDataEntrada(resultSet.getTimestamp("DATA_ENTRADA"));
				ponto.setDataSaida(resultSet.getTimestamp("DATA_SAIDA"));
				ponto.setStatus(StatusPonto.valueOf(resultSet.getString("STATUS_PONTO")));
				listaAlunos.add(ponto);
			}
		} catch (ClassNotFoundException | SQLException | PersistenciaException e) {
			e.printStackTrace();
		} finally {
			conexao.close();
		}

		return listaAlunos;
	}
}
