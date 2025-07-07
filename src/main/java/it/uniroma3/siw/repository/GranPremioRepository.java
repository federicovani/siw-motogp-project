package it.uniroma3.siw.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

import it.uniroma3.siw.model.GranPremio;
import org.springframework.data.jpa.repository.Query;

public interface GranPremioRepository extends JpaRepository<GranPremio, Long> {
    List<GranPremio> findAllByOrderByDataAsc();

    // Recupera i gran premi filtrati per uno specifico anno
    @Query("SELECT gp FROM GranPremio gp WHERE YEAR(gp.data) = :anno ORDER BY gp.data ASC")
    List<GranPremio> findByAnno(Integer anno);

    // Recupera tutti gli anni distinti dai gran premi
    @Query("SELECT DISTINCT YEAR(gp.data) FROM GranPremio gp ORDER BY YEAR(gp.data) ASC")
    List<Integer> findDistinctAnni();

    // Recupera i Gran Premi futuri ordinati per data crescente
    List<GranPremio> findByDataAfterOrderByDataAsc(LocalDate data);

}