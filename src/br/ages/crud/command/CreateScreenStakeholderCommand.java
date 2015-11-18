package br.ages.crud.command;

import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;

import br.ages.crud.bo.StakeholderBO;
import br.ages.crud.model.Stakeholder;

public class CreateScreenStakeholderCommand implements Command {

	private String proxima;
	
	private StakeholderBO stakeholderBO;
	@Override
	public String execute(HttpServletRequest request) throws SQLException {
		proxima= "main?acao=listStakeholder";
		String isEdit = request.getParameter("isEdit");
		try{
		if (isEdit.equals("")) {
		
			proxima= "stakeholder/addStakeholder.jsp";
			
		} else {
			int idStakeholder = Integer.parseInt(request.getParameter("idStakeholder"));
			Stakeholder stakeholder = stakeholderBO.bucaStakeholderId(idStakeholder);
			request.setAttribute("stakeholder", stakeholder);
			proxima= "stakeholder/addStakeholder.jsp";
		}
		
	} catch (Exception e) {
		request.setAttribute("msgErro", e.getMessage());
	}
		
	return proxima;
  }
}
