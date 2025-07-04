package it.uniroma3.siw.repository;

import it.uniroma3.siw.model.GranPremio;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import it.uniroma3.siw.model.Campionato;

import java.util.List;

public interface CampionatoRepository extends CrudRepository<Campionato, Long>{
    Campionato findByAnno(Integer anno);
}
