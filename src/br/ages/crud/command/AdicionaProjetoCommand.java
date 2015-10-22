package br.ages.crud.command;

import java.util.ArrayList;

import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.Part;

import br.ages.crud.bo.ArquivoBO;
import br.ages.crud.bo.ProjetoBO;
import br.ages.crud.model.Projeto;
import br.ages.crud.model.Status;
import br.ages.crud.model.Usuario;
import br.ages.crud.util.Constantes;
import br.ages.crud.util.MensagemContantes;

@MultipartConfig
public class AdicionaProjetoCommand implements Command {
	
	private String proxima;
	
	private ProjetoBO projetoBO;
	
	private ArquivoBO arquivoBO;
	
	@Override
	public String execute(HttpServletRequest request) {
		projetoBO =  new ProjetoBO();
		proxima = "project/addProject.jsp";
		
		String nome = request.getParameter("nome");
		//
		String integrante1 = request.getParameter("integrante1");
		//
		String status = request.getParameter("status");
		String workspace = request.getParameter("workspace");
		String dataInicioString = request.getParameter("dataInicio");
		String dataFimPrevistoString = request.getParameter("dataFimPrevisto");
		String dataFimString = request.getParameter("dataFim");
		
		//validação de datas (talvez dentro do try?)
		
		try{
			Projeto projeto = new Projeto();
			projeto.setNomeProjeto(nome);
			//projeto.setIntegrantes(ArrayList<Usuario);
			projeto.setStatus(Status.valueOf(status));
			projeto.setWorkspace(workspace);
			//TODO outros campos

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
					arquivoBO.uploadArquivo(arquivo, nome, Constantes.PROJETO_UPLOAD_PATH);
					
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
