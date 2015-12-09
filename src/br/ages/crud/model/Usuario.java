package br.ages.crud.model;

import java.io.Serializable;
import java.util.Date;

/**
 * Entity Usuario - Data Transfer Objeto
 * 
 * @author 3- Victor Diehl, Daniele de Souza.
 *
 */
public class Usuario implements Serializable, Comparable<Usuario> {

	private static final long serialVersionUID = 2717027327683138959L;
	private int idUsuario;
	private String usuario;
	private String senha;
	private TipoUsuario tipoUsuario;
	private PerfilAcesso perfilAcesso;
	private StatusUsuario statusUsuario;
	private String matricula;
	private String nome;
	private String email;
	private Date dataInclusao;

	public Usuario() {
		// TODO Auto-generated constructor stub
		this.dataInclusao = new Date();
	}

	public Usuario(int idUsuario) {
		this.idUsuario = idUsuario;
		this.dataInclusao = new Date();
	}

	public Usuario(String usuario, String senha, String matricula, String nome, String email, StatusUsuario statusUsuario, TipoUsuario tipoUsuario, PerfilAcesso perfilAcesso) {
		super();
		this.usuario = usuario;
		this.senha = senha;
		this.matricula = matricula;
		this.nome = nome;
		this.email = email;
		this.statusUsuario = statusUsuario;
		this.perfilAcesso = perfilAcesso;
		this.tipoUsuario = tipoUsuario;
		this.dataInclusao = new Date();
	}

	public Usuario(String usuario, String senha) {
		this.usuario = usuario;
		this.senha = senha;
	}

	public int getIdUsuario() {
		return idUsuario;
	}

	public void setIdUsuario(int value) {
		idUsuario = value;
	}

	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String value) {
		usuario = value;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String value) {
		senha = value;
	}

	public TipoUsuario getTipoUsuario() {
		return tipoUsuario;
	}

	public void setTipoUsuario(TipoUsuario tipoUsuario) {
		this.tipoUsuario = tipoUsuario;
	}

	public StatusUsuario getStatusUsuario() {
		return statusUsuario;
	}

	public void setStatusUsuario(StatusUsuario statusUsuario) {
		this.statusUsuario = statusUsuario;
	}

	public String getMatricula() {
		return matricula;
	}

	public void setMatricula(String value) {
		matricula = value;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String value) {
		nome = value;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String value) {
		email = value;
	}

	public Date getDataInclusao() {
		return dataInclusao;
	}

	public PerfilAcesso getPerfilAcesso() {
		return perfilAcesso;
	}

	public void setPerfilAcesso(PerfilAcesso perfilAcesso) {
		this.perfilAcesso = perfilAcesso;
	}

	@Override
	public int compareTo(Usuario usuario) {
		return this.getNome().compareToIgnoreCase(usuario.getNome());
	}

}
