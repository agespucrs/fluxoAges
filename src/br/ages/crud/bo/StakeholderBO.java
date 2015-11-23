
package br.ages.crud.bo;

import java.sql.SQLException;
import java.text.Normalizer;
import java.text.ParseException;
import java.util.List;

import br.ages.crud.dao.StakeholderDAO;
import br.ages.crud.exception.NegocioException;
import br.ages.crud.exception.PersistenciaException;
import br.ages.crud.model.Stakeholder;
import br.ages.crud.util.MensagemContantes;

public class StakeholderBO {
	StakeholderDAO stakeholderDAO = null;
	
	public StakeholderBO(){
		stakeholderDAO = new StakeholderDAO();
	}
	
	public boolean validaStakeholder(Stakeholder stakeholder) throws NegocioException {
		boolean isValido = true;
		StringBuilder msg = new StringBuilder();
		msg.append(MensagemContantes.MSG_ERR_STAKEHOLDER_DADO_INVALIDO.concat("<br/>")); 
		
		try{
			if(stakeholder.getNomeStakeholder() == null || "".equals(stakeholder.getNomeStakeholder())) {
				isValido = false;
				msg.append(MensagemContantes.MSG_ERR_STAKEHOLDER_NOME_OBRIGATORIO + "<br>");
			}
			
			//String nomeStakeholder = Normalizer.normalize(stakeholder.getNomeStakeholder(), Normalizer.Form.NFD).replace("\\p{InCombiningDiacriticalMarks}+", "");
			
			/*if(!nomeStakeholder.matches("(([A-Z][a-z]*)\\s{0,1}")) {
				isValido = false;
				msg.append(MensagemContantes.MSG_ERR_STAKEHOLDER_NOME_INVALIDO.replace("?","Nomen").concat("<br/>"));
			}*/
			if (!isValido) {
				throw new NegocioException(msg.toString());
			}
		}	catch(Exception e) {
			e.printStackTrace();
			throw new NegocioException(e);
		}
		return isValido;
	}
	

	public void cadastraStakeholder(Stakeholder stakeholder) throws NegocioException, SQLException, ParseException {
		
		try{
			stakeholderDAO.cadastrarStakeholder(stakeholder);
		} catch (PersistenciaException e) {
			throw new NegocioException(e);
		}
		
	}
	
	public List<Stakeholder> listarStakeholder() throws NegocioException {
		
		List<Stakeholder> listStakeholder = null;
		
		try {
			listStakeholder = stakeholderDAO.listarStakeholders();
		} catch(PersistenciaException | SQLException e) {
			e.printStackTrace();
			throw new NegocioException(e);
		}
		
		return listStakeholder;
	}
	
	public void removerStakeholder(Integer idStakeholder) throws NegocioException, SQLException {
		try{
			if(validaStakeholderProjeto(idStakeholder))
				stakeholderDAO.removerStakeholder(idStakeholder);
		} catch(PersistenciaException e) {
			e.printStackTrace();
			throw new NegocioException(MensagemContantes.MSG_ERR_REMOVE_STAKEHOLDER_EM_PROJETO);
		}		
	}
	
	private boolean validaStakeholderProjeto(Integer idStakeholder) throws NegocioException, SQLException {
		int id = -1;
		try{
			id = stakeholderDAO.verificaStakeholderProjeto(idStakeholder);			
		} catch(PersistenciaException e) {
			e.printStackTrace();
			throw new NegocioException(e);
		}
		if(id != -1) return false;
		return true;
	}
	
	public Stakeholder bucaStakeholderId(int idStakeholder) throws NegocioException {
		try{
			Stakeholder stakeholder = stakeholderDAO.buscaStakeholderId(idStakeholder);
			
			return stakeholder;
		} catch(Exception e) {
			e.printStackTrace();
			throw new NegocioException(e);
		}
	}
	
	public void editaStakeholder(Stakeholder stakeholder) throws NegocioException {
		try{
			stakeholderDAO.editaStakeholder(stakeholder);
		} catch(Exception e){
			e.printStackTrace();
			throw new NegocioException(e);
		}
	}
	

}

