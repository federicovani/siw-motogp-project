package it.uniroma3.siw.service;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.uniroma3.siw.model.Pilota;
import it.uniroma3.siw.model.PilotaGP;
import it.uniroma3.siw.repository.PilotaRepository;
import it.uniroma3.siw.repository.PilotaGPRepository;

@Service
public class PilotaService {

    @Autowired
    private PilotaRepository pilotaRepository;

    @Autowired
    private PilotaGPRepository pilotaGPRepository;

    public Pilota getPilotaById(Long id) {
        return pilotaRepository.findById(id).orElse(null);
    }

    public List<PilotaGP> getAllPiloti() {
        List<PilotaGP> result = new ArrayList<>();
        pilotaGPRepository.findAll().forEach(result::add);
        return result;
    }
}