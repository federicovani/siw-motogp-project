package it.uniroma3.siw.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
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

}
