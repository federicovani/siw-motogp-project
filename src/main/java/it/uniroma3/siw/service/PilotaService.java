package it.uniroma3.siw.service;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.uniroma3.siw.model.Pilota;
import it.uniroma3.siw.repository.PilotaRepository;

@Service
public class PilotaService {

    @Autowired
    private PilotaRepository pilotaRepository;

    public Pilota getPilotaById(Long id) {
        return pilotaRepository.findById(id).orElse(null);
    }

    public List<Pilota> getAllPiloti() {
        List<Pilota> result = new ArrayList<>();
        pilotaRepository.findAll().forEach(result::add);
        return result;
    }
}
