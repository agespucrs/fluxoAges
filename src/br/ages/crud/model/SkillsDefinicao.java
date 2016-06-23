package br.ages.crud.model;

import java.io.Serializable;

public class SkillsDefinicao implements Serializable {

	private static final long serialVersionUID = 698943541910415664L;
	private int idSkillsDefinicao;
	private TipoSkills  tipoSkills;
	private String nome;
	private String descricao;
	
	public SkillsDefinicao() {}

	public int getIdSkillsDefinicao() {
		return idSkillsDefinicao;
	}

	public void setIdSkillsDefinicao(int idSkillsDefinicao) {
		this.idSkillsDefinicao = idSkillsDefinicao;
	}

	public TipoSkills getTipoSkills() {
		return tipoSkills;
	}

	public void setTipoSkills(TipoSkills tipoSkills) {
		this.tipoSkills = tipoSkills;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	@Override
	public String toString() {
		return "SkillsDefinicao [idSkillsDefinicao=" + idSkillsDefinicao + ", tipoSkills=" + tipoSkills + ", nome=" + nome + ", descricao=" + descricao + "]";
	}
	
}
