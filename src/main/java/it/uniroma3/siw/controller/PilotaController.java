package it.uniroma3.siw.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import it.uniroma3.siw.model.User;
import it.uniroma3.siw.service.PilotaService;
import it.uniroma3.siw.sessionData.SessionData;

@Controller
public class PilotaController {
	
	@Autowired private PilotaService pilotaService;
	@Autowired private SessionData sessionData;
	
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
	
	@GetMapping("/admin/adminPilotiETeam")
	public String showAdminPilotiETeam(Model model) {
		User loggedUser = this.sessionData.getLoggedUser();
		model.addAttribute("user", loggedUser);
		model.addAttribute("piloti", this.pilotaService.getAllPiloti());
		return "admin/adminPilotiETeam";
	}

}
