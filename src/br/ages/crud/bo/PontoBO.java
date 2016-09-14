package br.ages.crud.bo;

import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import br.ages.crud.dao.PontoDAO;
import br.ages.crud.exception.NegocioException;
import br.ages.crud.exception.PersistenciaException;
import br.ages.crud.model.Ponto;
import br.ages.crud.model.ResumoPonto;
import br.ages.crud.model.Stakeholder;
import br.ages.crud.model.StatusPonto;
import br.ages.crud.model.Usuario;
import br.ages.crud.util.MensagemContantes;

public class PontoBO {

	public PontoBO() {
	}

	private PontoDAO pontoDAO;

	public Boolean validaPonto(Ponto ponto) throws NegocioException, SQLException {

		if (ponto.getDataEntrada().getTime() > ponto.getDataSaida().getTime()) {
			throw new NegocioException(MensagemContantes.MSG_ERR_CADASTRO_PONTO_DATA_INVALIDA);
		}
		return true;

	}

	/**
	 * Cadastra Ponto do Aluno
	 * 
	 * @param Ponto
	 * @throws NegocioException
	 * @throws SQLException
	 * @throws PersistenciaException
	 * @throws ParseException
	 */
	public void cadastrarPonto(Ponto ponto) throws NegocioException, SQLException, PersistenciaException {
		pontoDAO = new PontoDAO();
		try {
			pontoDAO.cadastrarPonto(ponto);
		} catch (NegocioException e) {
			e.printStackTrace();
			throw new NegocioException(MensagemContantes.MSG_ERR_CADASTRO_PONTO);
		}
	}

	public StatusPonto validaStatusPonto(Usuario responsavel, String senhaResponsavel) throws PersistenciaException {
		UsuarioBO usuarioBO = new UsuarioBO();
		StatusPonto statusPonto;
		if (usuarioBO.validaUsuarioResponsavel(responsavel.getUsuario(), senhaResponsavel)) {
			statusPonto = StatusPonto.VALIDO;
		} else {
			statusPonto = StatusPonto.INVALIDO;
		}

		return statusPonto;
	}

	public ArrayList<ResumoPonto> listaPontoAlunos(int idUsuario) throws NegocioException {
		pontoDAO = new PontoDAO();
		ArrayList<ResumoPonto> listaPontos = new ArrayList<>();
		try {
			listaPontos = pontoDAO.listaPontoAlunos(idUsuario, StatusPonto.VALIDO);

		} catch (SQLException e) {

			e.printStackTrace();
		}
		return listaPontos;
	}

	public ArrayList<ResumoPonto> listaPontoInvalidoAlunos() throws NegocioException {
		pontoDAO = new PontoDAO();
		ArrayList<ResumoPonto> listaPontos = new ArrayList<>();
		try {
			listaPontos = pontoDAO.listaPontoInvalidoAlunos();

		} catch (SQLException e) {

			e.printStackTrace();
		}
		return listaPontos;
	}

	public String calculatotalHorasAluno(ArrayList<ResumoPonto> listaPontos) throws NumberFormatException, NegocioException {

		String totalHorasAluno;
		int total = 0;
		for (ResumoPonto time : listaPontos) {
				total += time.getHoraTotalDia();
		}

		totalHorasAluno = (total / 60 + ":" + total % 60);

		return totalHorasAluno;
	}

	public Ponto buscaPontoId(int idPonto) throws NegocioException {
		pontoDAO = new PontoDAO();
		try {
			Ponto ponto = pontoDAO.buscaPontoId(idPonto);

			return ponto;
		} catch (Exception e) {
			e.printStackTrace();
			throw new NegocioException(e);
		}
	}

	public void editaPonto(Ponto ponto) throws NegocioException {
		pontoDAO = new PontoDAO();
		try {
			pontoDAO.editaPonto(ponto);
		} catch (Exception e) {
			e.printStackTrace();
			throw new NegocioException(e);
		}
	}

	public List<Ponto> listarAlunos() {
		pontoDAO = new PontoDAO();
		ArrayList<Ponto> listaAlunos = new ArrayList<>();
		try {
			listaAlunos = pontoDAO.listaAlunos();
		} catch (Exception e) {

			e.printStackTrace();
		}
		return listaAlunos;
	}
}
