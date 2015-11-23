package br.ages.crud.command;

import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;

import br.ages.crud.bo.ProjetoBO;
import br.ages.crud.bo.UsuarioBO;
import br.ages.crud.exception.NegocioException;
import br.ages.crud.model.PerfilAcesso;
import br.ages.crud.model.Projeto;
import br.ages.crud.model.Usuario;
import br.ages.crud.util.MensagemContantes;

public class RemoveProjetoCommand implements Command{

	private String proximo;
	
	private ProjetoBO projetoBO;

	@Override
	public String execute(HttpServletRequest request) throws SQLException {
		proximo = "main?acao=listaProjetos";
		projetoBO = new ProjetoBO();
		
		Usuario usuario = (Usuario)request.getSession().getAttribute("usuarioSessao");		

		try {
			if( !usuario.getPerfilAcesso().equals(PerfilAcesso.ADMINISTRADOR) ) throw new NegocioException(MensagemContantes.MSG_INF_DENY);

			Integer idProjeto = Integer.parseInt(request.getParameter("id_projeto"));
			
			Projeto projeto = new Projeto();
			projeto.setIdProjeto(idProjeto);
			
		  projetoBO.removerProjeto(projeto);
			request.setAttribute("msgSucesso", MensagemContantes.MSG_SUC_REMOVE_PROJETO.replace("?", idProjeto.toString()).concat("<br/>")); 
			
		} catch (Exception e) {
			request.setAttribute("msgErro", e.getMessage());
		}

		return proximo;
	}

}
