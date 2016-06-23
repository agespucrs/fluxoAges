package br.ages.crud.command;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import br.ages.crud.bo.PontoBO;
import br.ages.crud.bo.UsuarioBO;
import br.ages.crud.exception.NegocioException;
import br.ages.crud.model.PerfilAcesso;
import br.ages.crud.model.Ponto;
import br.ages.crud.model.Usuario;
import br.ages.crud.util.MensagemContantes;

public class CreateScreenPontoCommand implements Command {

	private String proxima;
	private Ponto ponto;
	private PontoBO pontoBO;
	private UsuarioBO usuarioBO;
	private List<Usuario> usuarios;
	private List<Usuario> responsaveis;

	@Override
	public String execute(HttpServletRequest request) throws SQLException {
		usuarioBO = new UsuarioBO();
		pontoBO = new PontoBO();
		usuarios = new ArrayList<>();
		responsaveis = new ArrayList<>();
		Usuario usuario = (Usuario) request.getSession().getAttribute("usuarioSessao");
		String isEdit = request.getParameter("isEdit");

		proxima = "main?acao=listaAluno";
		
		try {
			if (!usuario.getPerfilAcesso().equals(PerfilAcesso.ADMINISTRADOR))
				throw new NegocioException(MensagemContantes.MSG_INF_DENY);

			responsaveis = usuarioBO.listaUsuariosReponsaveis();
			request.setAttribute("responsaveis", responsaveis);
			

			if (isEdit != null && !"".equals(isEdit)) {
				int id = Integer.parseInt(request.getParameter("id_ponto"));
				ponto = pontoBO.buscaPontoId(id);
				request.setAttribute("ponto", ponto);
				proxima = "aluno/editPonto.jsp";
			
			} else {
				usuarios = usuarioBO.listarUsuarioAlunos();
				for (int i = 0; i < usuarios.size(); i++) {
					if (usuarios.get(i).getIdUsuario() == usuario.getIdUsuario()) {
						usuarios.remove(i);
						break;
					}
				}
				request.setAttribute("usuarios", usuarios);
				proxima = "aluno/ponto.jsp";
			}

		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("msgErro", e.getMessage());
		}
		return proxima;
	}
}
