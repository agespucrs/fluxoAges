package br.ages.crud.bo;

import java.sql.SQLException;
import java.text.ParseException;
import java.util.List;

import br.ages.crud.dao.ProjetoDAO;
import br.ages.crud.exception.NegocioException;
import br.ages.crud.exception.PersistenciaException;
import br.ages.crud.model.Projeto;

public class ProjetoBO {
	
	private ProjetoDAO projetoDAO;
	
	public ProjetoBO() {
		projetoDAO = new ProjetoDAO();
	}
	
	/**
	 * Cadastra Projeto em nível de negócio, chamando o DAO
	 * 
	 * @param projeto
	 * @throws NegocioException
	 * @throws SQLException
	 * @throws ParseException
	 */
	public void cadastrarProjeto(Projeto project) throws NegocioException, SQLException, ParseException {
		
		try{
			projetoDAO.cadastrar(project);
		} catch (PersistenciaException e) {
			e.printStackTrace();
			throw new NegocioException(e);
		}
	}

	public boolean validarProjeto(Projeto project){
		boolean valido = true;
		
		if(project.getStatus().equals("") || project.getStatus() == null) valido = false;
		if(project.getNome().equals("") || project.getNome() == null) valido = false;
		
		return valido;
	}
	
	public List<Projeto> listarProjeto() throws NegocioException{
		List<Projeto> listProject = null;
		
		try {
			listProject = projetoDAO.listarProjetos();
		} catch (PersistenciaException | SQLException e) {
			e.printStackTrace();
			throw new NegocioException(e);
		}

		return listProject;	
	}

}