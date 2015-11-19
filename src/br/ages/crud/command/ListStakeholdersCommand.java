package br.ages.crud.command;

import java.sql.SQLException;
import java.util.List;
import javax.servlet.http.HttpServletRequest;

import br.ages.crud.bo.StakeholderBO;
import br.ages.crud.exception.NegocioException;
import br.ages.crud.model.Stakeholder;

public class ListStakeholdersCommand implements Command {
	private String proxima;
	private StakeholderBO stakeholderBO;
	@Override
	public String execute(HttpServletRequest request) throws SQLException {
		stakeholderBO = new StakeholderBO();
		proxima = "project/listStakeholder.jsp";
		
		try {
			List<Stakeholder> listaStakeholders = stakeholderBO.listarStakeholder();
			request.setAttribute("listaStakeholders", listaStakeholders);
		}catch (NegocioException e) {
				request.setAttribute("msgErro", e.getMessage());
			}
			
		return proxima;
	}

}
