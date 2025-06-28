package it.uniroma3.siw.model;

import jakarta.persistence.*;

import java.util.List;

@Entity
public class Team {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String nome;
    @OneToMany(mappedBy = "team")
    private List<Pilota> pilotiUfficiali;
    @OneToMany(mappedBy = "team")
    private List<Pilota> pilotiTester;

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

    public List<Pilota> getPilotiTester() {
        return pilotiTester;
    }

    public void setPilotiTester(List<Pilota> pilotiTester) {
        this.pilotiTester = pilotiTester;
    }
}

