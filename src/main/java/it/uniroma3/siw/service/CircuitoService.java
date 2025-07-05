package it.uniroma3.siw.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.uniroma3.siw.model.Circuito;
import it.uniroma3.siw.repository.CircuitoRepository;

@Service
public class CircuitoService {
	
	@Autowired CircuitoRepository circuitoRepository;
	
	
	public Circuito getCircuitoById(Long id) {
		return circuitoRepository.findById(id).orElse(null);
	}
	
	public List<Circuito> getAllCircuiti() {
		List<Circuito> result = new ArrayList<>();
		circuitoRepository.findAll().forEach(result::add);
		return result;
	}

}
