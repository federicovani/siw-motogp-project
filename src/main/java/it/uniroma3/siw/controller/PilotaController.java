package it.uniroma3.siw.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import it.uniroma3.siw.service.PilotaService;
import it.uniroma3.siw.service.TeamService;
import it.uniroma3.siw.model.Team;
import it.uniroma3.siw.model.Pilota;
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
	public String mostraPilotiETeam(Model model, Authentication authentication) {
	    List<Pilota> piloti = pilotaService.getAllPiloti();
	    List<Team> teams = teamService.findAll();

	    List<List<Team>> teamChunks = new ArrayList<>();
	    for (int i = 0; i < teams.size(); i += 4) {
	        teamChunks.add(teams.subList(i, Math.min(i + 4, teams.size())));
	    }

	    model.addAttribute("piloti", piloti);
	    model.addAttribute("teams", teams);
	    model.addAttribute("teamChunks", teamChunks);

	    if (authentication != null && authentication.getPrincipal() instanceof UserDetails) {
	        model.addAttribute("user", authentication.getPrincipal());
	    } else {
	        model.addAttribute("user", null);
	    }

	    return "pilotiETeam";
	}




}
