package it.uniroma3.siw.model;

import jakarta.persistence.*;

import java.util.List;
import java.util.Objects;

@Entity
public class Team {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String nome;
    @OneToMany(mappedBy = "team")
    private List<Pilota> pilotiUfficiali;
    private String marcaMoto;
    private String immagine;
    @ManyToMany
    private List<Sponsor> sponsor;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public List<Pilota> getPilotiUfficiali() {
        return pilotiUfficiali;
    }

    public void setPilotiUfficiali(List<Pilota> pilotiUfficiali) {
        this.pilotiUfficiali = pilotiUfficiali;
    }

    public String getImmagine() {
        return immagine;
    }

    public void setImmagine(String immagine) {
        this.immagine = immagine;
    }

    public String getMarcaMoto() {
        return marcaMoto;
    }

    public void setMarcaMoto(String marcaMoto) {
        this.marcaMoto = marcaMoto;
    }

    public List<Sponsor> getSponsor() {
        return sponsor;
    }

    public void setSponsor(List<Sponsor> sponsor) {
        this.sponsor = sponsor;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Team team = (Team) o;
        return Objects.equals(id, team.id) && Objects.equals(nome, team.nome) && Objects.equals(pilotiUfficiali, team.pilotiUfficiali) && Objects.equals(immagine, team.immagine);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, nome, pilotiUfficiali, immagine);
    }
}

