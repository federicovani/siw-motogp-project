package it.uniroma3.siw.model;

import jakarta.persistence.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@Entity
public class CampionatoPiloti {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private int anno;

    @ElementCollection
    @CollectionTable(name = "classifica_piloti", joinColumns = @JoinColumn(name = "campionato_id"))
    @MapKeyJoinColumn(name = "pilota_id")
    @Column(name = "punti")
    private Map<Pilota, Integer> classifica = new HashMap<>();

    // Getter e Setter
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public int getAnno() { return anno; }
    public void setAnno(int anno) { this.anno = anno; }

    public Map<Pilota, Integer> getClassifica() { return classifica; }
    public void setClassifica(Map<Pilota, Integer> classifica) { this.classifica = classifica; }

    @Override
    public int hashCode() {
        return Objects.hash(id, anno, classifica);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        CampionatoPiloti other = (CampionatoPiloti) obj;
        return anno == other.anno && Objects.equals(id, other.id) && Objects.equals(classifica, other.classifica);
    }
}
