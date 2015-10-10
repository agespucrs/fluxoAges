package br.ages.crud.command;

import java.sql.SQLException;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import br.ages.crud.bo.ProjetoBO;
import br.ages.crud.model.Projeto;
import br.ages.crud.util.MensagemContantes;

public class AdicionaProjetoCommand implements Command {
	
	private String proxima;
	
	private ProjetoBO projetoBO;

	@Override
	public String execute(HttpServletRequest request) throws SQLException {
		projetoBO =  new ProjetoBO();
		proxima = "project/addProject.jsp";
		
		String nome = request.getParameter("nome");
		String equipe = request.getParameter("equipe");
		String status = request.getParameter("status");
		String workspace = request.getParameter("workspace");
		
		try{
			Projeto projeto = new Projeto();
			projeto.setNomeProjeto(nome);
			//projeto.setEquipe(equipe);
			projeto.setStatus(status);
			projeto.setWorkspace(workspace);

			boolean isValido = projetoBO.validarProjeto(projeto);
			
			if (!isValido) {
				request.setAttribute("msgErro", MensagemContantes.MSG_ERR_PROJETO_DADOS_INVALIDOS);
			} else { // cadastro de projeto com sucesso
				projetoBO.cadastrarProjeto(projeto);
				proxima = "main?acao=listProject";
				request.setAttribute("msgSucesso", MensagemContantes.MSG_SUC_CADASTRO_PROJETO.replace("?", projeto.getNomeProjeto()));
			}
			
		}catch(Exception e){
			request.setAttribute("msgErro", e.getMessage());
		}
		
		
		
		return null;
	}

}
