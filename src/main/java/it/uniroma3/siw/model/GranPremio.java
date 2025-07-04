package it.uniroma3.siw.model;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.*;

@Entity
public class GranPremio {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private int numeroDiGiri;
    private LocalDate data;

    @ManyToOne
    private Circuito circuito;

    @ManyToMany
    private List<Sponsor> sponsor;

    @OneToMany(mappedBy = "granPremio", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<PilotaGP> risultati = new ArrayList<>();

    // Getter e Setter principali

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getNumeroDiGiri() {
        return numeroDiGiri;
    }

    public void setNumeroDiGiri(int numeroDiGiri) {
        this.numeroDiGiri = numeroDiGiri;
    }

    public LocalDate getData() {
        return data;
    }

    public void setData(LocalDate data) {
        this.data = data;
    }

    public Circuito getCircuito() {
        return circuito;
    }

    public void setCircuito(Circuito circuito) {
        this.circuito = circuito;
    }

    public List<Sponsor> getSponsor() {
        return sponsor;
    }

    public void setSponsor(List<Sponsor> sponsor) {
        this.sponsor = sponsor;
    }

    public List<PilotaGP> getRisultati() {
        return risultati;
    }

    public void setRisultati(List<PilotaGP> risultati) {
        this.risultati = risultati;
    }

    // Aggiunge un PilotaGP al Gran Premio
    public void addRisultato(PilotaGP pilotaGP) {
        risultati.add(pilotaGP);
        pilotaGP.setGranPremio(this);
    }

    // Rimuove un PilotaGP dal Gran Premio
    public void removeRisultato(PilotaGP pilotaGP) {
        risultati.remove(pilotaGP);
        pilotaGP.setGranPremio(null);
    }
}