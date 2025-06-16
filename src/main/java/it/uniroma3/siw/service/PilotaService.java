package it.uniroma3.siw.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.uniroma3.siw.model.Pilota;
import it.uniroma3.siw.repository.PilotaRepository;

@Service
public class PilotaService {
	
	@Autowired PilotaRepository pilotaRepository;
	
	
	public Pilota getPilotaById(Long id) {
		return pilotaRepository.findById(id).orElse(null);
	}
	
	public Iterable<Pilota> getAllPiloti() {
		return pilotaRepository.findAll();
	}

}
