package br.ages.crud.command;

import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;

//import br.ages.crud.bo.SkillAlunoBO;
import br.ages.crud.bo.SkillsDefinicaoBO;
import br.ages.crud.bo.UsuarioBO;
import br.ages.crud.exception.NegocioException;
import br.ages.crud.exception.PersistenciaException;
import br.ages.crud.model.SkillAluno;
import br.ages.crud.model.SkillsDefinicao;
import br.ages.crud.model.Usuario;
import br.ages.crud.util.MensagemContantes;
import br.ages.crud.util.Util;

public class AddSkillCommand implements Command {

	private static Logger logger = Logger.getLogger("command.AddSkillCommand");
	private String proxima;
	private SkillsDefinicaoBO skillsDefinicaoBO;
	private SkillAluno skillAluno;
	private UsuarioBO alunoBO;
	private Usuario aluno;
	private UsuarioBO avaliadorBO;
	private Usuario avaliador;
	//private SkillAlunoBO skillAlunoBO;

	@Override
	public String execute(HttpServletRequest request) throws SQLException, NegocioException, ParseException, PersistenciaException {
		proxima = "main?acao=listaAluno";

		skillAluno = new SkillAluno();
		skillsDefinicaoBO = new SkillsDefinicaoBO();
		alunoBO = new UsuarioBO();
		aluno = new Usuario();
		avaliadorBO = new UsuarioBO();
		avaliador = new Usuario();
		//skillAlunoBO = new SkillAlunoBO();
		
		ArrayList<SkillsDefinicao> definicoes	 = new ArrayList<>(); 
		definicoes=(ArrayList<SkillsDefinicao>) skillsDefinicaoBO.listaskills();		

		String idAluno = request.getParameter("idAluno");
		String idAvaliador = request.getParameter("idResponsavel");
		String dataAvalicao = request.getParameter("dtAvaliacao");
		String senhaResponsavel = request.getParameter("senhaResponsavel");
		
		//Map<String, SkillAluno> skills = new HashMap<>();
		//varre os radio buttons pegando o valor de cada skill
		try {
			for (SkillsDefinicao s : definicoes) {
				aluno = alunoBO.buscaUsuarioId(Integer.valueOf(idAluno));
				skillAluno.setAluno(aluno);
				
				avaliador = avaliadorBO.buscaUsuarioId(Integer.valueOf(idAvaliador));
				skillAluno.setAvaliador(avaliador);
				
				skillAluno.setIdDefinicao(s.getIdSkillsDefinicao());
				String idSkillDefinicao = Integer.toString(s.getIdSkillsDefinicao());

				String v  = (String) (request.getParameter(idSkillDefinicao));

				Integer valor = Integer.valueOf(v);

				skillAluno.setValor(valor);
				
				skillAluno.setObservacao(request.getParameter("obs"+idSkillDefinicao));
				
				Date dtAvaliacao= Util.stringToDateTime(dataAvalicao);
				skillAluno.setDtValor(dtAvaliacao);
				
				//if(skillAlunoBO.confereAvaliador(avaliador, senhaResponsavel)){
				//	skillAlunoBO.cadastraAvaiacao(skillAluno);
				//	request.setAttribute("msgSucesso", MensagemContantes.MSG_SUC_AVALIACAO.replace("?", aluno.getNome()));
				//} else{
				//	proxima = "main?acao=skills"; //"aluno/skills.jsp";
				//	throw new NegocioException(" Senha de Avaliador Invalida");
				//}
				//logger.debug("Criado Avaliação: " + skillAluno);
				
				//skills.put(s.getNome(), skillAluno);
			}
/*
			
			Map<String, Integer> skillsX = new HashMap<>();
			//varre os radio buttons pegando o valor de cada skill
			try {
				for (SkillsDefinicao s : definicoes) {
					String idSkillDefinicao = Integer.toString(s.getIdSkillsDefinicao());
					String v  = (String) (request.getParameter(idSkillDefinicao).equals("S/A") ? "0" : request.getParameter(idSkillDefinicao));
					Integer valor = Integer.valueOf(v);
					skillsX.put(s.getNome(), valor);
				}
			
		
			
			
			Set<String> keys = skillsX.keySet();
			for (String k: keys) {
				System.out.println(k + " -- " + skillsX.get(k));
			}
			
*/			
			} catch (Exception e) {
				request.setAttribute("msgErro", e.getMessage() + MensagemContantes.MSG_ERR_AVALIACAO);
		}
		
		return proxima;
	}
}
