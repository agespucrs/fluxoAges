package br.ages.crud.command;

import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;

import br.ages.crud.bo.UsuarioBO;
import br.ages.crud.dao.UsuarioDAO;
import br.ages.crud.model.PerfilAcesso;
import br.ages.crud.model.StatusUsuario;
import br.ages.crud.model.TipoUsuario;
import br.ages.crud.model.Usuario;


public class EditUserCommand implements Command{
	

	private String proxima;
	
	private UsuarioBO usuarioBO;
	


	@Override
	public String execute(HttpServletRequest request) throws SQLException {
		usuarioBO =  new UsuarioBO();
		proxima = "user/editUser.jsp";
		
		String idUsuarioString = request.getParameter("idUsuario");
		String usuarioString = request.getParameter("usuario");
		String senhaString = request.getParameter("senha");
		String idTipoUsuario = request.getParameter("tipoUsuario");
		String perfilAcessoString = request.getParameter("perfilAcesso");
		String statusUsuarioString = request.getParameter("statusUsuario");
		String matriculaString = request.getParameter("matricula");
		String nomeString = request.getParameter("nome");
		String emailString = request.getParameter("email");
		
		
		try{
			Integer idUsuario = Integer.parseInt(idUsuarioString);
			
			PerfilAcesso perfilAcesso = PerfilAcesso.valueOf(perfilAcessoString);
			
			StatusUsuario statusUsuario = StatusUsuario.valueOf(statusUsuarioString);
						
			Usuario usuario = new Usuario();
			usuario.setIdUsuario(idUsuario);
			usuario.setSenha(senhaString);
		
			usuario.setPerfilAcesso(perfilAcesso);
			usuario.setMatricula(matriculaString);
			usuario.setNome(nomeString);
			usuario.setEmail(emailString);
			
			TipoUsuario tipoUsuario =  usuarioBO.consultaTipoUsuario(idTipoUsuario);
			
			usuario.setTipoUsuario(tipoUsuario);
			
			boolean isValido = usuarioBO.validaUsuario(usuario);
					
			if(isValido){
				usuarioBO.cadastraUsuario(usuario);
			}
		return proxima;		
	}

}