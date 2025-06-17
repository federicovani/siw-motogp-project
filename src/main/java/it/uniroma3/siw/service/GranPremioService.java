package it.uniroma3.siw.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.uniroma3.siw.model.GranPremio;
import it.uniroma3.siw.repository.GranPremioRepository;

@Service
public class GranPremioService {
	
	@Autowired GranPremioRepository granPremioRepository;
	
	public GranPremio getGranPremioById(Long id) {
		return granPremioRepository.findById(id).orElse(null);
	}
	
	public Iterable<GranPremio> getAllGranPremi() {
		return granPremioRepository.findAll();
	}

}
