package br.ages.crud.bo;

import br.ages.crud.exception.PersistenciaException;
import br.ages.crud.model.SkillAluno;
import br.ages.crud.model.StatusPonto;
import br.ages.crud.model.Usuario;

public class SkillAlunoBO {

	public boolean confereAvaliador(Usuario avaliador, String senhaResponsavel) throws PersistenciaException {
		UsuarioBO usuarioBO = new UsuarioBO();
		boolean resultado;
		if (usuarioBO.validaUsuarioResponsavel(avaliador.getUsuario(), senhaResponsavel)) {
			resultado = true;
		} else {
			resultado = false;
		}

		return resultado ;
	}

	public void cadastraAvaiacao(SkillAluno skillAluno) {
		// TODO Auto-generated method stub
		
	}

}
