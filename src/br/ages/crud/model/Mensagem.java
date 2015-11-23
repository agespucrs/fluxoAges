package br.ages.crud.model;

import java.util.HashSet;
import java.util.Set;



public class Mensagem {
	//private TipoMsg tipomsg;
	private Usuario emissor;
	private Set<Usuario> receptores;
	private String assunto;
	private String conteudo;
	
	public Mensagem(Usuario emissor) {
		this.emissor = emissor;
		assunto = "";
		conteudo = "";
		receptores = new HashSet<Usuario>();
	}

	public Set<Usuario> getReceptores() {
		return receptores;
	}

	public void setReceptores(Set<Usuario> receptores) {
		this.receptores = receptores;
	}

	public String getAssunto() {
		return assunto;
	}

	public void setAssunto(String assunto) {
		this.assunto = assunto;
	}

	public String getConteudo() {
		return conteudo;
	}

	public void setConteudo(String conteudo) {
		this.conteudo = conteudo;
	}
	
	public boolean adicionarReceptor(Usuario receptor){
		return receptores.add(receptor);
	}
	
	public boolean removerReceptor(Usuario receptor){
		return receptores.remove(receptor);
	}
	
}
