package it.uniroma3.siw.controller;

import it.uniroma3.siw.model.Team;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import it.uniroma3.siw.model.Circuito;
import it.uniroma3.siw.service.CircuitoService;

@Controller
public class CircuitoController {
	@Autowired CircuitoService circuitoService;
	
	@GetMapping("/admin/formNewCircuito")
	public String formNewCircuito(Model model) {
		model.addAttribute("circuito", new Circuito());
		return "admin/formNewCircuito.html";
	}
	
	@PostMapping("/admin/formNewCircuito")
	public String salvaCircuito(@ModelAttribute("circuito") Circuito circuito, 
			@RequestParam("file") MultipartFile file, Model model) {
		
		//Gestione immagini
		if (file != null && !file.isEmpty()) {
			circuitoService.saveImmagine(circuito, file);
		}
		
		circuitoService.save(circuito);
		return "redirect:/admin/formNewGranPremio";
	}

	@GetMapping("/admin/formUpdateCircuito/{id}")
	public String formUpdateCircuito(@PathVariable("id") Long id, Model model) {
		Circuito circuito = circuitoService.getCircuitoById(id);
		if(circuito != null)
			model.addAttribute("circuito", circuito);
		else {
			model.addAttribute("messaggioErrore", "Circuito non trovato.");
			return "redirect:/admin/formNewGranPremio";
		}
		return "admin/formUpdateCircuito.html";
	}

	@PostMapping("/admin/formUpdateCircuito")
	public String modificaCircuito(@ModelAttribute("circuito") Circuito circuito,
								   @RequestParam("file") MultipartFile file, Model model) {

		// Controlla l'esistenza del team in base all'ID
		Circuito circuitoEsistente = circuitoService.getCircuitoById(circuito.getId());

		if(circuitoEsistente != null) {
			// Aggiorna i dati del circuito
			circuitoEsistente.setLunghezza(circuito.getLunghezza());
			circuitoEsistente.setLarghezza(circuito.getLarghezza());
			circuitoEsistente.setRettilineo(circuito.getRettilineo());
			circuitoEsistente.setCurveDx(circuito.getCurveDx());
			circuitoEsistente.setCurveSx(circuito.getCurveSx());
			circuitoEsistente.setCitta(circuito.getCitta());
			circuitoEsistente.setPaese(circuito.getPaese());

			// Aggiorna l'immagine, se presente
			if (file != null && !file.isEmpty()) {
				circuitoService.saveImmagine(circuitoEsistente, file);
			}

			// Salva il circuito aggiornato
			circuitoService.save(circuitoEsistente);
			return "redirect:/admin/formNewGranPremio";

		} else {
			// Gestione caso circuito non trovato
			model.addAttribute("messaggioErrore", "Circuito non trovato.");
			return "redirect:/admin/formNewGranPremio";
		}
	}

}
