package it.uniroma3.siw.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import it.uniroma3.siw.service.PilotaService;
import it.uniroma3.siw.service.TeamService;
import it.uniroma3.siw.model.Team;
import it.uniroma3.siw.model.PilotaGP;

@Controller
public class PilotaController {
	
	@Autowired private PilotaService pilotaService;
	@Autowired private TeamService teamService;

	
	@GetMapping("/pilota/{id}")
	public String getPilota(@PathVariable("id") Long id, Model model) {
		model.addAttribute("pilota", this.pilotaService.getPilotaById(id));
		return "pilota.html";
	}
	
	@GetMapping("/pilotiETeam")
	public String mostraPilotiETeam(Model model) {
	    List<PilotaGP> piloti = pilotaService.getAllPiloti();
	    List<Team> teams = teamService.findAll();

	    model.addAttribute("piloti", piloti);
	    model.addAttribute("teams", teams);

	    return "pilotiETeam";
	}

}
