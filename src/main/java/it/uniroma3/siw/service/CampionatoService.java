package it.uniroma3.siw.service;

import it.uniroma3.siw.model.*;
import it.uniroma3.siw.repository.CampionatoPilotiRepository;
import it.uniroma3.siw.repository.GranPremioRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.uniroma3.siw.repository.CampionatoRepository;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class CampionatoService {
	
	@Autowired
	CampionatoRepository campionatoRepository;
	@Autowired
	CampionatoPilotiRepository campionatoPilotiRepository;
    @Autowired
    private GranPremioRepository granPremioRepository;

	public Campionato getCampionatoById(Long id) {
		return campionatoRepository.findById(id).orElse(null);
	}
	
	public Iterable<Campionato> getAllCampionati() {
		return campionatoRepository.findAll();
	}

	@Transactional
	public int getUltimoAnnoDisponibile() {
		Campionato campionato = campionatoRepository.findTopByOrderByAnnoDesc();
		if (campionato == null) {
			throw new IllegalStateException("Nessun anno disponibile nei campionati.");
		}
		return campionato.getAnno();
	}

	@Transactional
	public List<Integer> getAnniDisponibili() {
		return campionatoRepository.findAllDistinctAnni();
	}

	@Transactional
	public Campionato getCampionatoByGranPremio(GranPremio granPremio) {
		if (granPremio == null) {
			throw new IllegalArgumentException("Il Gran Premio fornito è nullo!");
		}
		return campionatoRepository.findByGranPremiContains(granPremio);
	}

	@Transactional
	public void aggiornaClassifica(Campionato campionato, Pilota pilota, int punti) {
		if (campionato == null) {
			throw new IllegalArgumentException("Il campionato non può essere nullo.");
		}
		if (pilota == null) {
			throw new IllegalArgumentException("Il pilota non può essere nullo.");
		}

		CampionatoPiloti campionatoPiloti = campionato.getClassifica().stream()
				.filter(cp -> cp.getPilota().equals(pilota))
				.findFirst()
				.orElseGet(() -> {
					CampionatoPiloti nuovo = new CampionatoPiloti(campionato, pilota);
					campionato.getClassifica().add(nuovo);
					return nuovo;

				});

		campionatoPiloti.aggiungiPunti(punti);

		campionatoRepository.save(campionato);
		campionatoPilotiRepository.save(campionatoPiloti);
	}


	public Campionato getCampionatoByAnno(int anno) {
		return campionatoRepository.findByAnno(anno);
	}


	@Transactional
	public void creaCampionato(int anno, List<Long> granPremiIds) {
		// Crea un nuovo campionato
		Campionato campionato = new Campionato();
		campionato.setAnno(anno);

		// Recupera i gran premi selezionati e associa al campionato
		List<GranPremio> granPremiSelezionati = new java.util.ArrayList<>();
		for(Long id : granPremiIds) {
			GranPremio gp = granPremioRepository.findById(id).orElse(null);
			granPremiSelezionati.add(gp);
		}
		campionato.setGranPremi(granPremiSelezionati);

		// Salva il campionato
		campionatoRepository.save(campionato);
	}

	public Map<Team, Integer> calcolaClassificaTeam(Campionato campionato) {
		if (campionato == null || campionato.getClassifica() == null) {
			throw new IllegalArgumentException("Il campionato o la classifica non possono essere nulli.");
		}

		// Mappa per accumulare i punti totali per ogni team
		Map<Team, Integer> classificaTeam = new HashMap<>();

		// Itera attraverso la classifica piloti
		for (CampionatoPiloti campionatoPiloti : campionato.getClassifica()) {
			Pilota pilota = campionatoPiloti.getPilota();
			Team team = pilota.getTeam();

			if (team != null) {
				// Somma i punti del pilota ai punti del team
				classificaTeam.put(team, classificaTeam.getOrDefault(team, 0) + campionatoPiloti.getPuntiTotali());
			}
		}

		// Ordina i team in base al punteggio totale in ordine decrescente
		return classificaTeam.entrySet()
				.stream()
				.sorted(Map.Entry.<Team, Integer>comparingByValue().reversed())
				.collect(Collectors.toMap(
						Map.Entry::getKey,
						Map.Entry::getValue,
						(e1, e2) -> e1,
						() -> new LinkedHashMap<>()
				));

	}
}
