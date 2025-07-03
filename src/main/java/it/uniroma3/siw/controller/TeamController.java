package it.uniroma3.siw.controller;

import it.uniroma3.siw.model.Pilota;
import it.uniroma3.siw.model.Team;
import it.uniroma3.siw.service.PilotaService;
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
    private PilotaService pilotaService;
    @Autowired 
    private TeamService teamService;


    @GetMapping("/team/{id}")
    public String getTeam(@PathVariable("id") Long id, Model model) {
        Team team = teamService.getTeamById(id);
        if(team == null)
            return "pilotiETeam.html";
        model.addAttribute("team", team);
        return "team.html";
    }
    

    @GetMapping("/admin/formNewTeam")
    public String formNewPilota(Model model) {
        model.addAttribute("team", new Team());
        return "admin/formNewTeam.html";
    }

    @PostMapping("/admin/formNewTeam")
    public String salvaTeam(@ModelAttribute("team") Team team, @RequestParam("file") MultipartFile file, Model model) {

        if (!teamService.existsByNome(team.getNome())) {
            if (file != null && !file.isEmpty())
                teamService.saveImmagine(team, file);
            teamService.save(team);
            model.addAttribute("team", team);
            return "redirect:/team/" + team.getId();
        } else {
            model.addAttribute("messaggioErrore", "Questo team esiste già");
            return "admin/formNewTeam.html";
        }
    }

    @GetMapping("/admin/deleteTeam/{teamId}")
    public String deleteTeam(@PathVariable("teamId") Long teamId, Model model) {
        teamService.deleteById(teamId);
        return "redirect:/pilotiETeam";
    }
}
