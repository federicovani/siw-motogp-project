package it.uniroma3.siw.model;

import java.time.Duration;
import java.util.List;
import java.util.Objects;

import jakarta.annotation.Nullable;
import jakarta.persistence.*;

@Entity
@Table(name = "piloto_gp") // Nome della tabella di relazione
public class PilotaGP {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne // Molti PilotaGP per un Pilota
    @JoinColumn(name = "pilota_id", nullable = false)
    private Pilota pilota;

    @ManyToOne // Molti PilotaGP per un GranPremio
    @JoinColumn(name = "gran_premio_id", nullable = false)
    private GranPremio granPremio;

    private int posizione;

    @Column(nullable = false)
    private int voti = 0;

    // Costruttore vuoto
    public PilotaGP() {}

    // Costruttore personalizzato
    public PilotaGP(Pilota pilota, GranPremio granPremio, int posizione) {
        this.pilota = pilota;
        this.granPremio = granPremio;
        this.posizione = posizione;
        this.voti = 0;
    }

    // Getter e Setter
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Pilota getPilota() {
        return pilota;
    }

    public void setPilota(Pilota pilota) {
        this.pilota = pilota;
    }

    public GranPremio getGranPremio() {
        return granPremio;
    }

    public void setGranPremio(GranPremio granPremio) {
        this.granPremio = granPremio;
    }

    public int getPosizione() {
        return posizione;
    }

    public void setPosizione(int posizione) {
        this.posizione = posizione;
    }

    public int getVoti() {
        return voti;
    }

    public void setVoti(int voti) {
        this.voti = voti;
    }

    public void addVoto(){
        this.voti++;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, pilota, granPremio, posizione);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        PilotaGP other = (PilotaGP) obj;
        return Objects.equals(id, other.id)
                && Objects.equals(pilota, other.pilota)
                && Objects.equals(granPremio, other.granPremio)
                && posizione == other.posizione;
    }
}