package it.uniroma3.siw.model;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import jakarta.persistence.Column;
import jakarta.persistence.CollectionTable;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.MapKeyJoinColumn;

@Entity
public class Campionato {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    
    @ElementCollection
    @CollectionTable(name = "campionato_classifica",
            joinColumns = @JoinColumn(name = "campionato_id"))
    @MapKeyJoinColumn(name = "pilota_id")
    @Column(name = "punti")
    private Map<Pilota, Integer> classifica = new HashMap<>();
    
    private int anno;
    
    // Getter e Setter
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	
	public Map<Pilota, Integer> getClassifica() {
		return classifica;
	}
	public void setClassifica(Map<Pilota, Integer> classifica) {
		this.classifica = classifica;
	}
	
	public int getAnno() {
		return anno;
	}
	public void setAnno(int anno) {
		this.anno = anno;
	}
	
	// Equals e hashCode
	@Override
	public int hashCode() {
		return Objects.hash(anno, classifica, id);
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Campionato other = (Campionato) obj;
		return anno == other.anno && Objects.equals(classifica, other.classifica) && Objects.equals(id, other.id);
	}
}