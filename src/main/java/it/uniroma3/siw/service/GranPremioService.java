package it.uniroma3.siw.service;

import it.uniroma3.siw.model.Campionato;
import it.uniroma3.siw.model.Pilota;
import it.uniroma3.siw.model.PilotaGP;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.uniroma3.siw.model.GranPremio;
import it.uniroma3.siw.repository.GranPremioRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

@Service
public class GranPremioService {

	public static final Map<Integer, Integer> PUNTI_PER_POSIZIONE = Map.of(
			1, 25, 2, 18, 3, 15, 4, 12, 5, 10,
			6, 8, 7, 6, 8, 4, 9, 2, 10, 1
	);

	@Autowired
	GranPremioRepository granPremioRepository;
	@Autowired
	CampionatoService campionatoService;

	@Transactional
	public GranPremio getGranPremioById(Long id) {
		return granPremioRepository.findById(id).orElse(null);
	}

	@Transactional
	public List<GranPremio> getAllGranPremi() {
		return granPremioRepository.findAllByOrderByDataAsc();
	}

	// Recupera i gran premi per anno specifico
	@Transactional
	public List<GranPremio> getGranPremiByAnno(Integer anno) {
		return granPremioRepository.findByAnno(anno);
	}

	// Recupera gli anni unici disponibili dai gran premi
	@Transactional
	public List<Integer> getAnniDisponibili() {
		return granPremioRepository.findDistinctAnni();
	}

	@Transactional
	public Integer getUltimoAnnoDisponibile() {
		List<Integer> anniDisponibili = getAnniDisponibili();
		return anniDisponibili.isEmpty() ? null : anniDisponibili.get(anniDisponibili.size() - 1);
	}

	@Transactional
	public void save(GranPremio granPremio) {
		granPremioRepository.save(granPremio);

		Campionato campionato = campionatoService.getCampionatoByGranPremio(granPremio);
		if(campionato != null)
			campionatoService.aggiornaClassifica(campionato);
	}

}
