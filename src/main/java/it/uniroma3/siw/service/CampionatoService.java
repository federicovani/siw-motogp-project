package it.uniroma3.siw.service;

import it.uniroma3.siw.model.CampionatoPiloti;
import it.uniroma3.siw.model.GranPremio;
import it.uniroma3.siw.model.Pilota;
import it.uniroma3.siw.repository.CampionatoPilotiRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.uniroma3.siw.model.Campionato;
import it.uniroma3.siw.repository.CampionatoRepository;

import java.util.List;

@Service
public class CampionatoService {
	
	@Autowired
	CampionatoRepository campionatoRepository;
	@Autowired
	CampionatoPilotiRepository campionatoPilotiRepository;
    @Autowired
    private GranPremioService granPremioService;

	public Campionato getCampionatoById(Long id) {
		return campionatoRepository.findById(id).orElse(null);
	}
	
	public Iterable<Campionato> getAllCampionati() {
		return campionatoRepository.findAll();
	}

	@Transactional
	public void aggiornaClassifica(Campionato campionato, Pilota pilota, int punti) {
		CampionatoPiloti campionatoPiloti = campionato.getClassifica().stream()
				.filter(cp -> cp.getPilota().equals(pilota))
				.findFirst()
				.orElseGet(() -> {
					CampionatoPiloti nuovo = new CampionatoPiloti();
					nuovo.setPilota(pilota);
					nuovo.setCampionato(campionato);
					campionato.getClassifica().add(nuovo);
					return nuovo;
				});

		campionatoPiloti.aggiungiPunti(punti);

		// Salva l'aggiornamento
		campionatoPilotiRepository.save(campionatoPiloti);
		campionatoRepository.save(campionato);
	}


	public Campionato getCampionatoByAnno(int anno) {
		return campionatoRepository.findByAnno(anno);
	}

	public void start(Campionato campionato) {
		List<GranPremio> granPremi = granPremioService.getGranPremiByAnno(campionato.getAnno());
		campionato.setGranPremi(granPremi);
	}
}
