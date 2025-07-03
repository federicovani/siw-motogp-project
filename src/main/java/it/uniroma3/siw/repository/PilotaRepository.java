package it.uniroma3.siw.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import it.uniroma3.siw.model.Pilota;

@Repository
public interface PilotaRepository extends CrudRepository<Pilota, Long>{

    boolean existsByNomeAndCognomeIgnoreCase(String nome, String cognome);
}
