package br.ages.crud.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;

import br.ages.crud.exception.PersistenciaException;
import br.ages.crud.model.Projeto;
import br.ages.crud.model.Stakeholder;
import br.ages.crud.model.StatusProjeto;
import br.ages.crud.model.Usuario;
import br.ages.crud.util.ConexaoUtil;

import com.mysql.jdbc.Statement;

/** 
 * @author Daniele Souza e Victor Diehl
 */

public class ProjetoDAO {

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
				dto.setIdProjeto(resultSet.getInt("ID_PROJETO"));
				dto.setNomeProjeto(resultSet.getString("NOME_PROJETO"));
				dto.setStatusProjeto(StatusProjeto.valueOf(resultSet.getString("STATUS_PROJETO")));
				
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
			sql.append("INSERT INTO TB_PROJETO (NOME_PROJETO, STATUS_PROJETO, WORKSPACE, DATA_INICIO, DATA_FIM, DATA_FIM_PREVISTO, DATA_INCLUSAO)");
			sql.append("VALUES (?, ?, ?, ?, ?, ?, ?)");
			
			java.sql.Date dataInicio = new java.sql.Date(projeto.getDataInicio().getTime());

			java.sql.Date dataFim = new java.sql.Date(projeto.getDataFim().getTime());
			
			java.sql.Date dataFimPrevisto = new java.sql.Date(projeto.getDataFimPrevisto().getTime());
			
			java.sql.Date dataInclusao = new java.sql.Date(projeto.getDataInclusao().getTime());

			PreparedStatement statement = conexao.prepareStatement(
					sql.toString(), Statement.RETURN_GENERATED_KEYS);
			statement.setString(1, projeto.getNomeProjeto());
			statement.setString(2, projeto.getStatusProjeto().toString());
			statement.setString(3, projeto.getWorkspace());
			statement.setDate(4, dataInicio);
			statement.setDate(5, dataFim);
			statement.setDate(6, dataFimPrevisto);
			statement.setDate(7, dataInclusao);

			statement.executeUpdate();

			ResultSet resultset = statement.getGeneratedKeys();
			if (resultset.first()) {
				idProjeto = resultset.getInt(1);
			}
			
			if(!inserirUsuariosProjeto(conexao, projeto)) return;
			if(!inserirStakeholdersProjeto(conexao, projeto)) return;
			
		} catch (ClassNotFoundException | SQLException e) {
			throw new PersistenciaException(e);
		} finally {
			conexao.close();
		}
	}
	
	private boolean inserirUsuariosProjeto(Connection conexao, Projeto projeto) throws SQLException {						
		
		boolean ok = false;
		
		ArrayList<Usuario> listaUsuarios = new ArrayList<>(projeto.getUsuarios());
		
		StringBuilder sql = new StringBuilder();
		sql.append("INSERT INTO TB_PROJETO_USUARIO (ID_PROJETO, ID_USUARIO)");
		sql.append("VALUES (?, ?)");
		
		PreparedStatement statement = conexao.prepareStatement(sql.toString());
		
		for (Usuario usuario: listaUsuarios) {
			
			statement.setInt(1, projeto.getIdProjeto());
			statement.setInt(2, usuario.getIdUsuario());
			
			ok = statement.execute();
			if(!ok) break;
		}
		
		return ok;
	}
	
	private boolean inserirStakeholdersProjeto(Connection conexao, Projeto projeto) throws SQLException {
		boolean ok = false;
		
		ArrayList<Stakeholder> listaStakeholders = new ArrayList<>(projeto.getStakeholders());
		
		StringBuilder sql = new StringBuilder();
		sql.append("INSERT INTO TB_PROJETO_STAKEHOLDER (ID_PROJETO, ID_STAKEHOLDER)");
		sql.append("VALUES (?, ?)");
		
		PreparedStatement statement = conexao.prepareStatement(sql.toString());
		
		for (Stakeholder stakeholder: listaStakeholders) {
			
			statement.setInt(1, projeto.getIdProjeto());
			statement.setInt(2, stakeholder.getIdStakeholder());
			
			ok = statement.execute();
			if(!ok) break;
		}
		
		return ok;
	}
	
	
	public void removerProjeto(Projeto projeto) throws PersistenciaException {
		Connection conexao = null;
		try	{
			conexao = ConexaoUtil.getConexao();
			
			StringBuilder sql = new StringBuilder();
			sql.append("DELETE FROM TB_PROJETO WHERE ID_PROJETO = ?");
			
			PreparedStatement statement = conexao.prepareStatement(sql.toString());
			statement.setInt(1, projeto.getIdProjeto());
			
			statement.execute();
			
			if(!removerUsuariosProjeto(conexao, projeto)) return;
			if(!removerStakeholdersProjeto(conexao, projeto)) return;
			
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
	
	private boolean removerUsuariosProjeto(Connection conexao, Projeto projeto) throws SQLException {
		boolean ok = false;
		
		StringBuilder sql = new StringBuilder();
		sql.append("DELETE FROM TB_PROJETO_USUARIO WHERE ID_PROJETO = ?");
		
		PreparedStatement statement = conexao.prepareStatement(sql.toString());
			statement.setInt(1, projeto.getIdProjeto());
			ok = statement.execute();
			
		return ok;
	}
	
	private boolean removerStakeholdersProjeto(Connection conexao, Projeto projeto) throws SQLException {
		boolean ok = false;
		
		StringBuilder sql = new StringBuilder();
		sql.append("DELETE FROM STAKEHOLDER WHERE ID_PROJETO = ?");
		
		PreparedStatement statement = conexao.prepareStatement(sql.toString());
			statement.setInt(1, projeto.getIdProjeto());
			ok = statement.execute();
			
		return ok;
	}
}