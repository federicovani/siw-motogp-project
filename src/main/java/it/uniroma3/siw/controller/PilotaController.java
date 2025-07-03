package it.uniroma3.siw.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import it.uniroma3.siw.service.PilotaService;
import it.uniroma3.siw.service.TeamService;
import it.uniroma3.siw.model.Team;
import it.uniroma3.siw.model.Pilota;
import it.uniroma3.siw.model.PilotaGP;
import org.springframework.web.multipart.MultipartFile;

@Controller
public class PilotaController {
	
	@Autowired private PilotaService pilotaService;
	@Autowired private TeamService teamService;

	
	@GetMapping("/pilota/{id}")
	public String getPilota(@PathVariable("id") Long id, Model model) {
		Pilota compagnoDiSquadra = pilotaService.getCompagnoDiSquadra(id);
		model.addAttribute("pilota", pilotaService.getPilotaById(id));
		model.addAttribute("compagnoDiSquadra", compagnoDiSquadra);
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

	@GetMapping("/admin/formNewPilota")
	public String formNewPilota(Model model) {
		model.addAttribute("pilota", new Pilota());
		return "admin/formNewPilota.html";
	}

	@PostMapping("/admin/formNewPilota")
	public String salvaPilota(@ModelAttribute("pilota") Pilota pilota, @RequestParam("file") MultipartFile file, Model model) {

		if (!pilotaService.existsByNomeAndCognome(pilota.getNome(), pilota.getCognome())) {
			if (file != null && !file.isEmpty())
				pilotaService.saveImmagine(pilota, file);
			pilotaService.save(pilota);
			model.addAttribute("pilota", pilota);
			return "redirect:/pilota/" + pilota.getId();
		} else {
			model.addAttribute("messaggioErrore", "Questo pilota esiste già");
			return "admin/formNewPilota.html";
		}
	}

	@GetMapping("/admin/deletePilota/{pilotaId}")
	public String deletePilota(@PathVariable("pilotaId") Long pilotaId, Model model) {
		pilotaService.deleteById(pilotaId);
		return "redirect:/pilotiETeam";
	}





}
