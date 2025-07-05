package it.uniroma3.siw.controller;

import it.uniroma3.siw.model.GranPremio;
import it.uniroma3.siw.model.Pilota;
import it.uniroma3.siw.model.PilotaGP;
import it.uniroma3.siw.service.PilotaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import it.uniroma3.siw.service.GranPremioService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.Duration;
import java.util.List;
import java.util.Map;

@Controller
public class GranPremioController {
	
	@Autowired
	GranPremioService granPremioService;
	@Autowired
	PilotaService pilotaService;

	@GetMapping("/granPremi")
	public String showGranPremi(@RequestParam(value = "anno", required = false) Integer anno, Model model) {
		if (anno != null) {
			model.addAttribute("granPremi", granPremioService.getGranPremiByAnno(anno));
		} else {
			model.addAttribute("granPremi", granPremioService.getGranPremiByAnno(granPremioService.getUltimoAnnoDisponibile()));
		}

		// Aggiungi anche gli anni unici disponibili per il selettore
		model.addAttribute("anniDisponibili", granPremioService.getAnniDisponibili());

		return "granPremi.html";
	}

	@GetMapping("/granPremio/{id}")
	public String getGranPremio(@PathVariable("id") Long id, Model model) {
		GranPremio granPremio = this.granPremioService.getGranPremioById(id);

		// Ordina i risultati: Prima le posizioni > 0, poi gli zeri
		granPremio.getRisultati().sort((r1, r2) -> {
			if (r1.getPosizione() == 0 && r2.getPosizione() != 0) {
				return 1; // r1 dopo r2
			} else if (r1.getPosizione() != 0 && r2.getPosizione() == 0) {
				return -1; // r1 prima di r2
			} else {
				return Integer.compare(r1.getPosizione(), r2.getPosizione()); // Ordina per posizione
			}
		});

		model.addAttribute("granPremio", granPremio);
		return "granPremio.html";
	}


	@GetMapping("/admin/formAddRisultatiGranPremio/{idGranPremio}")
	public String mostraFormAggiungiRisultati(@PathVariable("idGranPremio") Long idGranPremio, Model model) {
		model.addAttribute("granPremio", granPremioService.getGranPremioById(idGranPremio));
		model.addAttribute("piloti", pilotaService.getAllPiloti());
		model.addAttribute("risultato", new PilotaGP());
		return "admin/formAddRisultatiGranPremio.html";
	}

	@PostMapping("/admin/addRisultatiGranPremio/{idGranPremio}")
	public String aggiungiRisultati(
			@PathVariable("idGranPremio") Long idGranPremio,
			@RequestParam Map<String, String> posizioni, // Map con ID pilota (String) e posizione (String)
			Model model) {

		GranPremio granPremio = granPremioService.getGranPremioById(idGranPremio);

		// Elenca tutti i piloti
		List<Pilota> piloti = pilotaService.getAllPiloti();

		try {
			// Cancella risultati esistenti se presenti
			if (granPremio.getRisultati() != null && !granPremio.getRisultati().isEmpty()) {
				granPremio.getRisultati().clear();
			}

			for (Pilota pilota : piloti) { // Itera su tutti i piloti registrati
				String idPilotaStr = String.valueOf(pilota.getId());
				String posizioneStr = posizioni.get(idPilotaStr); // Ottieni la posizione come stringa

				Integer posizione;
				if (posizioneStr != null && !posizioneStr.isEmpty()) { // Se la posizione è specificata, convertila
					try {
						posizione = Integer.parseInt(posizioneStr);
					} catch (NumberFormatException e) {
						// Caso in cui il valore inserito non sia un numero valido
						posizione = 0; // Imposta il valore predefinito
					}
				} else {
					// Se il valore non è specificato, usa 0 come default
					posizione = 0;
				}

				PilotaGP pilotaGP = new PilotaGP();
				pilotaGP.setPilota(pilota);
				pilotaGP.setGranPremio(granPremio);
				pilotaGP.setPosizione(posizione);

				// Aggiungi il pilota al Gran Premio
				granPremio.addRisultato(pilotaGP);
			}

			// Salva tutti i risultati aggiornati
			granPremioService.save(granPremio);

			// Reindirizza alla pagina del Gran Premio
			return "redirect:/granPremio/" + idGranPremio;

		} catch (Exception e) {
			// Gestione dell'errore
			model.addAttribute("errorMessage", "Errore durante l'elaborazione dei dati: " + e.getMessage());
			model.addAttribute("granPremio", granPremio);
			model.addAttribute("piloti", piloti);
			return "admin/formAddRisultatiGranPremio.html";
		}
	}
	
	
	@GetMapping("/admin/formNewGranPremio") 
	public String formNewGranPremio(Model model) {
		model.addAttribute("granPremio", new GranPremio());
		return "admin/formNewGranPremio.html";
	}



}