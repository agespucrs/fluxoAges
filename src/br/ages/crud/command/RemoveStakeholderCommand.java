package br.ages.crud.command;

import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;

import br.ages.crud.bo.StakeholderBO;
import br.ages.crud.exception.NegocioException;
import br.ages.crud.model.*;
import br.ages.crud.util.MensagemContantes;

public class RemoveStakeholderCommand implements Command {
	private StakeholderBO stakeholderBO;
	private String proximo;
	@Override
	public String execute(HttpServletRequest request) throws SQLException {
		proximo = "main?acao=listStakeholder";
		stakeholderBO = new StakeholderBO();
		
		Usuario usuario = (Usuario)request.getSession().getAttribute("usuarioSessao");
		try {
			if (!usuario.getPerfilAcesso().equals(PerfilAcesso.ADMINISTRADOR))
				throw new NegocioException(MensagemContantes.MSG_INF_DENY);
		Integer idStakeholder = Integer.parseInt(request.getParameter("id_stakeholder"));
		stakeholderBO.removerStakeholder(idStakeholder);
		
		request.setAttribute("msgSucesso", MensagemContantes.MSG_SUC_REMOVE_STAKEHOLDER.replace("?", idStakeholder.toString()).concat("<br/>"));
		} catch (Exception e){
			request.setAttribute("msgErro", e.getMessage());
		}
		return proximo;
	}

}
