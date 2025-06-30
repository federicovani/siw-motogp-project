package it.uniroma3.siw.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.uniroma3.siw.model.CampionatoCostruttori;
import it.uniroma3.siw.repository.CampionatoCostruttoriRepository;

@Service
public class CampionatoCostruttoriService {

    @Autowired
    private CampionatoCostruttoriRepository campionatoCostruttoriRepository;

    public Iterable<CampionatoCostruttori> getAllCampionati() {
        return campionatoCostruttoriRepository.findAll();
    }

    public CampionatoCostruttori getCampionatoById(Long id) {
        return campionatoCostruttoriRepository.findById(id).orElse(null);
    }
}
