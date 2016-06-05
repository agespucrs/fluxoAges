package br.ages.crud.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.ages.crud.exception.PersistenciaException;
import br.ages.crud.model.SkillsDefinicao;
import br.ages.crud.model.TipoSkills;
import br.ages.crud.util.ConexaoUtil;

public class SkillsDefinicaoDAO {

	public List<SkillsDefinicao> listarkillsDefinicoes(String tipo) throws PersistenciaException, SQLException {
		Connection conexao = null;
		List<SkillsDefinicao> skillsDefinicoes = new ArrayList<SkillsDefinicao>();
		
		try {
			conexao = ConexaoUtil.getConexao();
			
			StringBuilder sql = new StringBuilder();
			sql.append("SELECT ");
			sql.append("ID_SKILLS_DEFINICAO,");
			sql.append("TIPO,");
			sql.append("NOME, ");
			sql.append("DESCRICAO ");
			sql.append("FROM TB_SKILLS_DEFINICAO ");
				if(!tipo.equals(""))	sql.append("WHERE TIPO = ?");
			
			PreparedStatement statement = conexao.prepareStatement(sql.toString());
				if(!tipo.equals("")) statement.setString(1, tipo);
			
				ResultSet resultset = statement.executeQuery();
			while(resultset.next()) {
				SkillsDefinicao skillsDefinicao  = new SkillsDefinicao();
			
				skillsDefinicao.setIdSkillsDefinicao(resultset.getInt("ID_SKILLS_DEFINICAO"));
				skillsDefinicao.setTipoSkills(TipoSkills.valueOf(resultset.getString("TIPO")));
				skillsDefinicao.setNome(resultset.getString("NOME"));
				skillsDefinicao.setDescricao(resultset.getString("DESCRICAO"));
				
				skillsDefinicoes.add(skillsDefinicao);
			}
		} catch (ClassNotFoundException | SQLException e ) {
			throw new PersistenciaException(e);
		} finally {
			conexao.close();
		}
		return skillsDefinicoes;
	}

}
