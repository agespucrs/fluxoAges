package br.ages.crud.command;

import java.sql.SQLException;
import java.text.SimpleDateFormat;
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
		
		ArrayList<Stakeholder> stakeholders = new ArrayList<Stakeholder>();
		stakeholders.add(new Stakeholder(1, "Getulio Vargas", "Diretor"));
		stakeholders.add(new Stakeholder(2, "Armando Nogueira", "Desenvolvedor"));
		stakeholders.add(new Stakeholder(3, "Bento Gonsalves", "Gerente"));
		
		try{
			
			String isEdit = request.getParameter("isEdit");
			
			if (isEdit != null && !"".equals(isEdit)) {
				proxima = "project/editProject.jsp";
				projetoBO = new ProjetoBO();

				int idProjeto = Integer.parseInt(request.getParameter("id_projeto"));
				Projeto projeto = projetoBO.buscarProjeto(idProjeto);
				
				usuarioBO = new UsuarioBO();
				List<Usuario> usuarios = usuarioBO.listarUsuario();
				
				
				
				request.setAttribute("projeto", projeto);
				request.setAttribute("listaUsuarios", usuarios);
				request.setAttribute("listaStakeholders", stakeholders);
				
				
			} else {
				//TODO implementar StakeholderBO e DAO pra fazer essa parte
				proxima = "project/addProject.jsp";
		
				usuarioBO = new UsuarioBO();
				List<Usuario> usuarios = usuarioBO.listarUsuario();				
				
				request.setAttribute("listaStakeholders", stakeholders);		
				request.setAttribute("listaUsuarios", usuarios);
			}
		} catch(Exception e){
			request.setAttribute("msgErro", e.getMessage());
		}
		
		return proxima;
	}
	
	
}
