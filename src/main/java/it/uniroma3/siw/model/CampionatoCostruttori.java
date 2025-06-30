package it.uniroma3.siw.model;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import jakarta.persistence.CollectionTable;
import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;

@Entity
public class CampionatoCostruttori {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private int anno;

    @ElementCollection
    @CollectionTable(name = "campionato_costruttori_classifica",
                     joinColumns = @JoinColumn(name = "campionato_id"))
    @Column(name = "punti")
    private Map<String, Integer> classifica = new HashMap<>();

    // Costruttore vuoto obbligatorio
    public CampionatoCostruttori() {}

    // Getter e Setter
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }

    public int getAnno() {
        return anno;
    }
    public void setAnno(int anno) {
        this.anno = anno;
    }

    public Map<String, Integer> getClassifica() {
        return classifica;
    }
    public void setClassifica(Map<String, Integer> classifica) {
        this.classifica = classifica;
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
        if (obj == null || getClass() != obj.getClass())
            return false;
        CampionatoCostruttori other = (CampionatoCostruttori) obj;
        return anno == other.anno &&
               Objects.equals(classifica, other.classifica) &&
               Objects.equals(id, other.id);
    }
}
