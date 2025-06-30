package it.uniroma3.siw.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.uniroma3.siw.model.CampionatoPiloti;
import it.uniroma3.siw.repository.CampionatoPilotiRepository;

@Service
public class CampionatoPilotiService {

    @Autowired
    private CampionatoPilotiRepository campionatoPilotiRepository;

    public Iterable<CampionatoPiloti> getAllCampionati() {
        return campionatoPilotiRepository.findAll();
    }

    public CampionatoPiloti getCampionatoById(Long id) {
        return campionatoPilotiRepository.findById(id).orElse(null);
    }
}
