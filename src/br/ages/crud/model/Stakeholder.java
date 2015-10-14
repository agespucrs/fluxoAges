package br.ages.crud.model;

import java.io.Serializable;

public class Stakeholder implements Serializable {

	private static final long serialVersionUID = 2L;
	private int idStakeholder;
	private String nomeStakeholder;
	private String descricao;
	
	public Stakeholder() {
		
	}
	
	public int getIdStakeholder() {
		return this.idStakeholder;
	}
	
	public void setIdStakeholder(int idStakeholder) {
		this.idStakeholder = idStakeholder;
	}
	
	
}
