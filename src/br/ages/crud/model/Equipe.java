package br.ages.crud.model;

import java.io.Serializable;
import java.util.Date;
import java.util.ArrayList;

public class Equipe implements Serializable {
	
	private static final long serialVersionUID = -352815037257667825L;
	private int idEquipe;
	private String nomeEquipe;
	private String metodo;
	private String workspace;
	private ArrayList<Usuario> usuarios;
	private Date dataInclusao;
	
	public Equipe() {
		
	}
	
	public Equipe(int idEquipe, String nomeEquipe, String metodo,
			 		String workspace, ArrayList<Usuario> usuarios) {
		this.idEquipe = idEquipe;
		this.nomeEquipe = nomeEquipe;
		this.metodo = metodo;
		this.workspace = workspace;
		this.usuarios = usuarios;
		this.dataInclusao = new Date();
	}

	public int getIdEquipe() {
		return idEquipe;
	}

	public void setIdEquipe(int idEquipe) {
		this.idEquipe = idEquipe;
	}

	public String getNomeEquipe() {
		return nomeEquipe;
	}

	public void setNomeEquipe(String nomeEquipe) {
		this.nomeEquipe = nomeEquipe;
	}

	public String getMetodo() {
		return metodo;
	}

	public void setMetodo(String metodo) {
		this.metodo = metodo;
	}

	public String getWorkspace() {
		return workspace;
	}

	public void setWorkspace(String workspace) {
		this.workspace = workspace;
	}

	public ArrayList<Usuario> getUsuarios() {
		return usuarios;
	}

	public void setUsuarios(ArrayList<Usuario> usuarios) {
		this.usuarios = usuarios;
	}

	public Date getDataInclusao() {
		return dataInclusao;
	}
}
