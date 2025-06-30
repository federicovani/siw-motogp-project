package it.uniroma3.siw.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import it.uniroma3.siw.service.CampionatoTeamService;

@Controller
public class CampionatoTeamController {

    @Autowired
    private CampionatoTeamService campionatoTeamService;

    @GetMapping("/campionati/team")
    public String listaCampionatiTeam(Model model) {
        model.addAttribute("campionatiTeam", campionatoTeamService.getAllCampionati());
        return "campionatiTeam.html";
    }

    @GetMapping("/campionati/team/{id}")
    public String dettagliCampionatoTeam(@PathVariable("id") Long id, Model model) {
        model.addAttribute("campionatoTeam", campionatoTeamService.getCampionatoById(id));
        return "dettaglioCampionatoTeam.html";
    }
}
