package br.ages.crud.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.mysql.jdbc.Statement;

import br.ages.crud.exception.NegocioException;
import br.ages.crud.exception.PersistenciaException;
import br.ages.crud.model.Ponto;
import br.ages.crud.model.ResumoPonto;
import br.ages.crud.model.Stakeholder;
import br.ages.crud.model.StatusPonto;
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
			java.sql.Date dataEntrada = new java.sql.Date(ponto.getDataEntrada().getTime());
			statement.setDate(1, dataEntrada);

			java.sql.Time horaEntrada = new java.sql.Time(ponto.getDataEntrada().getTime());
			statement.setTime(2, horaEntrada);

			java.sql.Date dataSaida = ponto.getDataSaida() == null ? null: new java.sql.Date(ponto.getDataSaida().getTime());
			statement.setDate(3, dataSaida);

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
			sql.append("and p.status_ponto ='"+StatusPonto.VALIDO+"'");
			
			PreparedStatement statement;
			if (idUsuario == 0) {
			//	sql.append(" and p.id_usuario_aluno = ?; ");
				statement = conexao.prepareStatement(sql.toString());
			//	statement.setInt(1, -1);
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
			sql.append("and p.status_ponto ='"+StatusPonto.INVALIDO+"'");
			
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
		
		Ponto ponto= new Ponto();

		try(Connection conexao = ConexaoUtil.getConexao()){

			StringBuilder sql = new StringBuilder();
			/*sql.append("SELECT ");
			sql.append(" ID_STAKEHOLDER,");
			sql.append(" NOME_STAKEHOLDER,");			
			sql.append(" DATA_INCLUSAO");			
			sql.append(" FROM tb_stakeholders ");
			sql.append(" WHERE ID_STAKEHOLDER = ?;");*/

			PreparedStatement statement = conexao.prepareStatement(sql.toString());
			statement.setInt(1, idPonto);
			ResultSet resultset = statement.executeQuery();

			while (resultset.next()) {
				/*ponto.setIdStakeholder(resultset.getInt("ID_STAKEHOLDER"));
				ponto.setNomeStakeholder(resultset.getString("NOME_STAKEHOLDER"));*/		
			}
			
		} catch (ClassNotFoundException | SQLException e) {
			throw new PersistenciaException(e);
		}

		return ponto;

	}
}
