package br.ages.crud.bo;

import br.ages.crud.dao.SkillAlunoDAO;
import br.ages.crud.exception.NegocioException;
import br.ages.crud.exception.PersistenciaException;
import br.ages.crud.model.SkillAluno;
import br.ages.crud.model.StatusPonto;
import br.ages.crud.model.Usuario;
import br.ages.crud.util.MensagemContantes;

public class SkillAlunoBO {
	SkillAlunoDAO skillAlunoDAO;

	public boolean confereAvaliador(Usuario avaliador, String senhaResponsavel) throws PersistenciaException {
		UsuarioBO usuarioBO = new UsuarioBO();
		boolean resultado = usuarioBO.validaUsuarioResponsavel(avaliador.getUsuario(), senhaResponsavel) ? true : false;
		
		return resultado;
	}

	public void cadastraAvaliacao(SkillAluno skillAluno) {
		skillAlunoDAO = new SkillAlunoDAO();
		try {
			if (!skillAlunoDAO.cadastraAvaliacao(skillAluno)) {
				throw new NegocioException(MensagemContantes.MSG_ERR_AVALIACAO);
			}
			;

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
