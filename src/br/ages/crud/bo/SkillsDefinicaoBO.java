package br.ages.crud.bo;

import java.sql.SQLException;
import java.util.List;

import br.ages.crud.dao.SkillsDefinicaoDAO;
import br.ages.crud.exception.NegocioException;
import br.ages.crud.exception.PersistenciaException;
import br.ages.crud.model.SkillsDefinicao;

public class SkillsDefinicaoBO {
	SkillsDefinicaoDAO skillsDefinicaoDAO = new SkillsDefinicaoDAO();
	
	public List<SkillsDefinicao> listaskills() throws NegocioException {
		List<SkillsDefinicao> skillsHards = null;
		
		try {
			skillsHards = skillsDefinicaoDAO.listarkillsDefinicoes("");
		} catch(PersistenciaException | SQLException e) {
			e.printStackTrace();
			throw new NegocioException(e);
		}
		
		return skillsHards;

	}
	public List<SkillsDefinicao> listaskillsHard() throws NegocioException {
		List<SkillsDefinicao> skillsHards = null;
		
		try {
			skillsHards = skillsDefinicaoDAO.listarkillsDefinicoes("HARD");
		} catch(PersistenciaException | SQLException e) {
			e.printStackTrace();
			throw new NegocioException(e);
		}
		
		return skillsHards;
		
	}

	public List<SkillsDefinicao> listaskillsSoft() throws NegocioException {
	List<SkillsDefinicao> skillsSofts = null;
		
		try {
			skillsSofts = skillsDefinicaoDAO.listarkillsDefinicoes("SOFT");
		} catch(PersistenciaException | SQLException e) {
			e.printStackTrace();
			throw new NegocioException(e);
		}
		
		return skillsSofts;
	}

}
