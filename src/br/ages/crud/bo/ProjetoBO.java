package br.ages.crud.bo;

import java.sql.SQLException;
import java.text.ParseException;
import java.util.List;

import br.ages.crud.dao.ProjetoDAO;
import br.ages.crud.exception.NegocioException;
import br.ages.crud.exception.PersistenciaException;
import br.ages.crud.model.Projeto;
//import br.ages.crud.model.Status;
import br.ages.crud.util.MensagemContantes;
import br.ages.crud.validator.DataValidator;

public class ProjetoBO {

	private ProjetoDAO projetoDAO;

	public ProjetoBO() {
		projetoDAO = new ProjetoDAO();
	}

	/**
	 * Cadastra Projeto em nï¿½vel de negï¿½cio, chamando o DAO
	 * 
	 * @param projeto
	 * @return
	 * @throws NegocioException
	 * @throws SQLException
	 * @throws ParseException
	 * @throws PersistenciaException
	 */
	public boolean cadastrarProjeto(Projeto project) throws SQLException, ParseException, NegocioException, PersistenciaException {
		boolean ok = false;
		ok = projetoDAO.cadastrarProjeto(project);
		if (ok == false)
			throw new NegocioException(MensagemContantes.MSG_ERR_CADASTRO_PROJETO);
		return ok;
	}

	public boolean validarProjeto(Projeto project) throws NegocioException {
		boolean valido = true;
		StringBuilder msg = new StringBuilder();
		msg.append(MensagemContantes.MSG_ERR_PROJETO_DADOS_INVALIDOS.concat("<br/>"));

			DataValidator validator = new DataValidator();

			if (project.getStatusProjeto() == null) {
				valido = false;
				msg.append(MensagemContantes.MSG_ERR_CAMPO_INVALIDO.replace("?", "Status ").concat("<br/>"));
			}
			if (project.getNomeProjeto() == null || project.getNomeProjeto().equals("")) {
				valido = false;
				msg.append(MensagemContantes.MSG_ERR_CAMPO_OBRIGATORIO.replace("?", "Nome do projeto ").concat("<br/>"));
			}
			if (project.getDataInicio() == null) {
				valido = false;
				msg.append(MensagemContantes.MSG_ERR_CAMPO_OBRIGATORIO.replace("?", "Data de início ").concat("<br/>"));
			}
			if (project.getDataFimPrevisto() == null) {
				valido = false;
				msg.append(MensagemContantes.MSG_ERR_CAMPO_OBRIGATORIO.replace("?", "Data de fim previsto ").concat("<br/>"));
			}
			if (!validator.maisCedoQue(project.getDataInicio(), project.getDataFimPrevisto())) {
				valido = false;
				msg.append(MensagemContantes.MSG_ERR_PROJETO_DATA_INCONSISTENTE.replace("?", " previsto").concat("<br/>"));
			}

			if (!valido) {
				throw new NegocioException(msg.toString());
			}
		return valido;
	}

	public void editarProjeto(Projeto project) throws NegocioException {

		try {
			projetoDAO.editarProjeto(project);
		} catch (PersistenciaException | SQLException | ParseException e) {
			e.printStackTrace();
			throw new NegocioException(e);
		}

	}

	public List<Projeto> listarProjeto() throws NegocioException {
		List<Projeto> listProject = null;

		try {
			listProject = projetoDAO.listarProjetos();
		} catch (PersistenciaException | SQLException e) {
			e.printStackTrace();
			throw new NegocioException(e);
		}

		return listProject;
	}

	public void removerProjeto(Projeto project) throws NegocioException {
		try {
			projetoDAO.removerProjeto(project);
		} catch (PersistenciaException e) {
			e.printStackTrace();
			throw new NegocioException(e);
		}
	}

	public Projeto buscarProjeto(int idProjeto) throws NegocioException {
		try {
			Projeto projeto = projetoDAO.consultarProjeto(idProjeto);
			return projeto;
		} catch (PersistenciaException | SQLException e) {
			e.printStackTrace();
			throw new NegocioException(e);
		}
	}

	public void setProjetoDAO(ProjetoDAO projetoDAO) {
		this.projetoDAO = projetoDAO;
	}

}