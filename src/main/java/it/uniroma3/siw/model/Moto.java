package it.uniroma3.siw.model;

import java.util.Objects;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Moto {
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	private int cilindrata;
	private String motore;
	private int potenzaMax;
	private int velocitàMax;
	private String scarico;
	private int peso;
	
	// Getter e Setter
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	
	public int getCilindrata() {
		return cilindrata;
	}
	public void setCilindrata(int cilindrata) {
		this.cilindrata = cilindrata;
	}
	
	public String getMotore() {
		return motore;
	}
	public void setMotore(String motore) {
		this.motore = motore;
	}
	
	public int getPotenzaMax() {
		return potenzaMax;
	}
	public void setPotenzaMax(int potenzaMax) {
		this.potenzaMax = potenzaMax;
	}
	
	public int getVelocitaMax() {
		return velocitàMax;
	}
	public void setVelocitaMax(int velocitaMax) {
		this.velocitàMax = velocitaMax;
	}
	
	public String getScarico() {
		return scarico;
	}
	public void setScarico(String scarico) {
		this.scarico = scarico;
	}
	
	public int getPeso() {
		return peso;
	}
	public void setPeso(int peso) {
		this.peso = peso;
	}
	
	// Equals e hashCode
	@Override
	public int hashCode( ) {
		return Objects.hash(id, cilindrata, motore, potenzaMax, velocitàMax, scarico, peso);
	}
	
	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null)
			return false;
		if (getClass() != o.getClass())
			return false;
		Moto other = (Moto) o;
		return Objects.equals(id, other.id) && Objects.equals(cilindrata, other.cilindrata)
				&& Objects.equals(motore, other.motore) && Objects.equals(potenzaMax, other.potenzaMax)
				&& Objects.equals(velocitàMax, other.velocitàMax) && Objects.equals(scarico, other.scarico)
				&& Objects.equals(peso, other.peso);
	}
}
