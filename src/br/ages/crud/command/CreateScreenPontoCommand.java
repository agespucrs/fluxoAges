package br.ages.crud.command;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import br.ages.crud.bo.UsuarioBO;
import br.ages.crud.model.Usuario;

public class CreateScreenPontoCommand implements Command {

	private String proxima;
	private UsuarioBO usuarioBO;
	private List<Usuario> usuarios; 
	private List<Usuario> responsaveis; 
	
	@Override
	public String execute(HttpServletRequest request) throws SQLException {
		proxima = "aluno/ponto.jsp";
		usuarioBO = new UsuarioBO();
		usuarios = new ArrayList<>();
		responsaveis = new ArrayList<>();
		try {
			Usuario usuario = (Usuario) request.getSession().getAttribute("usuarioSessao");
		
			usuarios = usuarioBO.listarUsuarioAlunos();
		
			for(int i = 0; i < usuarios.size(); i++){
					if(usuarios.get(i).getIdUsuario() == usuario.getIdUsuario()){
						usuarios.remove(i);
						break;
				}
			}
			request.setAttribute("usuarios", usuarios);
			
			responsaveis = usuarioBO.listaUsuariosReponsaveis();
			request.setAttribute("responsaveis", responsaveis);

			
			
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("msgErro", e.getMessage());
		}

		return proxima;
	}
}
