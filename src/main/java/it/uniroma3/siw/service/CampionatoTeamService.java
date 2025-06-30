package it.uniroma3.siw.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.uniroma3.siw.model.CampionatoTeam;
import it.uniroma3.siw.repository.CampionatoTeamRepository;

@Service
public class CampionatoTeamService {

    @Autowired
    private CampionatoTeamRepository campionatoTeamRepository;

    public Iterable<CampionatoTeam> getAllCampionati() {
        return campionatoTeamRepository.findAll();
    }

    public CampionatoTeam getCampionatoById(Long id) {
        return campionatoTeamRepository.findById(id).orElse(null);
    }
}
