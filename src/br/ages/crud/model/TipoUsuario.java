package br.ages.crud.model;

import java.io.Serializable;
import java.util.Date;

/** 
 * @author Daniele Souza e Victor Diehl
 */

public class TipoUsuario implements Serializable {

	private static final long serialVersionUID = -4196928909021033322L;
	private int idTipoUsuario;
	private String tipoUsuario;
	private String descricao;
	private Date dataInclusao;
	
	public TipoUsuario() {
		
	}
	
	public TipoUsuario(int idTipoUsuario, String tipoUsuario, String descricao) {
		this.idTipoUsuario = idTipoUsuario;
		this.tipoUsuario = tipoUsuario;
		this.descricao = descricao;
		this.dataInclusao = new Date();
	}

	public int getIdTipoUsuario() {
		return idTipoUsuario;
	}

	public void setIdTipoUsuario(int idTipoUsuario) {
		this.idTipoUsuario = idTipoUsuario;
	}

	public String getTipoUsuario() {
		return tipoUsuario;
	}

	public void setTipoUsuario(String tipoUsuario) {
		this.tipoUsuario = tipoUsuario;
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
