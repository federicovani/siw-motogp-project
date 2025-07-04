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

    @ManyToOne
    @JoinColumn(name = "campionato_id", nullable = false, unique = false) 
    private Campionato campionato;

    @ManyToOne
    @JoinColumn(name = "pilota_id", nullable = false, unique = false)
    private Pilota pilota;



    private int puntiTotali;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Campionato getCampionato() {
        return campionato;
    }

    public void setCampionato(Campionato campionato) {
        this.campionato = campionato;
    }

    public Pilota getPilota() {
        return pilota;
    }

    public void setPilota(Pilota pilota) {
        this.pilota = pilota;
    }

    public int getPuntiTotali() {
        return puntiTotali;
    }

    public void setPuntiTotali(int puntiTotali) {
        this.puntiTotali = puntiTotali;
    }

    public void aggiungiPunti(int punti) {
        this.puntiTotali += punti;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        CampionatoPiloti that = (CampionatoPiloti) o;
        return puntiTotali == that.puntiTotali && Objects.equals(id, that.id) && Objects.equals(campionato, that.campionato) && Objects.equals(pilota, that.pilota);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, campionato, pilota, puntiTotali);
    }
}
