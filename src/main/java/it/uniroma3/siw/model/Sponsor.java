package it.uniroma3.siw.model;

import java.util.Objects;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Sponsor {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String nome;
    private String descrizione;
    private Contratto contratto;
    
    
    
    public Sponsor(int id, String nome, String descrizione, Contratto contratto) {
    	this.id = id;
    	this.nome = nome;
    	this.descrizione = descrizione;
    	this.setContratto(contratto);
    }
    
    
    
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	
	
	public String getDescrizione() {
		return descrizione;
	}
	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}
	

	
	
	public Contratto getContratto() {
		return contratto;
	}
	public void setContratto(Contratto contratto) {
		this.contratto = contratto;
	}
	
	
	
	
	@Override
	public int hashCode() {
		return Objects.hash(descrizione, id, nome);
	}
	
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Sponsor other = (Sponsor) obj;
		return Objects.equals(descrizione, other.descrizione) && id == other.id && Objects.equals(nome, other.nome);
	}

	
	
}
