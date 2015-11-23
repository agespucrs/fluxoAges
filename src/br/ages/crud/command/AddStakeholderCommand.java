package br.ages.crud.command;

import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;

import br.ages.crud.bo.StakeholderBO;
import br.ages.crud.model.Stakeholder;
import br.ages.crud.util.MensagemContantes;

public class AddStakeholderCommand implements Command {
	
	private String proxima;
	private StakeholderBO stakeholderBO;
	@Override
	public String execute(HttpServletRequest request) throws SQLException {
		stakeholderBO = new StakeholderBO();
		proxima = "project/addStakeholder.jsp";
		
		String nomeStakeholder = request.getParameter("nomeStakeholder");
		
		try {
			Stakeholder stakeholder = new Stakeholder();
			stakeholder.setNomeStakeholder(nomeStakeholder);
			
			boolean isValido = stakeholderBO.validaStakeholder(stakeholder);
			if (!isValido){
				request.setAttribute("msgErro", MensagemContantes.MSG_ERR_STAKEHOLDER_DADO_INVALIDO);
			} else {
				stakeholderBO.cadastraStakeholder(stakeholder);
				proxima = "main?acao=listaStakeholders";
				request.setAttribute("msgSucesso", MensagemContantes.MSG_SUC_CADASTRO_STAKEHOLDER.replace("?", stakeholder.getNomeStakeholder()));
			}
		} catch (Exception e) {
			request.setAttribute("msgErro", e.getMessage());
		}
		return proxima;
	}

}
