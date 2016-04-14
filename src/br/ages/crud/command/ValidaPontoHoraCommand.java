package br.ages.crud.command;

import java.sql.SQLException;
import java.text.ParseException;

import javax.servlet.http.HttpServletRequest;

import br.ages.crud.bo.PontoBO;
import br.ages.crud.bo.UsuarioBO;
import br.ages.crud.exception.NegocioException;
import br.ages.crud.exception.PersistenciaException;
import br.ages.crud.model.Ponto;
import br.ages.crud.model.StatusPonto;
import br.ages.crud.model.Usuario;
import br.ages.crud.util.MensagemContantes;

public class ValidaPontoHoraCommand implements Command {

	private String proxima;
	private PontoBO pontoBO;
	private UsuarioBO responsavelBO;

	@Override
	public String execute(HttpServletRequest request) {

	/*	try {

			String idPonto;
			String idResponsavel;
			String senhaResponsavel;

			pontoBO = new PontoBO();
			responsavelBO = new UsuarioBO();

			Ponto ponto = pontoBO.buscaPontoId(Integer.parseInt(idPonto));
			Usuario responsavel = responsavelBO.buscaUsuarioId(Integer.parseInt(idResponsavel));

			StatusPonto statusPonto = pontoBO.validaStatusPonto(responsavel, senhaResponsavel);
			
			if (statusPonto.equals(StatusPonto.VALIDO)) {
				ponto.setResponsavel(responsavel);
				ponto.setStatus(statusPonto);
				pontoBO.editaPonto(ponto);
				request.setAttribute("msgSucesso", MensagemContantes.MSG_SUC_PONTO);
			} else {
				request.setAttribute("msgErro", MensagemContantes.MSG_ERR_PONTO);
			}

		} catch (SQLException | NegocioException | ParseException | PersistenciaException e){
			e.printStackTrace();
		}*/
		return null;
	}

}
