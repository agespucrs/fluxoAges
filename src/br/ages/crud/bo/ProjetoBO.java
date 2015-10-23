package br.ages.crud.bo;

import java.sql.SQLException;
import java.text.ParseException;
import java.util.List;

import br.ages.crud.dao.ProjetoDAO;
import br.ages.crud.exception.NegocioException;
import br.ages.crud.exception.PersistenciaException;
import br.ages.crud.model.Projeto;
//import br.ages.crud.model.Status;
import br.ages.crud.util.MensagemContantes;
import br.ages.crud.validator.DataValidator;

public class ProjetoBO {
	
	private ProjetoDAO projetoDAO;
	
	public ProjetoBO() {
		projetoDAO = new ProjetoDAO();
	}
	
	/**
	 * Cadastra Projeto em n�vel de neg�cio, chamando o DAO
	 * 
	 * @param projeto
	 * @throws NegocioException
	 * @throws SQLException
	 * @throws ParseException
	 */
	public void cadastrarProjeto(Projeto project) throws NegocioException, SQLException, ParseException {
		
		try{
			projetoDAO.cadastrarProjeto(project);
		} catch (PersistenciaException e) {
			e.printStackTrace();
			throw new NegocioException(MensagemContantes.MSG_ERR_CADASTRO_PROJETO);
		}
	}

	public boolean validarProjeto(Projeto project){
		boolean valido = true;
		DataValidator validator = new DataValidator();
		
		//if(project.getStatus() == null) valido = false;
		if(project.getNomeProjeto() == null || project.getNomeProjeto().equals("")) valido = false;
		if(project.getDataInicio() == null) valido = false;
		//if(project.getDataFimPrevisto == null) valido = false;
		//if(!validator.maisCedoQue(project.getDataInicio(), project.getDataFimPrevisto())) valido = false;		
		if(project.getDataFim() != null)
			if(!validator.maisCedoQue(project.getDataInicio(), project.getDataFim())) valido = false;		
		
		return valido;
	}
	
	public void editarProjeto(Projeto project, int id){
		/*
		try{
			projetoDAO.editarProjeto(project, id);
		} catch (PersistenciaException e) {
			e.printStackTrace();
			throw new NegocioException(e);
		}
		*/
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
	
	public void removerProjeto(Projeto project) throws NegocioException {
		try {
			projetoDAO.removerProjeto(project);
		} catch (PersistenciaException e) {
			e.printStackTrace();
			throw new NegocioException(e);
		}
	}
}