package it.uniroma3.siw.model;

import java.util.List;
import java.util.Objects;

import jakarta.persistence.*;

@Entity
public class Sponsor {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String nome;
    private String descrizione;
    @ManyToMany(mappedBy = "sponsor")
    private List<GranPremio> granPremi;
    
    public Sponsor() {
    	
    }
    
    public Sponsor(int id, String nome, String descrizione) {
    	this.id = id;
    	this.nome = nome;
    	this.descrizione = descrizione;
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

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}

	public List<GranPremio> getGranPremi() {
		return granPremi;
	}
	public void setGranPremi(List<GranPremio> granPremi) {
		this.granPremi = granPremi;
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
