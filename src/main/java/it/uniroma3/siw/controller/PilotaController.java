package it.uniroma3.siw.controller;

import java.util.ArrayList;
import java.util.List;

import it.uniroma3.siw.model.Sponsor;
import it.uniroma3.siw.service.SponsorService;
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
	@Autowired private SponsorService sponsorService;

	
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
		model.addAttribute("sponsors", sponsorService.getAllSponsors());
		return "admin/formNewPilota.html";
	}

	@PostMapping("/admin/formNewPilota")
	public String salvaPilota(@ModelAttribute("pilota") Pilota pilota, @RequestParam("file") MultipartFile file, @RequestParam(name = "sponsor", required = false) List<Long> sponsorIds, Model model) {

		if (!pilotaService.existsByNomeAndCognome(pilota.getNome(), pilota.getCognome())) {
			if (file != null && !file.isEmpty())
				pilotaService.saveImmagine(pilota, file);
			if(sponsorIds != null && !sponsorIds.isEmpty()) {
				List<Sponsor> sponsors = sponsorService.findAllById(sponsorIds);
				pilota.setSponsor(sponsors);
			}
			pilotaService.save(pilota);
			model.addAttribute("pilota", pilota);
			return "redirect:/pilota/" + pilota.getId();
		} else {
			model.addAttribute("sponsors", sponsorService.getAllSponsors());
			model.addAttribute("messaggioErrore", "Questo pilota esiste già");
			return "/admin/formNewPilota.html";
		}
	}

	@GetMapping("/admin/deletePilota/{pilotaId}")
	public String deletePilota(@PathVariable("pilotaId") Long pilotaId, Model model) {
		pilotaService.deleteById(pilotaId);
		return "redirect:/pilotiETeam";
	}

	@GetMapping("/admin/formUpdatePilota/{id}")
	public String formEditPilota(@PathVariable("id") Long id, Model model) {
		Pilota pilota = pilotaService.getPilotaById(id);

		if (pilota != null) {
			model.addAttribute("pilota", pilota);
			model.addAttribute("sponsors", sponsorService.getAllSponsors());
			return "admin/formUpdatePilota.html";
		} else {
			model.addAttribute("messaggioErrore", "Pilota non trovato.");
			return "redirect:/pilotiETeam";
		}
	}

	@PostMapping("/admin/formUpdatePilota")
	public String updatePilota(@ModelAttribute("pilota") Pilota pilota, @RequestParam("file") MultipartFile file, @RequestParam(name = "sponsor", required = false) List<Long> sponsorIds, Model model) {
		// Controlla l'esistenza del pilota in base all'ID
		Pilota pilotaEsistente = pilotaService.getPilotaById(pilota.getId());

		if (pilotaEsistente != null) {
			// Aggiorna i dati del pilota
			pilotaEsistente.setNome(pilota.getNome());
			pilotaEsistente.setCognome(pilota.getCognome());
			pilotaEsistente.setNumeroIdentificativo(pilota.getNumeroIdentificativo());
			pilotaEsistente.setNazionalita(pilota.getNazionalita());
			pilotaEsistente.setPeso(pilota.getPeso());
			pilotaEsistente.setAltezza(pilota.getAltezza());
			pilotaEsistente.setTeam(pilota.getTeam());

			// Aggiorna l'immagine, se caricata
			if (file != null && !file.isEmpty()) {
				pilotaService.saveImmagine(pilotaEsistente, file);
			}

			if (sponsorIds != null && !sponsorIds.isEmpty()) {
				List<Sponsor> sponsors = sponsorService.findAllById(sponsorIds);
				pilotaEsistente.setSponsor(sponsors);
			} else {
				pilotaEsistente.setSponsor(new ArrayList<>()); // Nessuno sponsor selezionato
			}

			// Salva le modifiche
			pilotaService.save(pilotaEsistente);
			model.addAttribute("pilota", pilotaEsistente);
			return "redirect:/pilota/" + pilotaEsistente.getId();
		} else {
			model.addAttribute("sponsors", sponsorService.getAllSponsors());
			model.addAttribute("messaggioErrore", "Pilota non trovato per l'aggiornamento.");
			return "admin/formNewPilota.html";
		}
	}





}
