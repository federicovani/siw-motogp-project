package it.uniroma3.siw.repository;

import it.uniroma3.siw.model.Sponsor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SponsorRepository extends JpaRepository<Sponsor, Long> {
    boolean existsByNomeIgnoreCase(String nome);
}

