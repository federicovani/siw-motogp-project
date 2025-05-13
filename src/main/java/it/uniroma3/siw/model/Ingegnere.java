package it.uniroma3.siw.model;

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
}
