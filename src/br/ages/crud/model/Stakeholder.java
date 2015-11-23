package br.ages.crud.model;

import java.io.Serializable;
import java.util.Date;

/** 
 * @author Daniele Souza e Victor Diehl
 */

public class Stakeholder implements Serializable, Comparable<Stakeholder>  {	
	
	private static final long serialVersionUID = 7731547546507235573L;
	private int idStakeholder;
	private String nomeStakeholder;	
	private Date dataInclusao;		
	
	public Stakeholder() {
		
	}	
	
	public Stakeholder(int idStakeholder) {
		this.idStakeholder = idStakeholder;
	}	
	
	public Stakeholder(int idStakeholder, String nomeStakeholder) {		
		this.idStakeholder = idStakeholder;
		this.nomeStakeholder = nomeStakeholder;
		this.dataInclusao = new Date();
	}

	public int getIdStakeholder() {
		return this.idStakeholder;
	}
	
	public void setIdStakeholder(int idStakeholder) {
		this.idStakeholder = idStakeholder;
	}

	public String getNomeStakeholder() {
		return nomeStakeholder;
	}

	public void setNomeStakeholder(String nomeStakeholder) {
		this.nomeStakeholder = nomeStakeholder;
	}
	
	public Date getDataInclusao() {
		return dataInclusao;
	}
	
	public void setDataInclusao(Date dataInclusao) {
		this.dataInclusao = dataInclusao;
	}

	@Override
	public int compareTo(Stakeholder stakeholder) {
		return this.getNomeStakeholder().compareToIgnoreCase(stakeholder.getNomeStakeholder());
	}
	
}
