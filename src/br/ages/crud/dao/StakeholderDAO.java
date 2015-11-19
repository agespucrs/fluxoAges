package br.ages.crud.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.text.ParseException;

import br.ages.crud.exception.PersistenciaException;
import br.ages.crud.model.PerfilAcesso;
import br.ages.crud.model.Stakeholder;
import br.ages.crud.model.StatusUsuario;
import br.ages.crud.model.TipoUsuario;
import br.ages.crud.model.Usuario;
import br.ages.crud.util.ConexaoUtil;
import br.ages.crud.util.MensagemContantes;

import java.util.List;
import java.util.ArrayList;

import com.mysql.jdbc.Statement;

public class StakeholderDAO {

	private DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
	
	private ArrayList<Stakeholder> listarStakeholders;
	
	public StakeholderDAO() {
		listarStakeholders = new ArrayList<>();
	}
	
	public Stakeholder validarStakeholder(Stakeholder stakeholderDTO) throws PersistenciaException{
		Stakeholder stakeholder = new Stakeholder();
		try{
			Connection conexao = ConexaoUtil.getConexao();
			StringBuilder sql = new StringBuilder();
			sql.append("SELECT * FROM TB_STAKEHOLDERS");
			sql.append("WHERE NOME_STAKEHOLDER = ?");
			
			PreparedStatement statement = conexao.prepareStatement(sql.toString());
			statement.setString(1, stakeholderDTO.getNomeStakeholder());
			
			ResultSet resultset = statement.executeQuery();
			if(resultset.next()) {
				stakeholder.setIdStakeholder(resultset.getInt("ID_STAKEHOLDER"));
				stakeholder.setNomeStakeholder(resultset.getString("NOME_STAKEHOLDER"));
			} else stakeholder = null;
		} catch(ClassNotFoundException | SQLException e) {
				e.printStackTrace();
				throw new PersistenciaException(e);
		}	
		return stakeholder;
	}
	
	public List<Stakeholder> listarStakeholders() throws PersistenciaException, SQLException {
		Connection conexao = null;
		
		try {
			conexao = ConexaoUtil.getConexao();
			
			StringBuilder sql = new StringBuilder();
			sql.append("SELECT ");
			sql.append("ID_STAKEHOLDER,");
			sql.append("NOME_STAKEHOLDER,");
			sql.append("DATA_INCLUSAO ");
			
			sql.append("FROM AGES_E.TB_STAKEHOLDERS");
			
			PreparedStatement statement = conexao.prepareStatement(sql.toString());
			ResultSet resultset = statement.executeQuery();
			while(resultset.next()) {
				Stakeholder dto = new Stakeholder();
				dto.setIdStakeholder(resultset.getInt("ID_STAKEHOLDER"));
				dto.setNomeStakeholder(resultset.getString("NOME_STAKEHOLDER"));
				
				listarStakeholders.add(dto);
			}
		} catch (ClassNotFoundException | SQLException e ) {
			throw new PersistenciaException(e);
		} finally {
			conexao.close();
		}
		return listarStakeholders;
	}
	
	public int cadastrarStakeholder(Stakeholder stakeholder) throws PersistenciaException, SQLException, ParseException {
		
		Connection conexao = null;
		
		try {
			Integer idStakeholder = null;
			
			conexao = ConexaoUtil.getConexao();
			
			StringBuilder sql = new StringBuilder();
			sql.append("INSERT INTO TB_STAKEHOLDERS (NOME_STAKEHOLDER,DATA_INCLUSAO)");
			sql.append("VALUES (?,?)");
			
			java.util.Date utilDate = new java.util.Date();
			java.sql.Date dataCadastro = new java.sql.Date(utilDate.getTime());
			
			PreparedStatement statement = conexao.prepareStatement(sql.toString(), Statement.RETURN_GENERATED_KEYS);
			statement.setString(1, stakeholder.getNomeStakeholder());
			statement.setDate(2, dataCadastro);
			
			statement.executeUpdate();
			
			ResultSet resultset = statement.getGeneratedKeys();
			if(resultset.first()) {
				idStakeholder = resultset.getInt(1);
			}
			return idStakeholder;
		} catch (ClassNotFoundException | SQLException e) {
			throw new PersistenciaException(MensagemContantes.MSG_ERR_STAKEHOLDER_JA_EXISTENTE.replace("?", stakeholder.getNomeStakeholder()));
		}	finally {
			conexao.close();
		}
	}
	
