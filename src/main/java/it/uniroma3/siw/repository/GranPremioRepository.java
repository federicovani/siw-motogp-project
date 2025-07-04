package it.uniroma3.siw.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

import it.uniroma3.siw.model.GranPremio;

public interface GranPremioRepository extends JpaRepository<GranPremio, Long> {
    List<GranPremio> findAllByOrderByDataAsc();
}