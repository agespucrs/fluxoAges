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
	private ArrayList<Usuario> usuarios;
	private StatusProjeto status;
	private String workspace;
	private ArrayList<Stakeholder> stakeholders;
	private Date dataInclusao;
	private Date dataInicio;
	private Date dataFim;
	private Date dataFimPrevisto;
			
	public Projeto() {
		this.dataInclusao = new Date();
	}
	
	public Projeto(int idProjeto, String nome, StatusProjeto status, Date dataInicio, Date dataFimPrevisto) {
		this.idProjeto = idProjeto;
		this.nomeProjeto = nomeProjeto;		
		this.status = status;
		this.dataInicio = dataInicio;		
		this.dataFimPrevisto = dataFimPrevisto;
	}
	
	public Projeto(int idProjeto, String nomeProjeto, ArrayList<Usuario> usuarios, String workspace, StatusProjeto status,
	ArrayList<Stakeholder> stakeholders, Date dataInicio, Date dataFim, Date dataFimPrevisto) {
		this.idProjeto = idProjeto;
		this.nomeProjeto = nomeProjeto;
		this.usuarios = usuarios;
		this.status = status;
		this.workspace = workspace;
		this.stakeholders = stakeholders;
		this.dataInclusao = new Date();
		this.dataInicio = dataInicio;
		this.dataFim = dataFim;
		this.dataFim = dataFimPrevisto;
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
	
	public StatusProjeto getStatusProjeto(){
		return status;
	}
	
	public void setStatusProjeto(StatusProjeto status) {
		this.status = status;
	}
	
	public ArrayList<Usuario> getUsuarios() {
		return  usuarios; 
	}
	
	public void setUsuarios(ArrayList<Usuario> usuarios) {
		this.usuarios = usuarios;
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
	
	public Date getDataFimPrevisto() {
		return dataFimPrevisto;
	}
	
	public void setDataFimPrevisto(Date dataFimPrevisto) {
		this.dataFimPrevisto = dataFimPrevisto;
	}
}