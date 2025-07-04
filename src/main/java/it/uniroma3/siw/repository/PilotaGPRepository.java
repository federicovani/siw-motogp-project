package it.uniroma3.siw.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import it.uniroma3.siw.model.PilotaGP;

import java.util.List;

@Repository
public interface PilotaGPRepository extends CrudRepository<PilotaGP, Long> {
    List<PilotaGP> findByGranPremio_Id(Long granPremioId);
    List<PilotaGP> findByPilota_Id(Long pilotaId);
}
