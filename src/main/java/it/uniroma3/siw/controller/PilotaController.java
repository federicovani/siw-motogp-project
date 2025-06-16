package it.uniroma3.siw.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import it.uniroma3.siw.service.PilotaService;

@Controller
public class PilotaController {
	
	@Autowired PilotaService pilotaService;
	
	@GetMapping("/pilotiETeam")
	public String showPilotiETeam(Model model) {
		model.addAttribute("piloti", this.pilotaService.getAllPiloti());
		return "pilotiETeam.html";
	}
	
	@GetMapping("/pilota/{id}")
	public String getPilota(@PathVariable("id") Long id, Model model) {
		model.addAttribute("pilota", this.pilotaService.getPilotaById(id));
		return "pilota.html";
	}

}
