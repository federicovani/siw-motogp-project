package it.uniroma3.siw.model;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.Objects;

@Entity
@Table(name = "pilota")
public class Pilota {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String nome;
    private String cognome;
    @Column(name = "numero_identificativo", unique = true, nullable = false)
    private int numeroIdentificativo;
    private java.time.LocalDate dataDiNascita;
    private String nazionalita;
    private int peso;
    private int altezza;
    @ManyToOne
    private Team team;
    private String immagine;

    public int getAltezza() {
        return altezza;
    }

    public void setAltezza(int altezza) {
        this.altezza = altezza;
    }

    public int getPeso() {
        return peso;
    }

    public void setPeso(int peso) {
        this.peso = peso;
    }

    public String getNazionalita() {
        return nazionalita;
    }

    public void setNazionalita(String nazionalita) {
        this.nazionalita = nazionalita;
    }

    public LocalDate getDataDiNascita() {
        return dataDiNascita;
    }

    public void setDataDiNascita(LocalDate dataDiNascita) {
        this.dataDiNascita = dataDiNascita;
    }

    public int getNumeroIdentificativo() {
        return numeroIdentificativo;
    }

    public void setNumeroIdentificativo(int numeroIdentificativo) {
        this.numeroIdentificativo = numeroIdentificativo;
    }

    public String getCognome() {
        return cognome;
    }

    public void setCognome(String cognome) {
        this.cognome = cognome;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

	public Team getTeam() {
		return team;
	}

	public void setTeam(Team team) {
		this.team = team;
	}

    public String getImmagine() {
        return immagine;
    }

    public void setImmagine(String immagine) {
        this.immagine = immagine;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Pilota pilota = (Pilota) o;
        return numeroIdentificativo == pilota.numeroIdentificativo && peso == pilota.peso && altezza == pilota.altezza && Objects.equals(id, pilota.id) && Objects.equals(nome, pilota.nome) && Objects.equals(cognome, pilota.cognome) && Objects.equals(dataDiNascita, pilota.dataDiNascita) && Objects.equals(nazionalita, pilota.nazionalita) && Objects.equals(team, pilota.team) && Objects.equals(immagine, pilota.immagine);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, nome, cognome, numeroIdentificativo, dataDiNascita, nazionalita, peso, altezza, team, immagine);
    }
}
