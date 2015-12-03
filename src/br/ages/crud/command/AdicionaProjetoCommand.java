package br.ages.crud.command;

import java.util.ArrayList;
import java.util.Date;

import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServletRequest;

import br.ages.crud.bo.ArquivoBO;
import br.ages.crud.bo.ProjetoBO;
import br.ages.crud.bo.StakeholderBO;
import br.ages.crud.bo.UsuarioBO;
import br.ages.crud.model.Projeto;
import br.ages.crud.model.Stakeholder;
import br.ages.crud.model.StatusProjeto;
import br.ages.crud.model.Usuario;
import br.ages.crud.util.MensagemContantes;
import br.ages.crud.util.Util;

@MultipartConfig
public class AdicionaProjetoCommand implements Command {
	
	private String proxima;
	
	private ProjetoBO projetoBO;
	
	private ArquivoBO arquivoBO;
	
	private UsuarioBO usuarioBO;
	
	private Usuario usuario;
	
	private StakeholderBO stakeholderBO;
	
	private Stakeholder stakeholder;
	
	@Override
	public String execute(HttpServletRequest request) {
		projetoBO =  new ProjetoBO();
		proxima = "main?acao=telaProjeto";
		
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
				usuario = new Usuario();
				usuario.setIdUsuario(Integer.valueOf(s));
				usuarios.add(usuario);
			}
		// mesma coisa mas com stakeholders
			ArrayList<Stakeholder> stakeholders = new ArrayList<Stakeholder>();	
			for(String s : stakeholdersString){
				stakeholder = new Stakeholder();
				stakeholder.setIdStakeholder(Integer.valueOf(s));
				stakeholders.add(stakeholder);
			}
			
			

			// cria um StatusProjeto com o string do request
			StatusProjeto statusProjeto = StatusProjeto.valueOf(statusProjetoString); 
			// cria Dates com os strings recebidos
			Date dataInicio = Util.stringToDate(dataInicioString);				
			Date dataFimPrevisto = Util.stringToDate(dataFimPrevistoString);
			Date dataFim = dataFimString.equals("") ? null : Util.stringToDate(dataFimString);
			
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
				request.setAttribute("projeto", projeto);
				proxima = "project/uploadProject.jsp";
				request.setAttribute("msgSucesso", MensagemContantes.MSG_SUC_CADASTRO_PROJETO.replace("?", nomeProjeto));
			} else{
				request.setAttribute("msgErro", MensagemContantes.MSG_ERR_PROJETO_DADOS_INVALIDOS);
			}
			
		}catch(Exception e){
			request.setAttribute("msgErro", e.getMessage());
			e.printStackTrace();
		}
			
		return proxima;
	}

}
