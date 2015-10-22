package br.ages.crud.command;

import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.Part;

import br.ages.crud.bo.ArquivoBO;
import br.ages.crud.bo.ProjetoBO;
import br.ages.crud.model.Projeto;
import br.ages.crud.util.Constantes;
import br.ages.crud.util.MensagemContantes;

public class EditaProjetoCommand implements Command{
	
	private ProjetoBO projetoBO;
	
	private ArquivoBO arquivoBO;
	
	private String proxima;

	@Override
	public String execute(HttpServletRequest request) throws SQLException {
		
		String id = request.getParameter("id");
		
		String nome = request.getParameter("nome");
		String equipe = request.getParameter("equipe");
		String status = request.getParameter("status");
		String workspace = request.getParameter("workspace");
		//TODO outros campos
		
		
		try{
			Projeto projeto = new Projeto();
			projeto.setNomeProjeto(nome);
			//projeto.setEquipe(equipe);
			//projeto.setStatus(Status.valueOf(status));
			projeto.setWorkspace(workspace);
			
			int idProjeto = Integer.parseInt(id);

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
					
					projetoBO.editarProjeto(projeto, idProjeto);
					
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
