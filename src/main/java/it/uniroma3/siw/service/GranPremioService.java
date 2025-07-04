package it.uniroma3.siw.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.uniroma3.siw.model.GranPremio;
import it.uniroma3.siw.repository.GranPremioRepository;

import java.util.List;

@Service
public class GranPremioService {
	
	@Autowired GranPremioRepository granPremioRepository;
	
	public GranPremio getGranPremioById(Long id) {
		return granPremioRepository.findById(id).orElse(null);
	}
	
	public List<GranPremio> getAllGranPremi() {
		return granPremioRepository.findAllByOrderByDataAsc();
	}

	// Recupera i gran premi per anno specifico
	public List<GranPremio> getGranPremiByAnno(Integer anno) {
		return granPremioRepository.findByAnno(anno);
	}

	// Recupera gli anni unici disponibili dai gran premi
	public List<Integer> getAnniDisponibili() {
		return granPremioRepository.findDistinctAnni();
	}

	public Integer getUltimoAnnoDisponibile() {
		List<Integer> anniDisponibili = getAnniDisponibili();
		return anniDisponibili.isEmpty() ? null : anniDisponibili.get(anniDisponibili.size() - 1);
	}
}
