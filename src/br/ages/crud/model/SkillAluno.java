package br.ages.crud.model;

import java.io.Serializable;
import java.util.Date;

public class SkillAluno implements Serializable{

	private static final long serialVersionUID = 5942272714490163834L;
	private int idSkill;
	private int idDefinicao;
	private int valor;
	private Date dtValor;
	private Usuario aluno;
	private Usuario avaliador;
	private String observacao;
	
	public SkillAluno() {}

	
	public int getIdSkill() {
		return idSkill;
	}


	public void setIdSkill(int idSkill) {
		this.idSkill = idSkill;
	}


	public int getIdDefinicao() {
		return idDefinicao;
	}


	public void setIdDefinicao(int idDefinicao) {
		this.idDefinicao = idDefinicao;
	}


	public int getValor() {
		return valor;
	}


	public void setValor(int valor) {
		this.valor = valor;
	}


	public Date getDtValor() {
		return dtValor;
	}


	public void setDtValor(Date dtValor) {
		this.dtValor = dtValor;
	}


	public Usuario getAluno() {
		return aluno;
	}


	public void setAluno(Usuario aluno) {
		this.aluno = aluno;
	}


	public Usuario getAvaliador() {
		return avaliador;
	}


	public void setAvaliador(Usuario avaliador) {
		this.avaliador = avaliador;
	}


	public String getObservacao() {
		return observacao;
	}


	public void setObservacao(String observacao) {
		this.observacao = observacao;
	}


	@Override
	public String toString() {
		return "SkillAluno [idSkill=" + idSkill + ", idDefinicao=" + idDefinicao + ", valor=" + valor + ", dtValor=" + dtValor + ", aluno=" + aluno + ", avaliador=" + avaliador + ", observacao="
				+ observacao + "]";
	}
	
	
}
