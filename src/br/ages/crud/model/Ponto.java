package br.ages.crud.model;

import java.io.Serializable;
import java.util.Date;

public class Ponto implements Serializable {

	private static final long serialVersionUID = 4692081871916050868L;
	private int idPonto;
	private Date dataEntrada;
	private Date dataSaida;
	private Usuario aluno;
	private Usuario responsavel;
	private StatusPonto status;

	public Ponto() {
		// TODO Auto-generated constructor stub
	}

	public Ponto(int idPonto, Date dataEntrada, Date dataSaida, Usuario aluno, Usuario responsavel, StatusPonto status) {
		super();
		this.idPonto = idPonto;
		this.dataEntrada = dataEntrada;
		this.dataSaida = dataSaida;
		this.aluno = aluno;
		this.responsavel = responsavel;
		this.setStatus(status);
	}

	public int getIdPonto() {
		return idPonto;
	}

	public void setIdPonto(int idPonto) {
		this.idPonto = idPonto;
	}

	public Date getDataEntrada() {
		return dataEntrada;
	}

	public void setDataEntrada(Date dataEntrada) {
		this.dataEntrada = dataEntrada;
	}

	public Date getDataSaida() {
		return dataSaida;
	}

	public void setDataSaida(Date dataSaida) {
		this.dataSaida = dataSaida;
	}

	public Usuario getAluno() {
		return aluno;
	}

	public void setAluno(Usuario aluno) {
		this.aluno = aluno;
	}

	public Usuario getResponsavel() {
		return responsavel;
	}

	public void setResponsavel(Usuario responsavel) {
		this.responsavel = responsavel;
	}

	public StatusPonto getStatus() {
		return status;
	}

	public void setStatus(StatusPonto status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return "Ponto [idPonto=" + idPonto + ", dataEntrada=" + dataEntrada + ", dataSaida=" + dataSaida + ", aluno=" + aluno + ", responsavel=" + responsavel + ", status=" + status + "]";
	}

}
