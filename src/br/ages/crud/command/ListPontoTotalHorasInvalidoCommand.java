package br.ages.crud.command;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import br.ages.crud.bo.PontoBO;
import br.ages.crud.bo.UsuarioBO;
import br.ages.crud.exception.NegocioException;
import br.ages.crud.model.ResumoPonto;
import br.ages.crud.model.Usuario;

public class ListPontoTotalHorasInvalidoCommand implements Command {

	private String proxima;
	private UsuarioBO usuarioBO;
	private List<Usuario> usuarios;
	private PontoBO pontoBO;
	private ArrayList<ResumoPonto> listaPontos ;
	
	@Override
	public String execute(HttpServletRequest request) throws SQLException {
		pontoBO = new PontoBO();
		usuarioBO = new UsuarioBO();
		usuarios = new ArrayList<>();
		proxima = "aluno/listPontoHoraInvalido.jsp";

		try {
		
			
			Integer idUsuario = Integer.valueOf(request.getParameter("id_usuario"));
						
			usuarios = usuarioBO.listarUsuarioAlunos();
			
			request.setAttribute("usuarios", usuarios);
		
/*			if (idUsuario == 0) {
		//	ResumoPonto rm = new ResumoPonto(0, "", null, null);
				listaPontos = new ArrayList<>();
		//		listaPontos.add(rm);
				request.setAttribute("listaPontos", listaPontos );
				request.setAttribute("totalHorasAluno", 0);
			} else {*/
				listaPontos = pontoBO.listaPontoInvalidoAlunos(idUsuario);
				request.setAttribute("listaPontos", listaPontos );
				request.setAttribute("totalHorasAluno", pontoBO.calculatotalHorasAluno(listaPontos));
		
			
			
		} catch (NegocioException e) {
			e.printStackTrace();
			request.setAttribute("msgErro", e.getMessage());
		}

		return proxima;
	}
}
