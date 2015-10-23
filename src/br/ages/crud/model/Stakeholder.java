package br.ages.crud.model;

import java.io.Serializable;
import java.util.Date;

/** 
 * @author Daniele Souza e Victor Diehl
 */

public class Stakeholder implements Serializable {	
	
	private static final long serialVersionUID = 7731547546507235573L;
	private int idStakeholder;
	private String nomeStakeholder;
	private String descricao;
	private Date dataInclusao;	
	private Integer idUsuario; //Atributo criado para relacionar stakeholder com usuário, caso ele tenha acesso ao sistema
	
	public Stakeholder() {
		
	}	
	
	public Stakeholder(int idStakeholder, String nomeStakeholder,
			String descricao) {		
		this.idStakeholder = idStakeholder;
		this.nomeStakeholder = nomeStakeholder;
		this.descricao = descricao;
		this.dataInclusao = new Date();
		this.idUsuario = null;
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

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	
	public Date getDataInclusao() {
		return dataInclusao;
	}
	
	public void setDataInclusao(Date dataInclusao) {
		this.dataInclusao = dataInclusao;
	}
	
}
