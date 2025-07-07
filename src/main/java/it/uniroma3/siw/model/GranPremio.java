package it.uniroma3.siw.model;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.*;

@Entity
public class GranPremio {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private int numeroDiGiri;
    private LocalDate data;

    @ManyToOne
    private Circuito circuito;

    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(
            name = "gran_premio_sponsor", // Nome della tabella di join
            joinColumns = @JoinColumn(name = "gran_premi_id"), // Nome della colonna in questa entità
            inverseJoinColumns = @JoinColumn(name = "sponsor_id") // Nome della colonna nell'altra entità
    )
    private List<Sponsor> sponsor;

    @OneToMany(mappedBy = "granPremio", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<PilotaGP> risultati = new ArrayList<>();

    @ManyToMany
    private List<User> utentiVotanti;

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

    public void setId(Long id) {
        this.id = id;
    }

    public List<User> getUtentiVotanti() {
        return utentiVotanti;
    }

    public void setUtentiVotanti(List<User> utentiVotanti) {
        this.utentiVotanti = utentiVotanti;
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

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        GranPremio that = (GranPremio) o;
        return id == that.id && numeroDiGiri == that.numeroDiGiri && Objects.equals(data, that.data) && Objects.equals(circuito, that.circuito) && Objects.equals(sponsor, that.sponsor) && Objects.equals(risultati, that.risultati);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, numeroDiGiri, data, circuito, sponsor, risultati);
    }
}