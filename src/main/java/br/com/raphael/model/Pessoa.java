package br.com.raphael.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.br.CPF;

@Entity
@Table(name="pessoa")
public class Pessoa implements Serializable {

	private static final long serialVersionUID = -6413396964895720932L;
	
	@Id
	@GeneratedValue
	private long id;
	
	@Column(length=200, nullable=false)
	@NotBlank(message="O campo nome é obrigatório.")
	@NotNull(message="O campo nome é obrigatório.")
	private String nome;
	
	@Column(length=200, nullable=false, name="sobre_nome")
	@NotBlank(message="O campo sobre nome é obrigatório.")
	@NotNull(message="O campo sobre nome é obrigatório.")
	private String sobreNome;
	
	@CPF
	@NotBlank(message="O campo cpf é obrigatório.")
	@NotNull(message="O campo cpf é obrigatório.")
	private String cpf;
	
	@Email
	@NotBlank(message="O campo email é obrigatório.")
	@NotNull(message="O campo email é obrigatório.")
	private String email;
	
	@NotBlank(message="O campo celular é necessário.")
	@NotNull(message="O campo celular é necessário.")
	private String celular;
	
	@NotBlank(message="O campo login é necessário.")
	@NotNull(message="O campo login é necessário.")
	private String login;
	
	@NotBlank(message="O campo senha é necessário.")
	@NotNull(message="O campo senha é necessário.")
	private String senha;
	
	private boolean disponivel;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getSobreNome() {
		return sobreNome;
	}

	public void setSobreNome(String sobreNome) {
		this.sobreNome = sobreNome;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getCelular() {
		return celular;
	}

	public void setCelular(String celular) {
		this.celular = celular;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public boolean isDisponivel() {
		return disponivel;
	}

	public void setDisponivel(boolean disponivel) {
		this.disponivel = disponivel;
	}

}
