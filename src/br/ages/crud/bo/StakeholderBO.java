package br.ages.crud.bo;

import java.text.Normalizer;

import br.ages.crud.dao.StakeholderDAO;
import br.ages.crud.exception.NegocioException;
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
			
			String nomeStakeholder = Normalizer.normalize(stakeholder.getNomeStakeholder(), Normalizer.Form.NFD).replace("\\p{InCombiningDiacriticalMarks}+", "");
			
			if(!nomeStakeholder.matches("(([A-Z][a-z]*)\\s{0,1}")) {
				isValido = false;
				msg.append(MensagemContantes.MSG_ERR_STAKEHOLDER_NOME_INVALIDO.replace("?","Nomen").concat("<br/>"));
			}
			if (!isValido) {
				throw new NegocioException(msg.toString());
			}
		}	catch(Exception e) {
			e.printStackTrace();
			throw new NegocioException(e);
		}
		return isValido;
	}
	
}
