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

	private ArrayList<Projeto> listarProjetos;
	private DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");

	public ProjetoDAO() {
		listarProjetos = new ArrayList<>();
	}

	public ArrayList<Projeto> listarProjetos() throws PersistenciaException, SQLException {
		Connection conexao = null;
		 ArrayList<Projeto> listaProjetos = new ArrayList<Projeto>();
		try {
			
		
			conexao = ConexaoUtil.getConexao();
			
			StringBuilder sql = new StringBuilder();
			sql.append("SELECT * FROM TB_PROJETO");
			
			PreparedStatement statement = conexao.prepareStatement(sql.toString());
			ResultSet resultset = statement.executeQuery();
			
			while (resultset.next()) {
				Projeto dto = new Projeto();
				dto.setNomeProjeto(resultset.getString("NOME_PROJETO"));
				dto.setWorkspace(resultset.getString("WORKSPACE"));
				dto.setStakeholders((ArrayList) resultset.getArray("STAKEHOLDERS"));
				
				listaProjetos.add(dto);
			}
		}  catch (Exception e) {
			e.printStackTrace();
		}
		
		return listarProjetos;
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

			// Conversão das datas para sql (data inicio, data fim , data
			// inclusão);
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
}
