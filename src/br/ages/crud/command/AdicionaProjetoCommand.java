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
		
		String nomeProjeto = request.getParameter("nome");
		//
		String[] usuariosString = request.getParameterValues("usuarios");
		//éoq
		String[] stakeholdersString = request.getParameterValues("stakeholders");
		//
		String statusProjetoString = request.getParameter("status");
		String workspace = request.getParameter("workspace");
		String dataInicioString = request.getParameter("dataInicio");
		String dataFimPrevistoString = request.getParameter("dataFimPrevisto");
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
			
			if (!isValido) {
				request.setAttribute("msgErro", MensagemContantes.MSG_ERR_PROJETO_DADOS_INVALIDOS);
			} else {
				//começa o troço do upload
				arquivoBO = new ArquivoBO();
				
				Part arquivo = request.getPart("arquivo");
				
				boolean tamanhoValido = arquivoBO.validaTamanho(arquivo, Constantes.PROJETO_ARQUIVO_MAX_BYTES);
				boolean extensaoValida = arquivoBO.validaExtensao(arquivo, Constantes.PROJETO_FILE_EXT);
				
				if(!tamanhoValido || !extensaoValida){
					request.setAttribute("msgErro", MensagemContantes.MSG_ERR_PROJETO_ARQUIVO_INVALIDO.replace("?", String.valueOf(Constantes.PROJETO_ARQUIVO_MAX_BYTES)));
				} else{
					arquivoBO.uploadArquivo(arquivo, nomeProjeto, Constantes.PROJETO_UPLOAD_PATH);
					
					projetoBO.cadastrarProjeto(projeto);
					
					proxima = "main?acao=listProject";
					request.setAttribute("msgSucesso", MensagemContantes.MSG_SUC_CADASTRO_PROJETO.replace("?", projeto.getNomeProjeto()));
				}				
			}
			
		}catch(Exception e){
			request.setAttribute("msgErro", e.getMessage());
		}
			
		return proxima;
	}

}
