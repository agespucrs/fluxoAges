package br.ages.crud.model;

import java.io.Serializable;
import java.util.Date;

public class Status implements Serializable {

	private static final long serialVersionUID = 1400568506422884529L;
	private int idStatus;
	private String nomeStatus;
	private String descricao;
	private Date dataInclusao;

	public Status() {
		
	}
	
	public Status(int idStatus, String nomeStatus, String descricao) {
		this.idStatus = idStatus;
		this.nomeStatus = nomeStatus;
		this.descricao = descricao;
		this.dataInclusao = new Date();
	}

	public int getIdStatus() {
		return idStatus;
	}

	public void setIdStatus(int idStatus) {
		this.idStatus = idStatus;
	}

	public String getNomeStatus() {
		return nomeStatus;
	}

	public void setNomeStatus(String nomeStatus) {
		this.nomeStatus = nomeStatus;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public Date getDataInclusao() {
		return dataInclusao;
	}
			
}
