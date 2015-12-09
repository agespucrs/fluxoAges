package br.ages.crud.command;

import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;

import br.ages.crud.bo.UsuarioBO;
import br.ages.crud.dao.UsuarioDAO;
import br.ages.crud.exception.NegocioException;
import br.ages.crud.model.PerfilAcesso;
import br.ages.crud.model.StatusUsuario;
import br.ages.crud.model.TipoUsuario;
import br.ages.crud.model.Usuario;
import br.ages.crud.util.MensagemContantes;


public class EditUserCommand implements Command{
	

	private String proxima;
	
	private UsuarioBO usuarioBO;
	


	@Override
	public String execute(HttpServletRequest request) throws SQLException {
		usuarioBO =  new UsuarioBO();
		Usuario usuario;
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
			TipoUsuario tipoUsuario = new TipoUsuario();
			tipoUsuario.setIdTipoUsuario(Integer.parseInt(idTipoUsuario));
			
			PerfilAcesso perfilAcesso = PerfilAcesso.valueOf(perfilAcessoString);
			
			StatusUsuario statusUsuario = StatusUsuario.valueOf(statusUsuarioString);
						
			usuario = new Usuario();
			
			usuario.setIdUsuario(Integer.valueOf(idUsuarioString));
			usuario.setUsuario(usuarioString);
			usuario.setSenha(senhaString);		
			
			TipoUsuario tipoUsuario2 = usuarioBO.consultaTipoUsuario(idTipoUsuario);
			
			usuario.setTipoUsuario(tipoUsuario2);
			usuario.setPerfilAcesso(perfilAcesso);
			usuario.setStatusUsuario(statusUsuario);
			usuario.setMatricula(matriculaString);
			usuario.setNome(nomeString);
			usuario.setEmail(emailString);			
			
			request.setAttribute("usuario", usuario);
			
			boolean isValido = usuarioBO.validaUsuarioA(usuario);
					
			if(isValido){
				usuarioBO.editaUsuario(usuario);
				proxima = "main?acao=listUser";
				request.setAttribute("msgSucesso", MensagemContantes.MSG_SUC_EDICAO_USUARIO.replace("?", nomeString));
			} else {				
				request.setAttribute("msgErro", MensagemContantes.MSG_ERR_USUARIO_DADOS_INVALIDOS);
			}				
		} catch(Exception e){		
			request.setAttribute("msgErro", e.getMessage());
		}
		return proxima;
	}

}