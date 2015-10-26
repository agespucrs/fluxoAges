package br.ages.crud.command;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import br.ages.crud.bo.ProjetoBO;
import br.ages.crud.bo.UsuarioBO;
import br.ages.crud.model.Projeto;
import br.ages.crud.model.Stakeholder;
import br.ages.crud.model.Usuario;

public class CreateScreenProjectCommand implements Command {

	private String proxima;
	
	private ProjetoBO projetoBO;
	
	private UsuarioBO usuarioBO;
	
	//private StakeoholderBO stakeholderBO;

	@Override
	public String execute(HttpServletRequest request) throws SQLException {
		//TODO utilizar stakeholderBO.listaStakeholders após a implementação do mesmo
		try{
			
			String isEdit = request.getParameter("isEdit");
			
			if (isEdit != null && !"".equals(isEdit)) {
				proxima = "project/editProject.jsp";
				projetoBO = new ProjetoBO();

				int idProjeto = Integer.parseInt(request.getParameter("id_projeto"));
				Projeto projeto = projetoBO.buscarProjeto(idProjeto);
				
				usuarioBO = new UsuarioBO();
				List<Usuario> usuarios = usuarioBO.listarUsuario();
				
				ArrayList<Stakeholder> stakeholders = new ArrayList<Stakeholder>();
				stakeholders.add(new Stakeholder(1, "Misto", "Quente"));
				
				request.setAttribute("projeto", projeto);
				request.setAttribute("usuarios", usuarios);
				request.setAttribute("stakeholders", stakeholders);
				
				
			} else {
				//TODO implementar StakeholderBO e DAO pra fazer essa parte
				proxima = "user/addProject.jsp";
				
				ArrayList<Stakeholder> stakeholders = new ArrayList<Stakeholder>();
				stakeholders.add(new Stakeholder(1, "Misto", "Quente"));

				usuarioBO = new UsuarioBO();
				List<Usuario> usuarios = usuarioBO.listarUsuario();				
				
				request.setAttribute("stakeholders", stakeholders);		
				request.setAttribute("usuarios", usuarios);
			}
		} catch(Exception e){
			request.setAttribute("msgErro", e.getMessage());
		}
		
		return proxima;
	}
	
	
}
