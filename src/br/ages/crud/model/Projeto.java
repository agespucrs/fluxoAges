package br.ages.crud.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;

public class Projeto implements Serializable {

	private static final long serialVersionUID = 2L;
		private int idProjeto;
		private String nomeProjeto;
		private String workspace;
		private String status;
		private ArrayList<Stakeholders> stakeholders;
		private Date dataInclusao;
		private Date dataInicio;
		private Date dataFim;
		private ArrayList<Equipe> equipe;
		
		
		public Projeto() {
			// TODO Auto-generated constructor stub
		}
		
		public Projeto(int idProjeto, String nomeProjeto, String workspace,
		ArrayList<Stakeholders> stakeholders, String status, Date dataInicio, Date dataFim, ArrayList<Equipe> equipe) {
			super();
			this.idProjeto = idProjeto;
			this.nomeProjeto = nomeProjeto;
			this.workspace = workspace;
			this.stakeholders = stakeholders;
			this.status = status;
			this.dataInclusao = new Date();
			this.dataInicio = dataInicio;
			this.dataFim = dataFim;
			this.equipe = equipe;
		}



		public int getIdProjeto() {
			return idProjeto;
		}
		
		public void setIdProjeto(int value){
			idProjeto = value;
		}
		
		public ArrayList<Stakeholders> getStakeholders() {
		 return stakeholders;
		}
		
		public void setStakeholders(ArrayList stakeholders) {
			this.stakeholders = stakeholders;
		}
		
		public String getStatus(){
			return status;
		}
		
		public void setStatus(String status) {
			this.status = status;
		}
		
		public ArrayList<Equipe> getEquipe() {
			return  equipe; 
		}
		
		public void setEquipes(ArrayList equipes) {
			this.equipe = equipe;
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
