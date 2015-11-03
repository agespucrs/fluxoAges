package br.ages.crud.command;

import java.util.Date;
import java.util.ArrayList;

import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.Part;

import br.ages.crud.bo.ArquivoBO;
import br.ages.crud.bo.ProjetoBO;
import br.ages.crud.model.Projeto;
import br.ages.crud.model.Stakeholder;
import br.ages.crud.model.StatusProjeto;
import br.ages.crud.model.Usuario;
import br.ages.crud.util.Constantes;
import br.ages.crud.util.MensagemContantes;
import br.ages.crud.util.Util;

@MultipartConfig
public class AdicionaProjetoCommand implements Command {
	
	private String proxima;
	
	private ProjetoBO projetoBO;
	
	private ArquivoBO arquivoBO;
	
	@Override
	public String execute(HttpServletRequest request) {
		projetoBO =  new ProjetoBO();
		proxima = "project/addProject.jsp";
		
		String nomeProjeto = request.getParameter("nomeProjeto");

		String[] usuariosString = request.getParameterValues("listaUsuarios");

		String[] stakeholdersString = request.getParameterValues("listaStakeholders");

		String statusProjetoString = request.getParameter("statusProjeto");
		String workspace = request.getParameter("workspace");
		String dataInicioString = request.getParameter("dataInicio");
		String dataFimPrevistoString = request.getParameter("dataFimPrevista");
		String dataFimString = request.getParameter("dataFim");
		
		
		try{
			//cria o array de usuarios com o array de String do request
			ArrayList<Usuario> usuarios = new ArrayList<Usuario>();		
			for(String s: usuariosString){
				usuarios.add(new Usuario(Integer.parseInt(s)));
			}
			// mesma coisa mas com stakeholders
			ArrayList<Stakeholder> stakeholders = new ArrayList<Stakeholder>();	
			for(String s: stakeholdersString){
				stakeholders.add(new Stakeholder(Integer.parseInt(s)));
			}
			// cria um StatusProjeto com o string do request
			StatusProjeto statusProjeto = StatusProjeto.valueOf(statusProjetoString); 
			// cria Dates com os strings recebidos
			Date dataInicio = Util.stringToDate(dataInicioString);				
			Date dataFimPrevisto = Util.stringToDate(dataFimPrevistoString);
			Date dataFim = Util.stringToDate(dataFimString);
			
			
			Projeto projeto = new Projeto();
			projeto.setNomeProjeto(nomeProjeto);
			projeto.setUsuarios(usuarios);
			projeto.setStatusProjeto(statusProjeto);
			projeto.setWorkspace(workspace);
			projeto.setStakeholders(stakeholders);
			projeto.setDataInicio(dataInicio);
			projeto.setDataFim(dataFim);
			projeto.setDataFimPrevisto(dataFimPrevisto);

			boolean isValido = projetoBO.validarProjeto(projeto);			
			
			if(isValido) {
				projetoBO.cadastrarProjeto(projeto);
				request.setAttribute("msgSucesso", MensagemContantes.MSG_SUC_CADASTRO_PROJETO.replace("?", nomeProjeto));
			} else{
				request.setAttribute("msgErro", MensagemContantes.MSG_ERR_PROJETO_DADOS_INVALIDOS);
			}
			
		}catch(Exception e){
			request.setAttribute("msgErro", e.getMessage());
		}
			
		return proxima;
	}

}
