package it.uniroma3.siw.model;

import java.time.Duration;
import java.util.Objects;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "pilotagp")
public class PilotaGP {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private int posizione;
    private Duration migliorTempo;

    // Costruttore vuoto
    public PilotaGP() {}

    // Getter e setter per ID
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getPosizione() {
        return posizione;
    }

    public void setPosizione(int posizione) {
        this.posizione = posizione;
    }

    public Duration getMigliorTempo() {
        return migliorTempo;
    }

    public void setMigliorTempo(Duration migliorTempo) {
        this.migliorTempo = migliorTempo;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, migliorTempo, posizione);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null) return false;
        if (getClass() != obj.getClass()) return false;
        PilotaGP other = (PilotaGP) obj;
        return Objects.equals(id, other.id)
                && Objects.equals(migliorTempo, other.migliorTempo)
                && posizione == other.posizione;
    }
}
