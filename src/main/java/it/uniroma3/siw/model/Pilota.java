package it.uniroma3.siw.model;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
public class Pilota {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private String nome;
    private String cognome;
    private int numeroIdentificativo;
    private String nazionalita;
    private int peso;
    private int altezza;

    @ManyToOne
    private Team team;

    @OneToMany(mappedBy = "pilota", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<PilotaGP> partecipazioni = new ArrayList<>();

    private String immagine;

    // Getter e Setter principali

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCognome() {
        return cognome;
    }

    public void setCognome(String cognome) {
        this.cognome = cognome;
    }

    public Team getTeam() {
        return team;
    }

    public void setTeam(Team team) {
        this.team = team;
    }

    public List<PilotaGP> getPartecipazioni() {
        return partecipazioni;
    }

    public void setPartecipazioni(List<PilotaGP> partecipazioni) {
        this.partecipazioni = partecipazioni;
    }

    // Aggiunge una partecipazione
    public void addPartecipazione(PilotaGP pilotaGP) {
        partecipazioni.add(pilotaGP);
        pilotaGP.setPilota(this);
    }

    // Rimuove una partecipazione
    public void removePartecipazione(PilotaGP pilotaGP) {
        partecipazioni.remove(pilotaGP);
        pilotaGP.setPilota(null);
    }

    public String getImmagine() {
        return immagine;
    }

    public void setImmagine(String immagine) {
        this.immagine = immagine;
    }

    public int getNumeroIdentificativo() {
        return numeroIdentificativo;
    }

    public void setNumeroIdentificativo(int numeroIdentificativo) {
        this.numeroIdentificativo = numeroIdentificativo;
    }

    public String getNazionalita() {
        return nazionalita;
    }

    public void setNazionalita(String nazionalita) {
        this.nazionalita = nazionalita;
    }

    public int getPeso() {
        return peso;
    }

    public void setPeso(int peso) {
        this.peso = peso;
    }

    public int getAltezza() {
        return altezza;
    }

    public void setAltezza(int altezza) {
        this.altezza = altezza;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Pilota pilota = (Pilota) o;
        return id == pilota.id && numeroIdentificativo == pilota.numeroIdentificativo && peso == pilota.peso && altezza == pilota.altezza && Objects.equals(nome, pilota.nome) && Objects.equals(cognome, pilota.cognome) && Objects.equals(nazionalita, pilota.nazionalita) && Objects.equals(team, pilota.team) && Objects.equals(partecipazioni, pilota.partecipazioni) && Objects.equals(immagine, pilota.immagine);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, nome, cognome, numeroIdentificativo, nazionalita, peso, altezza, team, partecipazioni, immagine);
    }
}