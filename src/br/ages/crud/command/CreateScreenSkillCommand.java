package br.ages.crud.command;

import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import br.ages.crud.bo.SkillsDefinicaoBO;
import br.ages.crud.bo.UsuarioBO;
import br.ages.crud.exception.NegocioException;
import br.ages.crud.exception.PersistenciaException;
import br.ages.crud.model.PerfilAcesso;
import br.ages.crud.model.SkillsDefinicao;
import br.ages.crud.model.Usuario;
import br.ages.crud.util.MensagemContantes;

public class CreateScreenSkillCommand implements Command {

	private String proxima;
	private UsuarioBO usuarioBO;
	private SkillsDefinicaoBO skillsDefinicaoBO;
	private List<Usuario> usuarios;
	private List<Usuario> responsaveis;
	private List<SkillsDefinicao> skillsHards;
	private List<SkillsDefinicao> skillsSofts;
	
	@Override
	public String execute(HttpServletRequest request) throws SQLException, NegocioException, ParseException, PersistenciaException {
		usuarioBO = new UsuarioBO();
		skillsDefinicaoBO = new SkillsDefinicaoBO();
		usuarios = new ArrayList<>();
		responsaveis = new ArrayList<>();
		Usuario usuario = (Usuario) request.getSession().getAttribute("usuarioSessao");
		proxima = "main?acao=listaAluno";
		try {
			if( !usuario.getPerfilAcesso().equals(PerfilAcesso.ADMINISTRADOR) ) throw new NegocioException(MensagemContantes.MSG_INF_DENY);
			responsaveis = usuarioBO.listaUsuariosReponsaveis();
			request.setAttribute("responsaveis", responsaveis);
			usuarios = usuarioBO.listarUsuarioAlunos();
			request.setAttribute("usuarios", usuarios);
			skillsHards = skillsDefinicaoBO.listaskillsHard();
			request.setAttribute("skillsHards", skillsHards);
			skillsSofts = skillsDefinicaoBO.listaskillsSoft();
			request.setAttribute("skillsSofts", skillsSofts);
			proxima = "aluno/skills.jsp";
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("msgErro", e.getMessage());
		}
		return proxima;
	}

}
