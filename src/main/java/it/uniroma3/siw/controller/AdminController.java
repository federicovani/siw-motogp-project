package it.uniroma3.siw.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import it.uniroma3.siw.service.PilotaService;
import it.uniroma3.siw.sessionData.SessionData;

@Controller
public class AdminController {
	
	@Autowired private SessionData sessionData;
	@Autowired private PilotaService pilotaService;
	
	
	@GetMapping("/adminHomepage") 
	public String getAdminHomepage() {
		return "admin/adminHomepage";
	}
	
	@GetMapping("/adminPilotiETeam")
	public String showAdminPilotiETeam(Model model) {
		model.addAttribute("piloti", this.pilotaService.getAllPiloti());
		return "admin/adminPilotiETeam.html";
	}

}