	public Stakeholder buscaStakeholderId(int idStakeholder) throws PersistenciaException, SQLException {
		
		Stakeholder stakeholder = new Stakeholder();

		Connection conexao = null;
		try {
			conexao = ConexaoUtil.getConexao();

			StringBuilder sql = new StringBuilder();
			sql.append("SELECT * FROM AGES_E.TB_STAKEHOLDERS WHERE ID_STAKEHOLDER = ?;");
			
			sql.append("SELECT ");			
			sql.append("ID_STAKEHOLDER,");
			sql.append("NOME_STAKEHOLDER,");			
			sql.append("DATA_INCLUSAO");			
			sql.append("WHERE ID_STAKEHOLDER = ?;");

			PreparedStatement statement = conexao.prepareStatement(sql.toString());
			statement.setInt(1, idStakeholder);
			ResultSet resultset = statement.executeQuery();

			while (resultset.next()) {
				stakeholder.setIdStakeholder(resultset.getInt("ID_STAKEHOLDER"));
				stakeholder.setNomeStakeholder(resultset.getString("NOME_STAKEHOLDER"));			
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

		return stakeholder;

	}
	
	public int verificaStakeholderProjeto(Integer idStakeholder) throws PersistenciaException, SQLException {
		int t = -1;
		Connection conexao = null;
		try {
			conexao = ConexaoUtil.getConexao();

			StringBuilder sql = new StringBuilder();
			sql.append("select id_stakeholder from ages_e.tb_projeto_stakeholder where id_stakeholder = ?;");

			PreparedStatement statement = conexao.prepareStatement(sql.toString());
			statement.setInt(1, idStakeholder);
			ResultSet resultset = statement.executeQuery();
			// gambiarra
			try {
				t = resultset.getInt("ID_STAKEHOLDER");
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
	
	public Stakeholder buscarStakeholderNome(String nomeStakeholder) throws PersistenciaException {
		Stakeholder stakeholder = new Stakeholder();
		Connection conexao = null;
		try{
			conexao = ConexaoUtil.getConexao();
			
			StringBuilder sql = new StringBuilder();
			sql.append("SELECT * FROM TB_STAKEHOLDERS WHERE NOME_STAKEHOLDER = ?");
			sql.append("ID_STAKEHOLDER,");
			sql.append("NOME_STAKEHOLDER;");
			PreparedStatement statement = conexao.prepareStatement(sql.toString());
		    statement.setString(1, nomeStakeholder);
			
			ResultSet resultset = statement.executeQuery();
			while (resultset.next()){
				stakeholder.setIdStakeholder(resultset.getInt("ID_STAKEHOLDER"));
				stakeholder.setNomeStakeholder(resultset.getString("NOME_STAKEHOLDER"));
				
			}
		} catch (ClassNotFoundException | SQLException e) {
			throw new PersistenciaException(e);
		} finally {
			try{
				conexao.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return stakeholder;
	}	
	public boolean editaStakeholder(Stakeholder stakeholder) throws PersistenciaException {
		boolean ok = false;
		Connection conexao = null;
		try {
			conexao = ConexaoUtil.getConexao();
			StringBuilder sql = new StringBuilder();
			int id = stakeholder.getIdStakeholder();
			
			sql.append("UPDATE AGES.E.TB_STAKEHOLDERS SET NOME_STAKEHOLDER = ?" + " WHERE ID_STAKEHOLDER = " + id + ";");
			
			PreparedStatement statement = conexao.prepareStatement(sql.toString());
			
			statement.setString(1, stakeholder.getNomeStakeholder());
			ok = statement.execute();
		} catch (ClassNotFoundException | SQLException e) {
			throw new PersistenciaException(e);				
			} finally {
				try{
					conexao.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			return ok;
		}
	
	public boolean removerStakeholder(Integer idStakeholder) throws PersistenciaException {
		boolean removidoOK = false;
		Connection conexao = null;
		try {
			conexao = ConexaoUtil.getConexao();

			StringBuilder sql = new StringBuilder();
			
			sql.append("DELETE FROM TB_STAKEHOLDERS WHERE ID_STAKEHOLDER= ? ");
			PreparedStatement statement = conexao.prepareStatement(sql.toString());
			statement.setInt(1, idStakeholder);

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
	}



