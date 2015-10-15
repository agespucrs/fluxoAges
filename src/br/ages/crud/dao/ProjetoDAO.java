package br.ages.crud.dao;

import java.sql.Array;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import br.ages.crud.exception.PersistenciaException;
import br.ages.crud.model.Projeto;
import br.ages.crud.util.ConexaoUtil;

import com.mysql.jdbc.Statement;

public class ProjetoDAO {

	private DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");

	public ProjetoDAO() {
		
	}

	public ArrayList<Projeto> listarProjetos() throws PersistenciaException, SQLException {
		Connection conexao = null;
		ArrayList<Projeto> listaProjetos = new ArrayList<Projeto>();
		
		try {
			conexao = ConexaoUtil.getConexao();
		
			StringBuilder sql = new StringBuilder();
			sql.append("SELECT * FROM TB_PROJETO");
			
			PreparedStatement statement = conexao.prepareStatement(sql.toString());
			ResultSet resultSet = statement.executeQuery();
			
			while (resultSet.next()) {
				Projeto dto = new Projeto();
				dto.setNomeProjeto(resultSet.getString("NOME_PROJETO"));
				dto.setWorkspace(resultSet.getString("WORKSPACE"));
				dto.setStakeholders((ArrayList) resultSet.getArray("STAKEHOLDERS"));
				
				listaProjetos.add(dto);
			}
		}  catch (Exception e) {
			e.printStackTrace();
		}
		
		return listaProjetos;
	}
	
	public void cadastrarProjeto(Projeto projeto) throws PersistenciaException,
			SQLException, ParseException {
		Connection conexao = null;
		try {
			Integer idProjeto = null;

			conexao = ConexaoUtil.getConexao();

			StringBuilder sql = new StringBuilder();
			sql.append("INSERT INTO TB_PROJETO (NOME_PROJETO, WORKSPACE, STAKEHOLDERS, STATUS, DATA_INCLUSAO, DATA_INICIO, DATA_FIM, EQUIPE)");
			sql.append("VALUES (?, ?, ?, ?, ?, ?, ?, ?)");

			// Convers�o das datas para sql (data inicio, data fim , data
			// inclus�o);
			java.util.Date utilDate1 = new java.util.Date();
			java.sql.Date dataInclusao = new java.sql.Date(utilDate1.getTime());

			java.util.Date utilDate2 = new java.util.Date();
			java.sql.Date dataInicio = new java.sql.Date(utilDate2.getTime());

			java.util.Date utilDate3 = new java.util.Date();
			java.sql.Date dataFim = new java.sql.Date(utilDate3.getTime());

			// Cadastra o projeto/ e gera Id;
			PreparedStatement statement = conexao.prepareStatement(
					sql.toString(), Statement.RETURN_GENERATED_KEYS);
			statement.setString(1, projeto.getNomeProjeto());
			statement.setString(2, projeto.getWorkspace());
			statement.setArray(3, (Array) projeto.getStakeholders());
			statement.setInt(4, 1); // ?????? colocar o id do projeto
			statement.setDate(5, dataInclusao);
			statement.setDate(6, dataInicio);
			statement.setDate(7, dataFim);

			statement.executeUpdate();

			ResultSet resultset = statement.getGeneratedKeys();
			if (resultset.first()) {
				idProjeto = resultset.getInt(1);
			}
		} catch (ClassNotFoundException | SQLException e) {
			throw new PersistenciaException(e);
		} finally {
			conexao.close();
		}
	}
	
	public void removeProjeto(Integer idProjeto) throws PersistenciaException {
		Connection conexao = null;
		try	{
			conexao =ConexaoUtil.getConexao();
			
			StringBuilder sql = new StringBuilder();
			sql.append("DELETE FROM TB_PROJETO WHERE ID_PROJETO= ?");
			
			PreparedStatement statement = conexao.prepareStatement(sql.toString());
			statement.setInt(1, idProjeto);
			
			statement.execute();			
			
		} catch (ClassNotFoundException | SQLException e) {
			throw new PersistenciaException(e);
		} finally {
			try {
				conexao.close();				
			}	catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
}
