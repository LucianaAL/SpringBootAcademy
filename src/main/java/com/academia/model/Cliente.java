package com.academia.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import org.hibernate.validator.constraints.NotEmpty;

@SuppressWarnings("deprecation")
@Entity
public class Cliente implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy= GenerationType.AUTO)
	private long matricula;
	
	@NotEmpty
	private String nome;
	@NotEmpty
	private String cpf;
	private String telefone;
	
	@ManyToOne
	private Academia academia;
	
	@OneToMany
	private List<FichaExercicio> fichaExercicio;
	
	@OneToMany
	private List<FichaMedica> fichaMedica;
	
	
	public List<FichaMedica> getFichaMedica() {
		return fichaMedica;
	}
	public void setFichaMedica(List<FichaMedica> fichaMedica) {
		this.fichaMedica = fichaMedica;
	}
	public List<FichaExercicio> getFichaExercicio() {
		return fichaExercicio;
	}
	public void setFichaExercicio(List<FichaExercicio> fichaExercicio) {
		this.fichaExercicio = fichaExercicio;
	}
	public Academia getAcademia() {
		return academia;
	}
	public void setAcademia(Academia academia) {
		this.academia = academia;
	}
	public long getMatricula() {
		return matricula;
	}
	public void setMatricula(long matricula) {
		this.matricula = matricula;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getCpf() {
		return cpf;
	}
	public void setCpf(String cpf) {
		this.cpf = cpf;
	}
	public String getTelefone() {
		return telefone;
	}
	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}
}
