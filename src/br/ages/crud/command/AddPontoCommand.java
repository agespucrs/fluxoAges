package br.ages.crud.command;

import java.sql.SQLException;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import br.ages.crud.bo.PontoBO;
import br.ages.crud.bo.UsuarioBO;
import br.ages.crud.exception.NegocioException;
import br.ages.crud.model.Ponto;
import br.ages.crud.model.StatusPonto;
import br.ages.crud.model.Usuario;
import br.ages.crud.util.MensagemContantes;
import br.ages.crud.util.Util;

public class AddPontoCommand implements Command {

	private String proxima;
	private UsuarioBO usuarioBO;
	private PontoBO pontoBO;

	@Override
	public String execute(HttpServletRequest request) throws SQLException, NegocioException {

		pontoBO = new PontoBO();
		proxima = "main?acao=registrarPonto";

		String idAluno = request.getParameter("idAluno");
		String idResponsavel = request.getParameter("idResponsavel");
		String dataEntradaString = request.getParameter("dtEntrada");
		String dataSaidaString = request.getParameter("dtSaida");
		String senhaResponsavel = request.getParameter("senhaResponsavel");
		try {
			Ponto ponto = new Ponto();

			usuarioBO = new UsuarioBO();
			Usuario aluno = usuarioBO.buscaUsuarioId(Integer.parseInt(idAluno));
			ponto.setAluno(aluno);

			usuarioBO = new UsuarioBO();
			Usuario responsavel = usuarioBO.buscaUsuarioId(Integer.parseInt(idResponsavel));
			ponto.setResponsavel(responsavel);

			Date dataEntrada = Util.stringToDateTime(dataEntradaString);
			Date dataSaida = dataEntradaString.equals("") ? null : Util.stringToDateTime(dataSaidaString);

			ponto.setDataEntrada(dataEntrada);
			ponto.setDataSaida(dataSaida);
						
			StatusPonto statusPonto = pontoBO.validaStatusPonto(responsavel, senhaResponsavel);
			ponto.setStatus(statusPonto);
			
			boolean isValido = pontoBO.validaPonto(ponto);
			if (isValido == false) {
				request.setAttribute("msgErro", MensagemContantes.MSG_ERR_CADASTRO_PONTO);
			} else { // cadastro ponto com sucesso
				pontoBO.cadastrarPonto(ponto);
				proxima = "main?acao=registrarPonto";
				request.setAttribute("msgSucesso", MensagemContantes.MSG_SUC_CADASTRO_PONTO.replace("?", ponto.getAluno().getNome()));

			}
		} catch (Exception e) {
			request.setAttribute("msgErro", e.getMessage());
			e.printStackTrace();
		}

		return proxima;
	}
}
