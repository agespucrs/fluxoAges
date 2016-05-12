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
	private List<Usuario> listaResponsaveis;
	
	@Override
	public String execute(HttpServletRequest request) throws SQLException {
		pontoBO = new PontoBO();
		usuarioBO = new UsuarioBO();
		usuarios = new ArrayList<>();
		proxima = "aluno/listPontoHoraInvalido.jsp";

		try {
		
				listaPontos = pontoBO.listaPontoInvalidoAlunos();
				request.setAttribute("listaPontos", listaPontos );
			
				listaResponsaveis = usuarioBO.listaUsuariosReponsaveis();
				request.setAttribute("listaResponsaveis", listaResponsaveis);
			
		} catch (NegocioException e) {
			proxima = "projeto/listaProjetos";
			e.printStackTrace();
			request.setAttribute("msgErro", e.getMessage());
		}

		return proxima;
	}
}
