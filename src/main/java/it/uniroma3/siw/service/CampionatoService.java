package it.uniroma3.siw.service;

import it.uniroma3.siw.model.*;
import it.uniroma3.siw.repository.CampionatoPilotiRepository;
import it.uniroma3.siw.repository.GranPremioRepository;
import it.uniroma3.siw.repository.PilotaRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.uniroma3.siw.repository.CampionatoRepository;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class CampionatoService {
	
	@Autowired
	CampionatoRepository campionatoRepository;
	@Autowired
	CampionatoPilotiRepository campionatoPilotiRepository;
    @Autowired
    private GranPremioRepository granPremioRepository;
	@Autowired
	private PilotaGPService pilotaGPService;
	@Autowired
	private PilotaRepository pilotaRepository;

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
	public void removeGranPremioFromCampionato(GranPremio granPremio) {
		Campionato campionato = getCampionatoByGranPremio(granPremio);
		if(campionato != null) {
			campionato.getGranPremi().remove(granPremio);
			campionatoRepository.save(campionato);
		}
		aggiornaClassifica(campionato);
	}

	@Transactional
	public void aggiornaPilotaInClassifica(Campionato campionato, Pilota pilota, int punti) {
		if (campionato == null || pilota == null) {
			throw new IllegalArgumentException("Campionato e Pilota non possono essere nulli.");
		}

		Optional<CampionatoPiloti> esistente = campionatoPilotiRepository
				.findByCampionatoIdAndPilotaId(campionato.getId(), pilota.getId());

		CampionatoPiloti campionatoPiloti = esistente.orElseGet(() -> {
			CampionatoPiloti nuovo = new CampionatoPiloti(campionato, pilota);
			campionatoPilotiRepository.save(nuovo);
			return nuovo;
		});


		campionatoPiloti.aggiungiPunti(punti);

		campionatoRepository.save(campionato);
		campionatoPilotiRepository.save(campionatoPiloti);
	}


	public Campionato getCampionatoByAnno(Integer anno) {
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
			if(id != null) {
				GranPremio gp = granPremioRepository.findById(id).orElse(null);
				granPremiSelezionati.add(gp);
			}
		}
		campionato.setGranPremi(granPremiSelezionati);

		// Salva il campionato
		campionatoRepository.save(campionato);

		//Aggiorna classifica
		aggiornaClassifica(campionato);
	}

	@Transactional
	public void aggiornaCampionato(Campionato campionato, List<Long> granPremiIds) {
		// Recupera i gran premi selezionati e associa al campionato
		List<GranPremio> granPremiSelezionati = new java.util.ArrayList<>();

		for(Long id : granPremiIds) {
			if(id != null) {
				GranPremio gp = granPremioRepository.findById(id).orElse(null);
				granPremiSelezionati.add(gp);
			}
		}
		campionato.setGranPremi(granPremiSelezionati);

		// Salva il campionato
		campionatoRepository.save(campionato);

		//Aggiorna anche la classifica
		aggiornaClassifica(campionato);
	}

	@Transactional
	public void aggiornaClassifica(Campionato campionato) {
		resetClassifica(campionato);

		List<GranPremio> granPremiCampionato = new ArrayList<>(campionato.getGranPremi());

		for(GranPremio gp : granPremiCampionato) {
			if(gp.getRisultati() != null) {
				for (PilotaGP risultato : gp.getRisultati()) {
					int posizione = risultato.getPosizione();
					Pilota pilota = risultato.getPilota();

					int punti = 0;
					if (GranPremioService.PUNTI_PER_POSIZIONE.containsKey(posizione))
						punti = GranPremioService.PUNTI_PER_POSIZIONE.get(posizione);
					// Utilizza il CampionatoService per aggiornare la classifica
					aggiornaPilotaInClassifica(campionato, pilota, punti);
				}
			}
		}
		campionatoRepository.save(campionato);
	}

	@Transactional
	public void resetClassifica(Campionato campionato) {
		if (campionato == null) {
			throw new IllegalArgumentException("Il campionato non può essere null.");
		}

		// Rimuovere tutte le entità della classifica
		List<CampionatoPiloti> classifica = campionato.getClassifica();
		if (classifica != null) {
			classifica.clear(); // Hibernate gestirà automaticamente gli orfani
		}


		// Salva il campionato aggiornato
		campionatoRepository.save(campionato);
	}


	@Transactional
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

	@Transactional
	public Map<String, Integer> calcolaClassificaCostruttori(Campionato campionato) {
		if (campionato == null || campionato.getClassifica() == null) {
			throw new IllegalArgumentException("Il campionato o la classifica non possono essere nulli.");
		}

		// Mappa per accumulare i punti totali per ogni costruttore
		Map<String, Integer> classificaCostruttori = new HashMap<>();

		// Itera attraverso la classifica piloti
		for (CampionatoPiloti campionatoPiloti : campionato.getClassifica()) {
			Pilota pilota = campionatoPiloti.getPilota();
			Team team = pilota.getTeam();

			if (team != null && team.getMarcaMoto() != null) {
				// Somma i punti del pilota ai punti del costruttore
				classificaCostruttori.put(team.getMarcaMoto(), classificaCostruttori.getOrDefault(team.getMarcaMoto(), 0) + campionatoPiloti.getPuntiTotali());
			}
		}

		// Ordina i costruttori in base al punteggio totale in ordine decrescente
		return classificaCostruttori.entrySet()
				.stream()
				.sorted(Map.Entry.<String, Integer>comparingByValue().reversed())
				.collect(Collectors.toMap(
						Map.Entry::getKey,
						Map.Entry::getValue,
						(e1, e2) -> e1,
						() -> new LinkedHashMap<>()
				));

	}

	public int getVittoriePilotaInCampionato(Long idPilota){

		Pilota pilota = pilotaRepository.findById(idPilota).orElse(null);

		int anno = java.time.Year.now().getValue();
		Campionato campionato = getCampionatoByAnno(anno);

		if (pilota == null || campionato == null)
			throw new IllegalArgumentException("Pilota o campionato non possono essere null.");

		int vittorie = 0;

		for(GranPremio gp : campionato.getGranPremi()){
			if(pilotaGPService.getPosizionePilota(gp, pilota) == 1)
				vittorie++;
		}
		return vittorie;
	}

	public int getPodiPilotaInCampionato(Long idPilota){

		Pilota pilota = pilotaRepository.findById(idPilota).orElse(null);

		int anno = java.time.Year.now().getValue();
		Campionato campionato = getCampionatoByAnno(anno);

		if (pilota == null || campionato == null)
			throw new IllegalArgumentException("Pilota o campionato non possono essere null.");

		int podi = 0;

		for(GranPremio gp : campionato.getGranPremi()){
			if(pilotaGPService.getPosizionePilota(gp, pilota) > 0 && pilotaGPService.getPosizionePilota(gp, pilota) < 4)
				podi++;
		}
		return podi;
	}

}
