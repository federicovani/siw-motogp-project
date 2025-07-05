package it.uniroma3.siw.repository;

import aj.org.objectweb.asm.commons.Remapper;
import it.uniroma3.siw.model.GranPremio;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import it.uniroma3.siw.model.Campionato;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CampionatoRepository extends CrudRepository<Campionato, Long>{
    Campionato findByAnno(Integer anno);

    // Metodo per cercare il campionato contenente il gran premio specificato
    @Query("SELECT c FROM Campionato c JOIN c.granPremi gp WHERE gp = :granPremio")
    Campionato findByGranPremiContains(@Param("granPremio") GranPremio granPremio);

    Campionato findTopByOrderByAnnoDesc();

    @Query("SELECT DISTINCT c.anno FROM Campionato c ORDER BY c.anno ASC")
    List<Integer> findAllDistinctAnni();

}
