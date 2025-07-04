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
	private int velocitaMax;
	private String scarico;
	private int peso;
	private int anno;
	private String costruttore;
	
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
	public String getCostruttore() {
		return costruttore;
	}
	public void setCostruttore(String costruttore) {
		this.costruttore = costruttore;
	}

	public int getVelocitaMax() {
		return velocitaMax;
	}

	public void setVelocitaMax(int velocitaMax) {
		this.velocitaMax = velocitaMax;
	}

	public int getAnno() {
		return anno;
	}

	public void setAnno(int anno) {
		this.anno = anno;
	}

	@Override
	public boolean equals(Object o) {
		if (o == null || getClass() != o.getClass()) return false;
		Moto moto = (Moto) o;
		return cilindrata == moto.cilindrata && potenzaMax == moto.potenzaMax && velocitaMax == moto.velocitaMax && peso == moto.peso && anno == moto.anno && Objects.equals(id, moto.id) && Objects.equals(motore, moto.motore) && Objects.equals(scarico, moto.scarico) && Objects.equals(costruttore, moto.costruttore);
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, cilindrata, motore, potenzaMax, velocitaMax, scarico, peso, anno, costruttore);
	}
}
