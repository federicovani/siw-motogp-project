package it.uniroma3.siw.model;

import java.util.Objects;

public class Ingegnere {
	private String nome;
	private String cognome;
	private java.time.LocalDate dataDiNascita;
	private String nazionalita;
	
	
	
	
	
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	
	
	public String getCognome() {
		return cognome;
	}
	public void setCognome(String cognome) {
		this.cognome = cognome;
	}
	
	
	public java.time.LocalDate getDataDiNascita() {
		return dataDiNascita;
	}
	public void setDataDiNascita(java.time.LocalDate dataDiNascita) {
		this.dataDiNascita = dataDiNascita;
	}
	
	
	public String getNazionalita() {
		return nazionalita;
	}
	public void setNazionalita(String nazionalita) {
		this.nazionalita = nazionalita;
	}
	
	
	
	
	@Override
	public int hashCode() {
		return Objects.hash(cognome, dataDiNascita, nazionalita, nome);
	}
	
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Ingegnere other = (Ingegnere) obj;
		return Objects.equals(cognome, other.cognome) && Objects.equals(dataDiNascita, other.dataDiNascita)
				&& Objects.equals(nazionalita, other.nazionalita) && Objects.equals(nome, other.nome);
	}
	
	
}
