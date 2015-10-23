package br.ages.crud.command;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

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

public class EditaProjetoCommand implements Command{
	
	private ProjetoBO projetoBO;
	
	private ArquivoBO arquivoBO;
	
	private String proxima;

	@Override
	public String execute(HttpServletRequest request) throws SQLException {
		projetoBO =  new ProjetoBO();
		proxima = "project/editProject.jsp";
		
		String idProjetoString = request.getParameter("idProjeto");
		String nomeProjeto = request.getParameter("nome");
		String[] usuariosString = request.getParameterValues("usuarios");
		String statusProjetoString = request.getParameter("status");
		String[] stakeholdersString = request.getParameterValues("stakeholders");
		String workspace = request.getParameter("workspace");
		String dataInicioString = request.getParameter("dataInicio");
		String dataFimString = request.getParameter("dataFim");
		String dataFimPrevistoString = request.getParameter("dataFimPrevisto");
		
		try{
			Integer idProjeto = Integer.parseInt(idProjetoString);

			ArrayList<Usuario> usuarios = new ArrayList<Usuario>();		
			for(String s: usuariosString){
				usuarios.add(new Usuario(Integer.parseInt(s)));
			}
			
			ArrayList<Stakeholder> stakeholders = new ArrayList<Stakeholder>();	
			for(String s: stakeholdersString){
				stakeholders.add(new Stakeholder(Integer.parseInt(s)));
			}
			
			StatusProjeto statusProjeto = StatusProjeto.valueOf(statusProjetoString); 
			Date dataInicio = Util.stringToDate(dataInicioString);				
			Date dataFimPrevisto = Util.stringToDate(dataFimPrevistoString);
			Date dataFim = Util.stringToDate(dataFimString);
			
			
			Projeto projeto = new Projeto();
			projeto.setIdProjeto(idProjeto);
			projeto.setNomeProjeto(nomeProjeto);
			projeto.setUsuarios(usuarios);
			projeto.setStatusProjeto(statusProjeto);
			projeto.setWorkspace(workspace);
			projeto.setStakeholders(stakeholders);
			projeto.setDataInicio(dataInicio);
			projeto.setDataFim(dataFim);
			projeto.setDataFimPrevisto(dataFimPrevisto);
			
			boolean isValido = projetoBO.validarProjeto(projeto);
			
			if(isValido){
				arquivoBO = new ArquivoBO();
				
				Part arquivo = request.getPart("arquivo");
				
				boolean tamanhoValido = arquivoBO.validaTamanho(arquivo, Constantes.PROJETO_ARQUIVO_MAX_BYTES);
				boolean extensaoValida = arquivoBO.validaExtensao(arquivo, Constantes.PROJETO_FILE_EXT);
				
				if(tamanhoValido && extensaoValida){
					arquivoBO.uploadArquivo(arquivo, nomeProjeto, Constantes.PROJETO_UPLOAD_PATH);
					
					projetoBO.cadastrarProjeto(projeto);
					
					proxima = "main?acao=listProject";
					request.setAttribute("msgSucesso", MensagemContantes.MSG_SUC_CADASTRO_PROJETO.replace("?", projeto.getNomeProjeto()));
				} else {
					request.setAttribute("msgErro", MensagemContantes.MSG_ERR_PROJETO_ARQUIVO_INVALIDO.replace("?", String.valueOf(Constantes.PROJETO_ARQUIVO_MAX_BYTES)));
				}
			} else {
				request.setAttribute("msgErro", MensagemContantes.MSG_ERR_PROJETO_DADOS_INVALIDOS);
			}
		}catch(Exception e){
			request.setAttribute("msgErro", e.getMessage());
		}		
		
		return proxima;		
	}

}
