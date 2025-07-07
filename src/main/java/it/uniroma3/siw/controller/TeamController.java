package it.uniroma3.siw.controller;

import it.uniroma3.siw.model.Pilota;
import it.uniroma3.siw.model.Sponsor;
import it.uniroma3.siw.model.Team;
import it.uniroma3.siw.service.PilotaService;
import it.uniroma3.siw.service.SponsorService;
import it.uniroma3.siw.service.TeamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;

@Controller
public class TeamController {
    @Autowired 
    private TeamService teamService;
    @Autowired
    private SponsorService sponsorService;
    @Autowired
    private PilotaService pilotaService;


    @GetMapping("/team/{id}")
    public String getTeam(@PathVariable("id") Long id, Model model) {
        Team team = teamService.getTeamById(id);
        if(team == null)
            return "pilotiETeam.html";
        model.addAttribute("team", team);
        return "team.html";
    }
    

    @GetMapping("/admin/formNewTeam")
    public String formNewSponsor(Model model) {
        model.addAttribute("team", new Team());
        model.addAttribute("piloti", pilotaService.getPilotiDisponibili());
        model.addAttribute("sponsors", sponsorService.getAllSponsors());
        return "admin/formNewTeam.html";
    }

    @PostMapping("/admin/formNewTeam")
    public String salvaTeam(@ModelAttribute("team") Team team,
                            @RequestParam("file") MultipartFile file,
                            @RequestParam(required = false) List<Long> pilotiIds,
                            @RequestParam(name = "sponsor", required = false) List<Long> sponsorIds,
                            Model model) {

        if (!teamService.existsByNome(team.getNome())) {
            //Gestisci immagine
            if (file != null && !file.isEmpty())
                teamService.saveImmagine(team, file);

            //Gestisci sponsor
            if(sponsorIds != null && !sponsorIds.isEmpty()) {
                List<Sponsor> sponsors = sponsorService.findAllById(sponsorIds);
                team.setSponsor(sponsors);
            }

            teamService.save(team);

            //Gestisci aggiunta ai team
            if(pilotiIds != null) {
                List<Pilota> pilotiSelezionati = pilotaService.findAllById(pilotiIds);
                for(Pilota pilota : pilotiSelezionati) {
                    pilota.setTeam(team);
                    pilotaService.save(pilota);
                }
            }

            model.addAttribute("team", team);
            return "redirect:/team/" + team.getId();
        } else {
            model.addAttribute("piloti", pilotaService.getPilotiDisponibili());
            model.addAttribute("sponsors", sponsorService.getAllSponsors());
            model.addAttribute("messaggioErrore", "Questo team esiste già");
            return "admin/formNewTeam.html";
        }
    }

    @GetMapping("/admin/deleteTeam/{teamId}")
    public String deleteTeam(@PathVariable("teamId") Long teamId, Model model) {
        teamService.deleteById(teamId);
        return "redirect:/pilotiETeam";
    }

    @GetMapping("/admin/formUpdateTeam/{id}")
    public String formEditTeam(@PathVariable("id") Long id, Model model) {
        Team team = teamService.getTeamById(id);

        if (team != null) {
            model.addAttribute("team", team);
            model.addAttribute("sponsors", sponsorService.getAllSponsors());

            List<Pilota> pilotiDisponibili = pilotaService.getPilotiDisponibili();
            if(team.getPilotiUfficiali() != null)
                pilotiDisponibili.addAll(team.getPilotiUfficiali());
            model.addAttribute("piloti", pilotiDisponibili);

            return "admin/formUpdateTeam.html";
        } else {
            model.addAttribute("messaggioErrore", "Team non trovato.");
            return "redirect:/pilotiETeam";
        }
    }

    @PostMapping("/admin/formUpdateTeam")
    public String updateTeam(@ModelAttribute("team") Team team,
                             @RequestParam("file") MultipartFile file,
                             @RequestParam(required = false) List<Long> pilotiIds,
                             @RequestParam(name = "sponsor", required = false) List<Long> sponsorIds,
                             Model model) {
        // Controlla l'esistenza del team in base all'ID
        Team teamEsistente = teamService.getTeamById(team.getId());

        if (teamEsistente != null) {
            // Aggiorna i dati del team
            teamEsistente.setNome(team.getNome());
            teamEsistente.setMarcaMoto(team.getMarcaMoto());

            // Aggiorna l'immagine, se caricata
            if (file != null && !file.isEmpty()) {
                teamService.saveImmagine(teamEsistente, file);
            }

            if (sponsorIds != null && !sponsorIds.isEmpty()) {
                List<Sponsor> sponsors = sponsorService.findAllById(sponsorIds);
                teamEsistente.setSponsor(sponsors);
            } else {
                teamEsistente.setSponsor(new ArrayList<>()); // Nessuno sponsor selezionato
            }

            // Salva le modifiche
            teamService.save(teamEsistente);

            //Gestisci aggiunta ai team
            if(pilotiIds != null) {
                List<Pilota> pilotiSelezionati = pilotaService.findAllById(pilotiIds);
                teamService.rimuoviPilotiUfficiali(teamEsistente);
                for(Pilota pilota : pilotiSelezionati) {
                    pilota.setTeam(teamEsistente);
                    pilotaService.save(pilota);
                }
            }

            model.addAttribute("team", teamEsistente);
            return "redirect:/team/" + teamEsistente.getId();
        } else {
            //Aggiungi i piloti stessi alla lista dei disponibili
            List<Pilota> pilotiDisponibili = pilotaService.getPilotiDisponibili();
            if(team.getPilotiUfficiali() != null)
                pilotiDisponibili.addAll(team.getPilotiUfficiali());
            model.addAttribute("piloti", pilotiDisponibili);

            model.addAttribute("sponsors", sponsorService.getAllSponsors());
            model.addAttribute("messaggioErrore", "Team non trovato per l'aggiornamento.");
            return "admin/formNewTeam.html";
        }
    }
}