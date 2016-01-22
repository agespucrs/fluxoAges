package br.ages.crud.model;

import java.io.Serializable;
import java.util.Date;

public class ResumoPonto implements Serializable {

	private static final long serialVersionUID = -8690624546267273422L;
	private int idPonto;
	private String nomeAluno;
	private Date dataEtrada;
	private Date horaTotalDia;

	public ResumoPonto() {
	}
	
	
	public int getIdPonto() {
		return idPonto;
	}

	public ResumoPonto(int idPonto, String nomeAluno, Date dataEtrada, Date horaTotalDia) {
		super();
		this.idPonto = idPonto;
		this.nomeAluno = nomeAluno;
		this.dataEtrada = dataEtrada;
		this.horaTotalDia = horaTotalDia;
	}


	public void setIdPonto(int idPonto) {
		this.idPonto = idPonto;
	}

	public String getNomeAluno() {
		return nomeAluno;
	}

	public void setNomeAluno(String nomeAluno) {
		this.nomeAluno = nomeAluno;
	}

	public Date getDataEtrada() {
		return dataEtrada;
	}

	public void setDataEtrada(Date dataEtrada) {
		this.dataEtrada = dataEtrada;
	}

	public Date getHoraTotalDia() {
		return horaTotalDia;
	}

	public void setHoraTotalDia(Date horaEntrada) {
		this.horaTotalDia = horaEntrada;
	}

}
