package it.uniroma3.siw.repository;

import org.springframework.data.repository.CrudRepository;

import it.uniroma3.siw.model.CampionatoPiloti;

import java.util.Optional;

public interface CampionatoPilotiRepository extends CrudRepository<CampionatoPiloti, Long>{
    void deleteAll();

    Optional<CampionatoPiloti> findByCampionatoIdAndPilotaId(Long campionatoId, Long pilotaId);

}
