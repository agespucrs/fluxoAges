package br.ages.crud.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.mysql.jdbc.Statement;

import br.ages.crud.exception.PersistenciaException;
import br.ages.crud.model.SkillAluno;
import br.ages.crud.util.ConexaoUtil;
import br.ages.crud.util.MensagemContantes;

public class SkillAlunoDAO {

	public boolean cadastraAvaliacao(SkillAluno skillAluno) throws PersistenciaException, SQLException {
		Connection conexao = null;
		Integer idSkill = null;
		try {
			conexao = ConexaoUtil.getConexao();	
			StringBuilder sql = new StringBuilder();
			sql.append("INSERT INTO  TB_SKILLS");
			sql.append("(ID_DEFINICAO,");
			sql.append("VALOR,");
			sql.append("DATA_VALOR,");
			sql.append("OBSERVACAO,");
			sql.append("ID_USUARIO_ALUNO,");
			sql.append("ID_USUARIO_AVALIADOR)");
			sql.append(" VALUES(?,?,?,?,?,?) ");
			
			
			PreparedStatement statement = conexao.prepareStatement(sql.toString(), Statement.RETURN_GENERATED_KEYS);
			statement.setInt(1, skillAluno.getIdDefinicao());
			statement.setInt(2, skillAluno.getValor());
			java.sql.Date dataValor = new java.sql.Date(skillAluno.getDtValor().getTime());
			statement.setDate(3, dataValor);
			statement.setString(4, skillAluno.getObservacao());
			statement.setInt(5, skillAluno.getAluno().getIdUsuario());
			statement.setInt(6, skillAluno.getAvaliador().getIdUsuario());
			
			statement.execute();
			
			ResultSet resultset = statement.getGeneratedKeys();
			if(resultset.first()) {
				idSkill = resultset.getInt(1);
			}
			
		} catch (ClassNotFoundException | SQLException e) {
			throw new PersistenciaException(MensagemContantes.MSG_ERR_AVALIACAO.replace("?", skillAluno.getAluno().getNome()));
		}	finally {
			conexao.close();
		}
		return (idSkill>0);
	}

}
