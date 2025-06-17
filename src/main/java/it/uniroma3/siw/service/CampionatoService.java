package it.uniroma3.siw.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.uniroma3.siw.model.Campionato;
import it.uniroma3.siw.repository.CampionatoRepository;

@Service
public class CampionatoService {
	
	@Autowired CampionatoRepository campionatoRepository;
	
	public Campionato getCampionatoById(Long id) {
		return campionatoRepository.findById(id).orElse(null);
	}
	
	public Iterable<Campionato> getAllCampionati() {
		return campionatoRepository.findAll();
	}

}
