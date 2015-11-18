package br.ages.crud.command;

import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;

import br.ages.crud.bo.StakeholderBO;
import br.ages.crud.model.Stakeholder;
import br.ages.crud.util.MensagemContantes;

public class EditStakeholderCommand implements Command {
	private String proxima;
	private StakeholderBO stakeholderBO;
	public String execute(HttpServletRequest request) throws SQLException {
		stakeholderBO = new StakeholderBO();
		Stakeholder stakeholder;
		proxima = "stakeholder/editStakeholder.jsp";
		
		String nome = request.getParameter("nome");
		
		try {
			stakeholder = new Stakeholder();
			
			stakeholder.setNomeStakeholder(nome);
			
			request.setAttribute("stakeholder", stakeholder);
			
			boolean isValido = stakeholderBO.validaStakeholder(stakeholder);
			if (isValido){
				stakeholderBO.editaStakeholder(stakeholder);
				proxima = "main?acao=listStakeholder";
				request.setAttribute("msgSucesso", MensagemContantes.MSG_SUC_EDITA_STAKEHOLDER.replace("?", nome));
			}else {
				request.setAttribute("msgErro", MensagemContantes.MSG_ERR_STAKEHOLDER_DADO_INVALIDO);
			}
			
		} catch (Exception e){
			request.setAttribute("msgErro", e.getMessage());
		}
		return proxima;
	}
	
}
