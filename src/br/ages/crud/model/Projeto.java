package br.ages.crud.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;

/** 
 * @author Daniele Souza e Victor Diehl
 */

public class Projeto implements Serializable {
	
	private static final long serialVersionUID = 8366767996508020776L;
	private int idProjeto;
	private String nomeProjeto;
	private ArrayList<Equipe> equipes;
	private Status status;
	private String workspace;
	private ArrayList<Stakeholder> stakeholders;
	private Date dataInclusao;
	private Date dataInicio;
	private Date dataFim;
			
	public Projeto() {
		
	}
	
	public Projeto(int idProjeto, String nomeProjeto, ArrayList<Equipe> equipes, String workspace, Status status,
	ArrayList<Stakeholder> stakeholders, Date dataInicio, Date dataFim) {
		this.idProjeto = idProjeto;
		this.nomeProjeto = nomeProjeto;
		this.equipes = equipes;
		this.status = status;
		this.workspace = workspace;
		this.stakeholders = stakeholders;
		this.dataInclusao = new Date();
		this.dataInicio = dataInicio;
		this.dataFim = dataFim;
	}

	public int getIdProjeto() {
		return idProjeto;
	}
	
	public void setIdProjeto(int value){
		idProjeto = value;
	}
	
	public ArrayList<Stakeholder> getStakeholders() {
		return stakeholders;
	}
	
	public void setStakeholders(ArrayList<Stakeholder> stakeholders) {
		this.stakeholders = stakeholders;
	}
	
	public Status getStatus(){
		return status;
	}
	
	public void setStatus(Status status) {
		this.status = status;
	}
	
	public ArrayList<Equipe> getEquipe() {
		return  equipes; 
	}
	
	public void setEquipes(ArrayList<Equipe> equipes) {
		this.equipes = equipes;
	}
	
	public String getNomeProjeto() {
		return nomeProjeto;			
	}
	
	public void setNomeProjeto(String nomeProjeto) {
		this.nomeProjeto = nomeProjeto;
	}
	
	public Date getDataInicio() {
		return dataInicio;
	}
	
	public void setDataInicio(Date dataInicio) {
		this.dataInicio = dataInicio;
	}
	
	public Date getDataFim() {
		return dataFim;
	}
	
	public void setDataFim(Date dataFim) {
		this.dataFim = dataFim;
	}
	
	public String getWorkspace() {
		return workspace;
	}

	public void setWorkspace(String workspace) {
		this.workspace = workspace;
	}

	public Date getDataInclusao() {
		return dataInclusao;
	}
}